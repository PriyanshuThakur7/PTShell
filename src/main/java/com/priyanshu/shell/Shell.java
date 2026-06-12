package com.priyanshu.shell;

import java.util.HashMap;
import java.util.Map;

import com.priyanshu.shell.commands.*;
import com.priyanshu.shell.parser.Parser;

import java.util.Arrays;
import java.util.Scanner;

public class Shell {
    private final Map<String, Command> commands;

    public Shell(){
        commands=new HashMap<>();
        commands.put("exit", new ExitCommand());
        commands.put("echo", new EchoCommand());
        commands.put("pwd", new PwdCommand());
    }


    public void start(){
        Scanner sc=new Scanner(System.in);
        String command;

        while (true){
            System.out.print("$ ");
            command=sc.nextLine();
            String[] tokens=Parser.parseInput(command);
            if(tokens[0].isEmpty()) continue;
            String[] args=Arrays.copyOfRange(tokens, 1, tokens.length);
            Command cmd = commands.get(tokens[0]);
            if(cmd != null){
                cmd.execute(args);
            } else {
                if(!ExternalCommand.execute(tokens)) System.out.println(tokens[0] + ": command not found");
            }
        }
    }
}
