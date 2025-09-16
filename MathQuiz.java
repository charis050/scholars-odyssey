import java.util.Random;

public class MathQuiz implements Quiz {
    private final int left;
    private final int right;
    private final char op;
    private final int correct;

    public MathQuiz(int min, int max) {
        this(min, max, new char[]{'+', '-', '*'});
    }

    public MathQuiz(int min, int max, char[] ops) {
        Random r = new Random();
        int a = min + r.nextInt(Math.max(1, max - min + 1));
        int b = min + r.nextInt(Math.max(1, max - min + 1));
        this.op = ops[r.nextInt(ops.length)];
        this.left = a;
        this.right = b;
        switch (op) {
            case '+': this.correct = a + b; break;
            case '-': this.correct = a - b; break;
            default:  this.correct = a * b; break; // '*'
        }
    }

    @Override
    public String prompt() {
        return "Solve: " + left + " " + op + " " + right + " = ?";
    }

    @Override
    public boolean check(String answer) {
        try {
            return Integer.parseInt(answer.trim()) == correct;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String hint() {
        return "Tip: You can use paper or a calculator item if you have one.";
    }
}
