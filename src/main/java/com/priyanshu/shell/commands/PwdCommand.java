package com.priyanshu.shell.commands;

import com.priyanshu.shell.Shell;

public class PwdCommand implements Command{

    public PwdCommand(Shell shell){
        this.shell=shell;
    }

    Shell shell;

    @Override
    public void execute(String[] args) {
        System.out.println(shell.getCurrentDirectory());
    }
}
