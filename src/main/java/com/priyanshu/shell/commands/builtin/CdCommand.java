package com.priyanshu.shell.commands.builtin;

import com.priyanshu.shell.Shell;
import com.priyanshu.shell.commands.Command;

import java.nio.file.Files;
import java.nio.file.Path;

public class CdCommand implements Command {

    public CdCommand(Shell shell) {
        this.shell = shell;
    }

    private Shell shell;

    @Override
    public void execute(String[] args) {
        Path newPath=shell.getCurrentDirectory().resolve(args[0]).normalize();
        if(Files.isDirectory(newPath)) {
            shell.setCurrentDirectory(newPath);
        }
        else{
            System.out.println("cd: " + args[0] + ": No such file or directory");
        }
    }
}
