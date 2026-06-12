package com.priyanshu.shell.commands;

import java.io.IOException;

public class ExternalCommand {
    public static boolean execute(String[] tokens){
        try{
            ProcessBuilder pb=new ProcessBuilder(tokens);
            pb.inheritIO();
            Process process=pb.start();
            process.waitFor();
            return true;
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
