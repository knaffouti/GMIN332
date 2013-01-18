package gmin332.hbase.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class HbaseUtils {

	static Configuration hc = HBaseConfiguration.create();
	
	public static HTable htable;
	
	public static void setHtable(String htable) {
		try {
			HbaseUtils.htable = new HTable(hc, htable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create table
	 * 
	 * @param name
	 * @param family
	 */
	public static void createtable(String name, String... family) {
		List<String> ls = new ArrayList<String>();
		for (String string : family) {
			ls.add(string);
		}
		createtable(name, ls);
	}

	/**
	 * Create table
	 * 
	 * @param name
	 * @param family
	 */
	public static void createtable(String name, List<String> family) {
		HTableDescriptor ht = new HTableDescriptor(name);
		
		for (String fam : family) {
			ht.addFamily(new HColumnDescriptor(fam));
		}
		try {
			HBaseAdmin hba = new HBaseAdmin(hc);
			hba.createTable(ht);

		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Inserer une ligne
	 * 
	 * @param table
	 * @param raw
	 * @param family
	 * @param qualifier
	 * @param value
	 */
	public static void putRaw( String raw, String family,
			String qualifier, String value) {
		Put put1 = new Put(new String(raw).getBytes());
		put1.add(new String(family).getBytes(), new String(qualifier).getBytes(), new String(value).getBytes());

		try {
			htable.put(put1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	public static void main(String args[]) throws IOException {

		System.out.println("starting...");

		// createtable("patient2", "allergy","iG");

		Put pierrePut = new Put(new String("P_M_001").getBytes());
		pierrePut.add(new String("iG2").getBytes(),
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

		HTable table = new HTable(hc, "patient2");
		System.out.println("add patient's data");
		table.put(pierrePut);
		table.put(mariePut);
		table.put(paulPut);
		System.out.println("done!");

	}

}