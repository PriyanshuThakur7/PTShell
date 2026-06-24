package com.priyanshu.shell.commands.builtin;

import com.priyanshu.shell.Shell;
import com.priyanshu.shell.commands.Command;

public class ExportCommand implements Command {

    private final Shell shell;

    public ExportCommand(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void execute(String[] args) {
        int idx=args[0].indexOf('=');
        if(idx==-1) {
            System.out.println("export: invalid argument");
            return;
        }
        else{
            String key=args[0].substring(0,idx);
            String value=args[0].substring(idx+1);
            shell.getEnv().put(key,value);
        }
    }
}
