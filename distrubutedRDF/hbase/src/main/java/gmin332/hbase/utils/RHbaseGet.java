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

import com.hp.hpl.jena.sdb.util.Pair;

public class RHbaseGet {

	@SuppressWarnings("resource")
	public static List<Triple<String,String, String>> getLike(String search,
			String ColumnFamily, String qualifier, String tablename) {
		List<Triple<String, String,String>> lst = new ArrayList<Triple<String, String,String>>();

		// configuration get
		Configuration conf = HBaseConfiguration.create();

		// table
		HTable table;
		try {

			table = new HTable(conf, tablename);
			List<Filter> filters = new ArrayList<Filter>();
			Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
					new BinaryComparator(Bytes.toBytes(ColumnFamily)));
			filters.add(famFilter);
			Filter colFilter = new QualifierFilter(
					CompareFilter.CompareOp.EQUAL, new BinaryComparator(
							Bytes.toBytes(qualifier)));
			filters.add(colFilter);
			RegexStringComparator comp = new RegexStringComparator(search);
			Filter valFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL,
					comp);

			filters.add(valFilter);// */

			FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL,
					filters);

			Scan scan = new Scan();
			scan.setFilter(fl);

			ResultScanner scanner = table.getScanner(scan);
			for (Result result : scanner) {
				for (KeyValue kv : result.raw()) {
					lst.add(new Triple<String, String,String>(
							Bytes.toString(kv.getRow()),Bytes.toString(kv.getFamily())+":"+Bytes.toString(kv.getQualifier()), Bytes.toString(kv
									.getValue())));
				}
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lst;

	}

	@SuppressWarnings("resource")
	public static List<Triple<String, String,String>> getRaw(String raw, String tablename) {
		List<Triple<String, String,String>> lst = new ArrayList<Triple<String, String,String>>();

		// configuration get
		Configuration conf = HBaseConfiguration.create();

		// table
		HTable table;
		try {

			table = new HTable(conf, tablename);
			Get get = new Get(Bytes.toBytes(raw));
			Result r = table.get(get);
			
			for (KeyValue kv : r.raw()) {
				lst.add(new Triple<String, String,String>(
						Bytes.toString(kv.getRow()),Bytes.toString(kv.getFamily())+":"+Bytes.toString(kv.getQualifier()), Bytes.toString(kv
								.getValue())));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lst;

	}

	public static void main(String[] args) throws IOException {
		//List<Triple<String,String, String>> f = getRaw("2992166", "feature");
		/*List<Triple<String,String, String>> f = getLike("Montpellier","geo","feature_name", "feature");

		for (Triple<String,String, String> pair : f) {
			System.out.println(pair.getFirst() + " " + pair.getSecond()+" "+pair.getThird());
		}//*/
		
		FamilyQualfierType r = FamilyQualfierType.geo$feature_countryCode;
		
		System.out.println(r.getValue());

	}
}