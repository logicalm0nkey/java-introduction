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

public class KnowledgeExercise5 {
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

            // キーの昇順で表示
            System.out.println("キー昇順。");
            map.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .forEach(System.out::println);

            // キーの短い順。長さが同じときは昇順。
            System.out.println("キーの長さ順。次いでキー昇順。");
            map.entrySet()
                .stream()
                // ここ、 sorted の中の Comparator を逆にすると↑と同じ結果になってしまう。なぜ？
                .sorted((s1, s2) -> s1.getKey().compareTo(s2.getKey()))
                .sorted((e1, e2) -> e1.getKey().length() - e2.getKey().length())
                .forEach(System.out::println);
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
