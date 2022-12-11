package junli.jdbc.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Script {

    public static void main(String[] args) {
        ArrayList<String> commands = parseCommand("/Users/xiaojunli/MySQL_Homework/JDBC/Comment_test.sql");
        // 印出 SQL 腳本移除掉註解的內容
        for (String command: commands) {
            System.out.println(command);
        }
    }

    // 從讀取到的腳本拆分出一個一個指令，存進ArrayList
    public static ArrayList<String> parseCommand(String filePath) {
        String script = readScript(filePath);
        String[] array = script.split(";");
        ArrayList<String> commands = new ArrayList<>();

        for (String s: array) {
            s = s.trim();
            if(s.length() != 0) {
                commands.add(s + ";");
            }
        }
        return commands;
    }

    // 先從檔案讀到整個 SQL 腳本檔內容，清除單行/多行註解
    private static String readScript(String filePath) {
        StringBuilder sb = new StringBuilder();
        String str;

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr) ;
            while ((str = br.readLine()) != null) {
                sb.append(removeCommentSingle(str)).append(" ");
            }
            br.close();
            fr.close();
        } catch(IOException e){
            System.out.println("Failed reading the SQL script: " + filePath);
        }
        return lessSpace(removeCommentMultiple(sb.toString()));
    }

    // 清除單行註解，為必要
    private static String removeCommentSingle(String line) {
        int index = line.indexOf("-- ");
        return index == -1 ? line : line.substring(0, index);
    }

    // 清除多行註解，非必要，不影響指令重組後的語意
    private static String removeCommentMultiple(String lines) {
        // StackOverflow: Java - regular expression finding comments in code
        String commentRegex = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";
        return lines.replaceAll(commentRegex, "$1 ");
    }

    // 將兩個以上的 space 或 tab 替換成一個 space
    private static String lessSpace(String str) {
        return str.trim().replaceAll(" +|\t+", " ");
    }
}
