package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;
import enums.Operator;

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // 以下3行が仕様。
            String input = br.readLine();
            String[] params = input.split(" ");

            // <整数 演算子 整数> の形式か判定
            if (params.length != 3 || !isIntegers(params) || !hasOperator(params)) {
                System.out.println("値は <整数 演算子 整数> の形式で入力してください。\n(e.g. 2 + 3)");
                return;
            }

            var leftVal = Integer.valueOf(params[0]);
            var rightVal = Integer.valueOf(params[2]);

            if (!isInRange(leftVal, rightVal)) {
                System.out.println("演算子の両辺は -10000 以上 10000 以下で入力してください。");
                return;
            }

            var operator = Operator.getOperator(params[1]);

            calc(leftVal, rightVal, operator);
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
     * @param leftVal  演算子の左辺
     * @param rightVal 演算子の右辺
     * @param operator 演算子
     */
    private static void calc(Integer leftVal, Integer rightVal, Operator operator) {
        switch (operator) {
            case ADDITION:
                System.out.println(leftVal.toString() + " + " + rightVal.toString() + " = "
                        + (leftVal.intValue() + rightVal.intValue()));
                break;
            case SUBTRACTION:
                System.out.println(leftVal.toString() + " - " + rightVal.toString() + " = "
                        + (leftVal.intValue() - rightVal.intValue()));
                break;
            case MULTIPLICATION:
                System.out.println(leftVal.toString() + " * " + rightVal.toString() + " = "
                        + (leftVal.intValue() * rightVal.intValue()));
                break;
            case DIVISION:
                System.out.println(leftVal.toString() + " / " + rightVal.toString() + " = "
                        + (leftVal.intValue() / rightVal.intValue()));
                break;
        }
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
                && pattern.matcher(params[2]).matches();
    }

    /**
     * 四則演算子が入力されているか判定する
     * 
     * @param params 入力値
     * @return true: 四則演算子が入力されている、 false: 四則演算子が入力されていない
     */
    private static boolean hasOperator(String[] params) {
        Operator operator = Operator.getOperator(params[1]);

        // 四則演算が入力されているか判定
        return operator != null;
    }

    /**
     * 入力値が規定の範囲内の値か判定する
     * 
     * @param leftVal  演算子の左辺
     * @param rightVal 演算子の右辺
     * @return true: 範囲内、 false: 範囲外
     */
    private static boolean isInRange(Integer leftVal, Integer rightVal) {
        return (-10000 <= leftVal.intValue() && leftVal.intValue() <= 10000)
                && (-10000 <= rightVal.intValue() && rightVal.intValue() <= 10000);
    }
}
