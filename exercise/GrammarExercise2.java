package exercise;

import java.io.*;

public class GrammarExercise2 {
    public static void main(String[] args) throws Exception {
        try {
            throw new NullPointerException("ぬるぽ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            System.out.println("例外発生: " + e.getMessage() + "\n" + sw.toString());
        }

        try {
            String[] arr = new String[0];
            System.out.println(arr[0]);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            System.out.println("例外発生: " + e.getMessage() + "\n" + sw.toString());
        }

        try {
            Object i = Integer.valueOf(0);
            System.out.println((String)i);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            System.out.println("例外発生: " + e.getMessage() + "\n" + sw.toString());
        }

        System.out.println("正常終了。");
    }
}
