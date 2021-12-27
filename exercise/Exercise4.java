package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.regex.Pattern;
import enums.*;

public class Exercise4 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            String[] params = input.split(" ");

            Integer result = calc(params, 0, params.length - 1);

            if (result != null) {
                System.out.println("計算結果: " + result.intValue());
            }
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
    private static Integer calc(String[] params, int startIndex, int endIndex) {
        Integer left = Integer.valueOf(0);
        Integer right = Integer.valueOf(0);
        Operator operator = Operator.ADDITION;
        // 1個前の入力値の型 (半角スペースの1個前)
        PreType preType = PreType.DEFAULT;

        for (int i = startIndex; i <= endIndex; i++) {
            if (isInteger(params[i])) {
                if (preType == PreType.INTEGER) {
                    System.out.println("2回連続で整数を入力しないでください。");
                    return null;
                }

                right = Integer.parseInt(params[i]);
                preType = PreType.INTEGER;

                if (!isInRange(right)) {
                    System.out.println("整数は -10000 以上 10000 以下のみ有効です。");
                    return null;
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

            if (isOperator(params[i])) {
                if (preType == PreType.OPERATOR) {
                    System.out.println("2回連続で演算子を入力しないでください。");
                    return null;
                }

                operator = Operator.getOperator(params[i]);
                preType = PreType.OPERATOR;

                if (operator == Operator.MULTIPLICATION || operator == Operator.DIVISION) {
                    System.out.println("演算子は +, - だけ有効です。");
                    return null;
                }

                continue;
            }

            if (params[i].equals("(")) {
                // ( が現れたら、対応する ) のインデックスを取得
                int closedBracketIndex = getClosedBracketIndex(params, i);
                right = calc(params, i + 1, closedBracketIndex);

                if (operator == Operator.ADDITION) {
                    // 加算
                    left += right;
                } else {
                    // 減算
                    left -= right;
                }

                i = closedBracketIndex;
                continue;
            }
        }

        return left;
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

    private static int getClosedBracketIndex(String[] params, int openBracketIndex) {
        int bracketCount = 0;
        int ret = 0;

        for (int i = openBracketIndex + 1; i < params.length; i++) {
            if (params[i].equals("(")) {
                // 開き括弧が現れるたびに増やす
                bracketCount++;
                continue;
            }

            if (params[i].equals(")")) {
                if (bracketCount != 0) {
                    // 閉じ括弧が現れたら減らす。
                    // こうすると、対応する () を全て取りつくしたとき、 bracketCount = 0 となる。
                    bracketCount--;
                } else {
                    ret = i;
                    break;
                }
            }
        }

        return ret;
    }
}
