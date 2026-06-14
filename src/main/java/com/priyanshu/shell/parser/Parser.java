package com.priyanshu.shell.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static String[] parseInput(String input){
        return input.split(" ");
    }
    public static List<String[]> handlePipeline(String input){
        String[] commands = input.split("\\|");
        List<String[]> pipeline=new ArrayList<>();
        for(String cmd: commands){
            pipeline.add(parseInput(cmd.trim()));
        }
        return pipeline;
    }
}
