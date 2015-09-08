
public class Phone {

    private static Button[] buttons = new Button[]{new Button("#"), new Button(" "),
        new Button("ABC"), new Button("DEF"), new Button("GHI"), new Button("JKL"), new Button("MNO"),
        new Button("PQRS"), new Button("TUV"), new Button("WXYZ")};
    private StringBuffer sb = new StringBuffer();

    public void press(final char c) {
        if (Character.isDigit(c)) {
            buttons[c - '0'].press();
            sb.append(buttons[c - '0'].getResult());
        } else {
            sb.append('-');
        }
    }

    public void press(final String s) {
        if (1 == s.length()) {
            press(s.charAt(0));
            return;
        }
        int i = 0, j = 0;
        boolean f = true;
        while (f) {
            while (f && s.charAt(i) == s.charAt(j)) {
                if (buttons[s.charAt(i) - '0'].isLast()) 
                    sb.append(buttons[s.charAt(i) - '0'].getResult());
                buttons[s.charAt(i) - '0'].press();
                if (++j == s.length()) 
                    f = !f;
            }
            sb.append(buttons[s.charAt(i) - '0'].getResult());
            i = j;
        }

    }

    public String getResult() {
//         String s = sb.toString().indexOf("#") > -1 ? sb.toString().replaceAll("#", "") : sb.toString();
        String s = sb.toString().replaceAll("#", "");
        reset();
        return s;
    }

    private void reset() {
        sb = new StringBuffer();
        System.gc();
    }

    public static void main(String[] args) {
//        Button b = new Button("1111");
//        b.getResult();
//        System.out.println(b.getResult());
        Phone p = new Phone();
//        p.press(new java.util.Scanner(System.in).nextLine());
        p.press("4433555555666196667775553");
        System.out.println(p.getResult());
    }

}
