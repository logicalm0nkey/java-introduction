package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Exercise1 {
    public static void main(String[] args) throws Exception {
        // InputStreamReader クラスを BufferedReader でラップ。
        // こうすることで、読み込んだ文字をバッファに蓄えられ、 readline() メソッドを使って1行ずつ読み込めるらしい。
        // System.in がいわゆる stdinput らしい。
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 入力パラメータを読み込む
        String input = br.readLine();
        // 入力値を空白で分解する
        String[] params = input.split(" ");

        if (!isIntegers(params)) {
            System.out.println("整数を入力してください。");
            return;
        }

        var val1 = Integer.valueOf(params[0]);
        var val2 = Integer.valueOf(params[1]);

        if (!isInRange(val1, val2)) {
            System.out.println("-10000 以上 10000 以下で入力してください。");
            return;
        }

        calc(val1, val2);

        br.close();
    }

    /**
     * 計算する
     * 
     * @param param 入力値
     */
    private static void calc(Integer val1, Integer val2) {
        System.out.println(val1.toString() + " + " + val2.toString() + " = "
                + (val1.intValue() + val2.intValue()));
    }

    /**
     * 入力値が整数か判定する
     * 
     * @param params 入力値
     * @return true: 整数、 false: 整数でない
     */
    private static boolean isIntegers(String[] params) {
        // 負の数を含めた整数か判定する正規表現
        Pattern pattern = Pattern.compile("-?\\d+");

        // 入力値が整数か判定
        return pattern.matcher(params[0]).matches()
                && pattern.matcher(params[1]).matches();
    }

    /**
     * 入力値が規定の範囲内の値か判定する
     * 
     * @param val1 1つ目の入力値
     * @param val2 2つ目の入力値
     * @return true: 範囲内、 false: 範囲外
     */
    private static boolean isInRange(Integer val1, Integer val2) {
        return (-10000 <= val1.intValue() && val1.intValue() <= 10000)
                && (-10000 <= val2.intValue() && val2.intValue() <= 10000);
    }
}
