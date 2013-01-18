package gmin332.database.util;

public class StatmetInter {
	String s;
	String p;
	String o;

	public StatmetInter(String s, String p, String o) {
		this.s = s;
		this.p = p;
		this.o = o;
	}

	@Override
	public String toString() {
		return "{" + s + "," + p + "," + o + "}";
	}
}
