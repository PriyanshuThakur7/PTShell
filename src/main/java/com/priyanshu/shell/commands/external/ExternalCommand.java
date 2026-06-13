package com.priyanshu.shell.commands.external;

import java.io.IOException;
import java.nio.file.Path;

public class ExternalCommand {
    public static boolean execute(String[] tokens, Path currentDirectory) {
        try{
            ProcessBuilder pb=new ProcessBuilder(tokens);
            pb.directory(currentDirectory.toFile());
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
