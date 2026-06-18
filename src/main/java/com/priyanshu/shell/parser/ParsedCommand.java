package com.priyanshu.shell.parser;

public class ParsedCommand {
    public String[] args;
    public String outputFile;
    public boolean append;
    public String inputFile;

    public ParsedCommand(String[] args,String outputFile, String inputFile, boolean append) {
        this.args=args;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        this.append = append;
    }
}
