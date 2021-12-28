package exercise;

public class GrammarExercise1 {
    public static void main(String[] args) {
        System.out.println("引数の個数: " + args.length + "個");

        for (int i = 0; i < args.length; i++) {
            System.out.println("引数 " + (i + 1) +" 番目は " + args[i]);
        }
    }
}
