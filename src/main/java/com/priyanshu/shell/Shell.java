package com.priyanshu.shell;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.priyanshu.shell.commands.*;
import com.priyanshu.shell.commands.builtin.*;
import com.priyanshu.shell.executor.PipelineExecutor;
import com.priyanshu.shell.parser.Parser;

public class Shell {
    private final Map<String, Command> builtinCommands;

    private Path currentDirectory;

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Path currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public Shell(){
        builtinCommands=new HashMap<>();
        builtinCommands.put("exit", new ExitCommand());
        builtinCommands.put("pwd", new PwdCommand(this));
        builtinCommands.put("cd", new CdCommand(this));
        currentDirectory = Paths.get(System.getProperty("user.dir"));
    }


    public void start(){
        Scanner sc=new Scanner(System.in);
        String command;

        while (true){
            System.out.print("$ ");
            command=sc.nextLine();
            if(command.isEmpty()) continue;
            List<String[]> commands=Parser.handlePipeline(command);
            try{
                PipelineExecutor.execute(commands,builtinCommands,currentDirectory);
            }
            catch (Exception e){
                System.out.println("Error: Not a valid command");
            }
        }
    }
}
