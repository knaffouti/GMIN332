package gmin332.hbase.utils;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Bytes.ByteArrayComparator;


public class NumberCompartor extends ByteArrayComparator implements Comparable<byte[]> {

	float value;

	public NumberCompartor(float value) {
		this.value = value;
	}

	@Override
	public int compareTo(byte[] o) {
		String x = Bytes.toString(o);
		
		float in = Float.parseFloat(x);
		System.out.println(in);
		return (int) value - (int) in;
	}

	
}
