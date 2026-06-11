package com.priyanshu.shell;

import com.priyanshu.shell.parser.Parser;

import java.util.Arrays;
import java.util.Scanner;

public class Shell {
    public void start(){
        Scanner sc=new Scanner(System.in);
        String command;

        while (true){
            System.out.print("$ ");
            command=sc.nextLine();
            if(command.equals("exit")){
                break;
            }
            String[] tokens=Parser.parseInput(command);
            System.out.println("Command: "+tokens[0]);
            System.out.println("Args: "+Arrays.toString(Arrays.copyOfRange(tokens, 1, tokens.length)));
        }
    }
}
