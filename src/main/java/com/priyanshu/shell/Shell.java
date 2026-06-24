package com.priyanshu.shell;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.priyanshu.shell.commands.*;
import com.priyanshu.shell.commands.builtin.*;
import com.priyanshu.shell.executor.PipelineExecutor;
import com.priyanshu.shell.parser.Expander;
import com.priyanshu.shell.parser.ParsedCommand;
import com.priyanshu.shell.parser.Parser;

public class Shell {
    private final Map<String, Command> builtinCommands;

    private final Map<String,String> env;

    public Map<String, String> getEnv() {
        return env;
    }

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
        builtinCommands.put("export", new ExportCommand(this));
        currentDirectory = Paths.get(System.getProperty("user.dir"));
        env = new HashMap<>();
    }


    public void start(){
        String command;

        try(Scanner sc=new Scanner(System.in);){
            while (true){
                System.out.print("$ ");
                command=sc.nextLine();
                if(command.isEmpty()) continue;
                command= Expander.expand(command, env);
                List<ParsedCommand> commands=Parser.handlePipeline(command);
                try{
                    PipelineExecutor.execute(commands,builtinCommands,currentDirectory);
                }
                catch(IOException e){
                    System.out.println("shell: error executing command");
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
