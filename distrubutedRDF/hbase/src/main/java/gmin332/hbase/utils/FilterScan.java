package gmin332.hbase.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class FilterScan {

	public static void main(String[] args) throws IOException {
		Configuration conf = HBaseConfiguration.create();

		HTable table = new HTable(conf, "feature");

		List<Filter> filters = new ArrayList<Filter>();

		Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(Bytes.toBytes("geo")));
		filters.add(famFilter);

		Filter colFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(Bytes.toBytes("feature_name")));

		
		filters.add(colFilter);


		RegexStringComparator comp = new RegexStringComparator("Montpellier");   // any value that starts with 'my'

		
		
		
		
		Filter valFilter = new ValueFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL,
	              comp);
		

		filters.add(valFilter);//*/

		
		
		
		Get get = new Get(Bytes.toBytes("8292066"));
		get.setMaxVersions(3);  // will return last 3 versions of row
		Result r = table.get(get);
		byte[] b = r.getValue(Bytes.toBytes("geo"), Bytes.toBytes("attr"));  // returns current version of value
		
		for (KeyValue kv : r.raw()) {
			System.out.println(Bytes.toString(kv.getRow()) + ","
					+ Bytes.toString(kv.getValue()));
		}
		
		
		FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL,
				filters);

		Scan scan = new Scan();
		scan.setFilter(fl);

		ResultScanner scanner = table.getScanner(scan);
		System.out.println("Scanning table... " + scanner);
		for (Result result : scanner) {
			System.out.println("REsultat ");
			for (KeyValue kv : result.raw()) {
				System.out.println(Bytes.toString(kv.getRow()) + ","
						+ Bytes.toString(kv.getValue()));
			}
		}

		scanner.close();
		System.out.println("Completed ");
	}
}