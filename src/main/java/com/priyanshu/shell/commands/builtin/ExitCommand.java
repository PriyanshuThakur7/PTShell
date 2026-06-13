package com.priyanshu.shell.commands.builtin;

import com.priyanshu.shell.commands.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}
