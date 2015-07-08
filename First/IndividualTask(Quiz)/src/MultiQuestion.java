public class MultiQuestion implements Question {
	private String Question;
	private String Answer;
	private String[] Var;

	MultiQuestion(String q, String a, String[] v) {
		Question = q;
		Answer = a;
		Var = v;
	}

	public String getQuestion() {
		return Question;
	}

	public String getAnswer() {
		return Answer;
	}

	public boolean getResult(String s) {
		return (s.equals(getAnswer())) ? true : false;
	}

	public String getVar(int i) {
		assert (i >= 0 & i < Var.length);
		return Var[i];
	}

	public void printQuestion() {
		System.out.println(getQuestion());
		for (int i = 0; i < Var.length; i++)
			System.out.println(getVar(i));
	}

}
