import java.util.Vector;

public class Handler {
	private Record[] r;
	private int size = 0;
	private Vector<Question> v;

	Handler(int l) {
		r = new Record[l];
		v = new Vector<Question>(l);
	}

	void adds(String s, Question q) {
		assert (size < r.length);
		r[size]=new Record(s, q.getResult(s));
		v.add(q);
		if (r[size].getUserBoolean() == true)
			Score.score++;
		size++;
	}

	void mistakes() {
		System.out.println("----------------------------------------------------");
		System.out.println("You mistakes:");
		for (int i = 0; i < size; i++) {
			if (r[i].getUserBoolean() == false) {
				System.out.println((i + 1) + ") " + (v.get(i)).getQuestion());
				System.out.println("Currect: " + (v.get(i)).getAnswer());
				System.out.println("You: " + r[i].getUserAnswer());
				System.out.println();
			}
		}
		System.out.println("----------------------------------------------------");
	}

	void clear() {
		r = new Record[0];
		v.clear();
		size = 0;
	}

}
