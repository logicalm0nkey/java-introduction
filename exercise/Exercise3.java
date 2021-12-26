package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;
import enums.*;

public class Exercise3 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            String[] params = input.split(" ");

            calc(params);
        } catch (Exception e) {
            // Throwable インターフェースのスタックトレースを出力するテンプレ
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            System.out.println("例外発生: " + e.getMessage() + "\n" + sw.toString());
        }
    }

    /**
     * 計算する
     * 
     * @param params 入力値
     */
    private static void calc(String[] params) {
        Integer left = Integer.valueOf(0);
        Integer right = Integer.valueOf(0);
        Operator operator = Operator.ADDITION;
        // 1個前の入力値の型 (半角スペースの1個前)
        PreType preType = PreType.DEFAULT;

        for (String param : params) {
            if (isInteger(param)) {
                if (preType == PreType.INTEGER) {
                    System.out.println("2回連続で整数を入力しないでください。");
                    System.out.println("現在の計算結果: " + left);
                    return;
                }

                right = Integer.parseInt(param);
                preType = PreType.INTEGER;

                if (!isInRange(right)) {
                    System.out.println("整数は -10000 以上 10000 以下のみ有効です。");
                    System.out.println("現在の計算結果: " + left);
                    return;
                }

                // 2回目以降の整数は計算していく
                if (operator == Operator.ADDITION) {
                    // 加算
                    left += right;
                    continue;
                }

                // 減算
                left -= right;
                continue;
            }

            if (isOperator(param)) {
                if (preType == PreType.OPERATOR) {
                    System.out.println("2回連続で演算子を入力しないでください。");
                    System.out.println("現在の計算結果: " + left);
                    return;
                }

                operator = Operator.getOperator(param);
                preType = PreType.OPERATOR;

                if (operator == Operator.MULTIPLICATION || operator == Operator.DIVISION) {
                    System.out.println("演算子は +, - だけ有効です。");
                    System.out.println("現在の計算結果: " + left);
                    return;
                }
                
                continue;
            }

            // ここに到達したときは、入力値が整数でも演算子でもない時なのでエラー扱い。
            System.out.println("入力値は整数または演算子のみ有効です。");
            System.out.println("現在の計算結果: " + left);
            return;
        }

        System.out.println("計算結果: " + left);
    }

    /**
     * 入力値が整数か判定する
     * 
     * @param param 入力値
     * @return true: 整数、 false: 整数でない
     */
    private static boolean isInteger(String param) {
        // 負の数を含めた整数か判定する正規表現
        Pattern pattern = Pattern.compile("-?\\d+");

        // 入力値が整数か判定
        return pattern.matcher(param).matches();
    }

    /**
     * 四則演算子か判定する
     * 
     * @param param 入力値
     * @return true: 四則演算子、 false: 四則演算子でない
     */
    private static boolean isOperator(String param) {
        Operator operator = Operator.getOperator(param);

        // 四則演算が入力されているか判定
        return operator != null;
    }

    /**
     * 入力値が規定の範囲内の値か判定する
     * 
     * @param value 入力値
     * @return true: 範囲内、 false: 範囲外
     */
    private static boolean isInRange(Integer value) {
        return -10000 <= value.intValue() && value.intValue() <= 10000;
    }
}
