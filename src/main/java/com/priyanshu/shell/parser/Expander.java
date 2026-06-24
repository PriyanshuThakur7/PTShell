package com.priyanshu.shell.parser;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expander {
    public static String expand(String input, Map<String,String> env) {
        StringBuilder result = new StringBuilder();

        Pattern pattern = Pattern.compile("\\$[A-Za-z_][A-Za-z0-9_]*");
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            String varName = matcher.group().substring(1);
            String value;
            if(env.containsKey(varName)){
                value = env.get(varName);
            } else {
                value=System.getenv(varName);
            }
            if(value == null) {
                value = "";
            }
            matcher.appendReplacement(result, Matcher.quoteReplacement(value));
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
