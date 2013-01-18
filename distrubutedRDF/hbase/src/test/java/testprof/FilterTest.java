package testprof;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
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
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;


public class FilterTest {

  public static void main(String[] args) throws IOException {
    Configuration conf = HBaseConfiguration.create();

    HTable table = new HTable(conf, "patient");

    List<Filter> filters = new ArrayList<Filter>();

    Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
              new BinaryComparator(Bytes.toBytes("iG")));
    filters.add(famFilter);

    Filter colFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
      new BinaryComparator(Bytes.toBytes("age")));

    filters.add(colFilter);

    Filter valFilter = new ValueFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL,
              new BinaryComparator(Bytes.toBytes("26")));

    filters.add(valFilter);

    FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);


    Scan scan = new Scan();
    scan.setFilter(fl);
    ResultScanner scanner = table.getScanner(scan);
    System.out.println("Scanning table... ");
    for (Result result : scanner) {
       
        for (KeyValue kv : result.raw()) {
            System.out.println("kv:"+kv +", Key: " + Bytes.toString(kv.getRow())  + ", Value: " +Bytes.toString(kv.getValue()));
        }
    }   

    scanner.close();
    System.out.println("Completed ");
  }
}