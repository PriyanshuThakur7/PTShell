package com.priyanshu.shell.commands.builtin;

import com.priyanshu.shell.Shell;
import com.priyanshu.shell.commands.Command;

public class PwdCommand implements Command {

    public PwdCommand(Shell shell){
        this.shell=shell;
    }

    Shell shell;

    @Override
    public void execute(String[] args) {
        System.out.println(shell.getCurrentDirectory());
    }
}
