package gmin332.hbase.other;

import org.apache.hadoop.hbase.filter.WritableByteArrayComparable;
import org.apache.hadoop.hbase.util.Bytes;

public class NumberCompartor extends WritableByteArrayComparable {

	public NumberCompartor() {
		super();
		
	}

	public NumberCompartor(byte[] value) {
		super(value);
	}

	public static void main(String[] args) {
		NumberCompartor f = new NumberCompartor(Bytes.toBytes("2000"));
		System.out.println(f.compareTo(Bytes.toBytes("3000"), 0, 0));
	}

	@Override
	public int compareTo(byte[] value, int offset, int length) {

		String our = Bytes.toString(this.getValue());
		String their = Bytes.toString(value);
		System.out.println(their);

		double ourd = Double.parseDouble(our);
		double theird = Double.parseDouble(their);

		int result = (int) (ourd - theird);
		return result;
	}

}
