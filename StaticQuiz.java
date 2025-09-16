public class StaticQuiz implements Quiz {
    private final String q;
    private final String a;
    private final String hint;

    public StaticQuiz(String prompt, String answer) {
        this(prompt, answer, "");
    }

    public StaticQuiz(String prompt, String answer, String hint) {
        this.q = prompt;
        this.a = answer;
        this.hint = hint == null ? "" : hint;
    }

    @Override
    public String prompt() {
        return q;
    }

    @Override
    public boolean check(String answer) {
        if (answer == null) return false;
        // Case-insensitive, trim spaces
        return a.trim().equalsIgnoreCase(answer.trim());
    }

    @Override
    public String hint() {
        return hint;
    }
}

