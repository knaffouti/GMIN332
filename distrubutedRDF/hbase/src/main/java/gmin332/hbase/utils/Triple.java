package gmin332.hbase.utils;

public class Triple<T,U,V> {
	T first;
	U second;
	V third;
	
	public Triple(T fst, U snd, V thd) {
		first =fst;
		second = snd;
		third = thd;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public U getSecond() {
		return second;
	}

	public void setSecond(U second) {
		this.second = second;
	}

	public V getThird() {
		return third;
	}

	public void setThird(V third) {
		this.third = third;
	}
}
