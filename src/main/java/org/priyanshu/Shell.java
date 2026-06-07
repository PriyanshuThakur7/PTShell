package org.priyanshu;

import java.util.Scanner;

public class Shell {
    public void start(){
        Scanner sc=new Scanner(System.in);
        String command="";
        while (true){
            System.out.print("$ ");
            command=sc.nextLine();
            if(command.equals("exit")){
                break;
            }
            System.out.println("Command: "+command);
        }
    }
}
