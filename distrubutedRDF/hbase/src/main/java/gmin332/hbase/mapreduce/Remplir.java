package gmin332.hbase.mapreduce;
import java.util.Random;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
* writes random access logs into hbase table
*
*hbase shell
*	  	create 'access_logs', 'details'
*	  	create 'summary_user', {NAME=>'details', VERSIONS=>1}
*	  	
* 'access_logs' is the 'raw' logs.  The key is userID+counter  (int + int)
* 'summary_user' is to compute summary.  key is 'userID' (int)
* userID_count => {
* details => {
* page
* }
* }
*
* @author sujee ==at== sujee.net
*
*/
public class Remplir {

    public static void main(String[] args) throws Exception {
        
        String [] pages = {"/", "/a.html", "/b.html", "/c.html"};
        
         
        Configuration hbaseConfig = HBaseConfiguration.create();
        HTable htable = new HTable(hbaseConfig, "access_logs");
        htable.setAutoFlush(false);
        htable.setWriteBufferSize(1024 * 1024 * 12);
        
        int totalRecords = 1000;
        int maxID = totalRecords / 10;
        Random rand = new Random();
        System.out.println("importing " + totalRecords + " records ....");
        for (int i=0; i < totalRecords; i++)
        {
            int userID = rand.nextInt(maxID) + 1;
            byte [] rowkey = Bytes.add(Bytes.toBytes(userID), Bytes.toBytes(i));
            String randomPage = pages[rand.nextInt(pages.length)];
            Put put = new Put(rowkey);
            System.out.println("cle bytes et int "+Bytes.toInt(rowkey));
            put.add(Bytes.toBytes("details"), Bytes.toBytes("page"), Bytes.toBytes(randomPage));
            htable.put(put);
        }
        htable.flushCommits();
        htable.close();
        System.out.println("done");
    }
}