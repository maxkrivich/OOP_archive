
public class Button {

    private int cnt;
    private final String LETTERS;

    public Button(String lt) {
        this.LETTERS = lt;
        this.cnt = -1;
    }

    public void press() {
        this.cnt++;
    }

    public char getResult() {
        try {
            if (-1 == this.cnt) 
                throw new Exception();
            char c = LETTERS.charAt(cnt);
            reset();
            return c;
        } catch (Exception e) {
            System.err.println("You did not press the button!");
        }
        return '-';
    }

    public boolean isLast() {
        return (-1 == this.cnt ? 0 : this.cnt + 1) == this.LETTERS.length();
    }

    private void reset() {
        this.cnt = -1;
    }

}
