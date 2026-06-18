package com.priyanshu.shell.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static String[] parseInput(String input){
        return input.split(" ");
    }
    public static List<ParsedCommand> handlePipeline(String input){
        String[] commands = input.split("\\|");
        List<ParsedCommand> pipeline=new ArrayList<>();
        for(String cmd: commands){
            String trimmed = cmd.trim();
            if(trimmed.isEmpty()) continue;
            pipeline.add( parseRedirection( parseInput(trimmed) ) );
        }
        return pipeline;
    }
    public static ParsedCommand parseRedirection(String[] tokens){
        String outputFile = null;
        String inputFile = null;
        boolean append = false;
        int cutIndex = tokens.length; // default: no redirection found, keep all tokens

        List<String> tokenList = Arrays.asList(tokens);

        if(tokenList.contains(">")) {
            int idx = tokenList.indexOf(">");
            outputFile = tokens[idx+1];
            cutIndex = Math.min(cutIndex, idx);
        }
        if(tokenList.contains(">>")) {
            int idx = tokenList.indexOf(">>");
            outputFile = tokens[idx+1];
            append = true;
            cutIndex = Math.min(cutIndex, idx);
        }
        if(tokenList.contains("<")) {
            int idx = tokenList.indexOf("<");
            inputFile = tokens[idx+1];
            cutIndex = Math.min(cutIndex, idx);
        }

        return new ParsedCommand(Arrays.copyOfRange(tokens, 0, cutIndex), outputFile, inputFile, append);
    }
}
