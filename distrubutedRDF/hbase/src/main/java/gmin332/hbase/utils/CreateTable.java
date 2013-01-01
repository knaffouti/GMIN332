package gmin332.hbase.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class CreateTable {

	public static void main(String args[]) throws IOException {

		System.out.println("starting...");

		System.out.println("getting config...");
		Configuration hc = HBaseConfiguration.create();

		
		HTableDescriptor ht = new HTableDescriptor("patient");
		// column family
		ht.addFamily(new HColumnDescriptor("iG"));

		ht.addFamily(new HColumnDescriptor("allergy"));

		Put pierrePut = new Put(new String("P_M_001").getBytes());
		pierrePut.add(new String("iG").getBytes(),
				new String("name").getBytes(),
				new String("Pierre Martin").getBytes());
		pierrePut.add(new String("iG").getBytes(),
				new String("age").getBytes(), new String("25").getBytes());
		pierrePut.add(new String("allergy").getBytes(),
				new String("sneezing").getBytes(),
				new String("mild").getBytes());

		Put mariePut = new Put(new String("M_M_001").getBytes());
		mariePut.add(new String("iG").getBytes(),
				new String("name").getBytes(),
				new String("Marie Martin").getBytes());
		mariePut.add(new String("iG").getBytes(), new String("age").getBytes(),
				new String("26").getBytes());
		mariePut.add(new String("allergy").getBytes(),
				new String("itchyNose").getBytes(),
				new String("severe").getBytes());

		Put paulPut = new Put(new String("P_P_001").getBytes());
		paulPut.add(new String("iG").getBytes(), new String("name").getBytes(),
				new String("Paul Passage").getBytes());
		paulPut.add(new String("iG").getBytes(), new String("age").getBytes(),
				new String("36").getBytes());
		paulPut.add(new String("allergy").getBytes(),
				new String("wateryEyes").getBytes(),
				new String("severe").getBytes());
		paulPut.add(new String("allergy").getBytes(),
				new String("itchyNose").getBytes(),
				new String("severe").getBytes());
		paulPut.add(new String("allergy").getBytes(),
				new String("snuffyNose").getBytes(),
				new String("severe").getBytes());

		System.out.println("connecting...");
		HBaseAdmin hba = new HBaseAdmin(hc);
		
		System.out.println("creating table...patient ");
		hba.createTable(ht);
		HTable table = new HTable(hc, "patient");
		System.out.println("add patient's data");
		table.put(pierrePut);
		table.put(mariePut);
		table.put(paulPut);
		System.out.println("done!");

	}

}