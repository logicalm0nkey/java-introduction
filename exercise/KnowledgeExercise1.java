package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class KnowledgeExercise1 {
    public static void main(String[] args) throws Exception {
        File txtA = new File("resources/textA.txt");
        File txtB = new File("resources/textB.txt");
        try (BufferedReader br = Files.newBufferedReader(txtA.toPath(), Charset.forName("UTF8"));
                PrintWriter pw = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtB), "UTF8")))) {
            String txt;
            while ((txt = br.readLine()) != null) {
                pw.println(txt);
            }
        }
    }
}
