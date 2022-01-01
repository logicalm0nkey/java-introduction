package exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class KnowledgeExercise3 {
    public static void main(String[] args) throws Exception {
        try {
            // resources/textA.txt
            File txtA = new File(args[0]);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(txtA), Charset.forName("UTF8")))) {
                String txt;
                // テキスト格納用のリスト
                List<String> lines = new ArrayList<String>();

                while ((txt = br.readLine()) != null) {
                    // 格納
                    lines.add(txt);
                }

                // 格納した順番で出力
                lines.forEach(e -> System.out.println(e));
                // 逆順で出力
                for (ListIterator<String> li = lines.listIterator(lines.size()); li.hasPrevious();) {
                    System.out.println(li.previous());
                }

                // 「あいうえお」の行番号を取得
                System.out.println(lines.indexOf("あいうえお") + 1);
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
