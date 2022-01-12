package exercise;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class KnowledgeExercise2 {
    public static void main(String[] args) throws Exception {
        try {
            // resources/アイコン.png
            File icon = new File(args[0]);
            // resources/copy.png
            File copy = new File(args[1]);

            try (BufferedInputStream biStream = new BufferedInputStream(new FileInputStream(icon));
                    BufferedOutputStream boStream = new BufferedOutputStream(new FileOutputStream(copy))) {
                // 読み込み用バイト列
                byte[] buf = new byte[4096];
                int length;

                // biStream.read(buf, 0, buf.length) で
                // 1. 読み込んだバイト列が buf に格納される
                // 2. 読み込んだバイト列の長さが戻り値として得られる。 -1 で読み切ったことを表す。
                while ((length = biStream.read(buf, 0, buf.length)) != -1) {
                    // 書き込み。
                    boStream.write(buf, 0, length);
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
