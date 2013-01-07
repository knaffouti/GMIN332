package gmin332.database.utils;

public class StatmentInter {
	String s;
	String p;
	String o;

	public StatmentInter(String s, String p, String o) {
		this.s = s;
		this.p = p;
		this.o = o;
	}

	@Override
	public String toString() {
		return "{" + s + "," + p + "," + o + "}";
	}
}
