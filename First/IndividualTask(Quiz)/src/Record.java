public class Record {
	private String user;
	private boolean userBol;

	public Record(String s, boolean b) {
		user=s;
		userBol=b;
	}

	public String getUserAnswer() {
		return user;
	}

	public boolean getUserBoolean() {
		return userBol;
	}

}
