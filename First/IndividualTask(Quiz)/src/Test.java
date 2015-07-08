import java.util.Scanner;

public class Test {
	final static int c = 6; // question count
	final static int vc = 4; // options count
	private static Question[] q = new Question[c];
	private static Handler h = new Handler(q.length);
	private static String[] Var = new String[c * vc];
	private static String[] Que = new String[c];
	private static String[] Ans = new String[c];
	private static Scanner in = new Scanner(System.in);

	static void loadTest() {

		Que[0] = "How to run JavaProgram on the command prompt?";
		Ans[0] = "2)java JavaProgram";
		Que[1] = "What is the use of the println method?";
		Ans[1] = "2)It is used to print text on the screen with the line break.";
		Que[2] = "How to read a character from the keyboard?";
		Ans[2] = "4)char c=(char)System.in.read()";
		Que[3] = "What is the value of x? int x = 3>>2";
		Ans[3] = "2)0";
		Que[4] = "Which one is a single-line comment?";
		Ans[4] = "2)//...";
		Que[5] = "How do you convert a string of number to a number?";
		Ans[5] = "1)int x;";

		Var[0] = "1)javac JavaProgram";
		Var[1] = "2)java JavaProgram";
		Var[2] = "3)javac JavaProgram.java";
		Var[3] = "4)No one";

		Var[4] = "1)It is used to print text on the screen.";
		Var[5] = "2)It is used to print text on the screen with the line break.";
		Var[6] = "3)It is used to read text from keyboard.";
		Var[7] = "4)It is used to read text from a file.";

		Var[8] = "1)char c=System.read()";
		Var[9] = "2)char c=System.in.read()";
		Var[10] = "3)char c=(char)System.read()";
		Var[11] = "4)char c=(char)System.in.read()";

		Var[12] = "1)1";
		Var[13] = "2)0";
		Var[14] = "3)3";
		Var[15] = "4)-3";

		Var[16] = "1)/...";
		Var[17] = "2)//...";
		Var[18] = "3)/*...";
		Var[19] = "4)/*...*/";

		Var[20] = "1)int x;";
		Var[21] = "2)x as Integer";
		Var[22] = "3)Int[] x";
		Var[23] = "4)No one is correct.";
	}

	static void runTest() {
		for (int i = 0; i < q.length; i++) {
			int tmp = Math.random() >= 0.5 ? 1 : 2;
			switch (tmp) {
			case 1: {
				q[i] = new FreeQuestion(Que[i], Ans[i].substring(2));
				break;
			}
			case 2: {
				String[] vartmp = new String[vc];
				System.arraycopy(Var, i * vc, vartmp, 0, vc);
				q[i] = new MultiQuestion(Que[i], Ans[i].substring(2), vartmp);
				break;
			}
			}
			q[i].printQuestion();
			System.out.print("Answer: ");
			boolean bError = true;
			do {
				try {
					input(q[i]);
					bError = false;
				} catch (Exception e) {
					System.out.println("Input error! Please try again");
					in.nextLine();
				}
			} while (bError);
		}
	}

	static void input(Question q) {
		String s = in.nextLine();
		if (q instanceof FreeQuestion) {
			h.adds(s, q);
			return;
		}
		if (q instanceof MultiQuestion) {
			h.adds(((MultiQuestion) q).getVar(Integer.parseInt(s) - 1).substring(2), q);
			return;
		}
	}

	static void stopTest() {
		h.clear();
		Score.score = 0;
	}

	public static void main(String[] args) {
		loadTest();
		long a = System.currentTimeMillis();
		runTest();
		long b = System.currentTimeMillis();
		System.out.println("Do you want to know the mistakes? (y/n)");
		String s = in.next();
		if (s.equals("y"))
			h.mistakes();
		System.out.println("You score: " + Score.score + "(" + c + ")");
		System.out.println("You time: " + (b - a) + " ms");
		stopTest();
	}
}
