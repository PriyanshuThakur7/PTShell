package com.priyanshu.shell.executor;

import com.priyanshu.shell.commands.Command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PipelineExecutor {
    public static void execute(List<String[]> pipeline, Map<String, Command> commandMap,Path currentDirectory) throws IOException, InterruptedException {
        List<Process> processes=new ArrayList<>();

        for(int i=0;i<pipeline.size();i++){
            String[] cmd=pipeline.get(i);
            if(i==0 && commandMap.containsKey(cmd[0])){
                String[] args= Arrays.copyOfRange(cmd, 1, cmd.length);
                commandMap.get(cmd[0]).execute(args);
                continue;
            }
            ProcessBuilder pb=new ProcessBuilder(cmd);
            pb.directory(currentDirectory.toFile());

            if(i==pipeline.size()-1){
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
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
