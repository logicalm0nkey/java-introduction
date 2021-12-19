package exercise;

/**
 * 演算子
 */
public enum Operator {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    ;

    private final String operator;

    private Operator(final String operator) {
        this.operator = operator;
    }

    /**
     * 演算子の記号を文字列で返す (+, -, *, /)
     * 
     * @return 演算子の記号
     */
    public String getSymbolString() {
        return this.operator;
    }

    /**
     * 文字列から演算子を取得する
     * 
     * @param param
     * @return 演算子。引数が +, -, *, / 以外の場合は null 。
     */
    public static Operator getOperator(final String param) {
        Operator[] operators = Operator.values();

        for (Operator operator : operators) {
            if (operator.getSymbolString().equals(param)) {
                return operator;
            }
        }

        return null;
    }
}
