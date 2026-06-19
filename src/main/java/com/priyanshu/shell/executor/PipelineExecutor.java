package com.priyanshu.shell.executor;

import com.priyanshu.shell.commands.Command;
import com.priyanshu.shell.parser.ParsedCommand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PipelineExecutor {
    public static void execute(List<ParsedCommand> pipeline, Map<String, Command> commandMap, Path currentDirectory) throws IOException, InterruptedException {
        List<Process> processes=new ArrayList<>();

        for(int i=0;i<pipeline.size();i++){
            ParsedCommand parsedCommand=pipeline.get(i);
            String[] command= parsedCommand.args;

            if(i==0 && commandMap.containsKey(command[0])){

                String[] args= Arrays.copyOfRange(parsedCommand.args, 1, command.length);

                if(parsedCommand.outputFile != null) {
                    PrintStream original = System.out;
                    try(PrintStream fileOut = new PrintStream(new FileOutputStream(parsedCommand.outputFile, parsedCommand.append))) {
                        System.setOut(fileOut);
                        commandMap.get(command[0]).execute(args);
                    } finally {
                        System.setOut(original);
                    }
                } else {
                    commandMap.get(command[0]).execute(args);
                }

                continue;
            }
            ProcessBuilder pb=new ProcessBuilder(command);
            pb.directory(currentDirectory.toFile());

            if(parsedCommand.outputFile != null) {
                File outFile = new File(parsedCommand.outputFile);
                if(parsedCommand.append) {
                    pb.redirectOutput(ProcessBuilder.Redirect.appendTo(outFile));
                } else {
                    pb.redirectOutput(outFile);
                }
            } else if(i==pipeline.size()-1) {
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            }

            if(parsedCommand.inputFile != null) {
                pb.redirectInput(new File(parsedCommand.inputFile));
            }

            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            processes.add(pb.start());
        }


        for(int i=0;i<processes.size()-1;i++){
            final Process from=processes.get(i);
            final Process to=processes.get(i+1);

            new Thread(() -> {
                try {
                    from.getInputStream().transferTo(to.getOutputStream());
                    to.getOutputStream().close();
                } catch (IOException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        if(!processes.isEmpty()){
            processes.getLast().waitFor();
        }
    }
}
