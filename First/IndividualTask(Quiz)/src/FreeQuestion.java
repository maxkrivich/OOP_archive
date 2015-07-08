public class FreeQuestion implements Question {
	private String Question;
	private String Answer;

	FreeQuestion(String q, String a) {
		Question = q;
		Answer = a;
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

	public void printQuestion() {
		System.out.println(getQuestion());
	}

}
