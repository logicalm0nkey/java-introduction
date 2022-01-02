package exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeExercise4 {
    public static void main(String[] args) throws Exception {
        // テキスト格納用のリスト
        Map<String, String> map = new HashMap<String, String>();

        try {
            // resources/textC.txt
            File txtC = new File(args[0]);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(txtC), Charset.forName("UTF8")))) {
                String txt;

                while ((txt = br.readLine()) != null) {
                    // 半角スペースで区切って取得
                    String[] line = txt.split(" ");
                    // 1列目が key 、2列目が value
                    map.put(line[0], line[1]);
                }
            }

            map.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });

            System.out.println("key が TOKYO の value は " + map.get("TOKYO"));
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
