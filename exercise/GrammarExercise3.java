package exercise;

import java.util.Arrays;
import java.util.List;

public class GrammarExercise3 {
    public static void main(String[] args) throws Exception {
        // 配列をリストに変換、出力。
        List<String> list = Arrays.asList(args);
        list.forEach(s -> System.out.println(s));
    }
}
