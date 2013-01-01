package gmin332.hbase.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class HBaseConnector {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Configuration hc = HBaseConfiguration.create();
		HTableDescriptor ht = new HTableDescriptor("patient");
		ht.addFamily(new HColumnDescriptor("allergy"));
		Put pierrePut = new Put(new String("P_M_001").getBytes());
		pierrePut.add(new String("allergy").getBytes(),
				new String("sneezing").getBytes(),
				new String("mild").getBytes());

		HBaseAdmin hba = new HBaseAdmin(hc);
		System.out.println("creating table...patient ");
		hba.createTable(ht);
		HTable table = new HTable(hc, "patient");
		System.out.println("creating row...Pierre ");
		table.put(pierrePut);

	}
}