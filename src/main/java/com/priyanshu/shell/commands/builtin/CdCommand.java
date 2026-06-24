package com.priyanshu.shell.commands.builtin;

import com.priyanshu.shell.Shell;
import com.priyanshu.shell.commands.Command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements Command {

    public CdCommand(Shell shell) {
        this.shell = shell;
    }

    private final Shell shell;

    @Override
    public void execute(String[] args) {
        Path newPath;

        if(args.length==0){
            newPath= Paths.get(System.getProperty("user.home"));
        }
        else{
            newPath=shell.getCurrentDirectory().resolve(args[0]).normalize();
        }

        if(Files.isDirectory(newPath)) {
            shell.setCurrentDirectory(newPath);
        }
        else{
            System.out.println("cd: " + args[0] + ": No such file or directory");
        }
    }
}
