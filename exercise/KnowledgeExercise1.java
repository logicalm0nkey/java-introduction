package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class KnowledgeExercise1 {
    public static void main(String[] args) throws Exception {
        try {
            // resources/textA.txt
            File txtA = new File(args[0]);
            // resources/textB.txt
            File txtB = new File(args[1]);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(txtA), Charset.forName("UTF8")));
                    // テキストをファイルに追記するときは FileOutputStream のコンストラクタの第2引数で true を指定する
                    PrintWriter pw = new PrintWriter(
                            new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtB), "UTF8")))) {
                String txt;
                
                while ((txt = br.readLine()) != null) {
                    pw.println(txt);
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
