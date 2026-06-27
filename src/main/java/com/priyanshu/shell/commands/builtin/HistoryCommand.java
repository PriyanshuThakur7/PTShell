package com.priyanshu.shell.commands.builtin;

import com.priyanshu.shell.commands.Command;

import java.util.List;

public class HistoryCommand implements Command {

    private final List<String> history;

    public HistoryCommand(List<String> history) {
        this.history = history;
    }

    @Override
    public void execute(String[] args) {

        for(int i=0;i< history.size();i++){
            System.out.println((i+1)+" "+history.get(i));
        }
    }
}
