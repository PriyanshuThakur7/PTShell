package com.priyanshu.shell.commands;

public class EchoCommand implements Command{
    @Override
    public void execute(String[] args){
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }
}
