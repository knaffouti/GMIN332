package gmin332.dispatch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gmin332.database.util.RD2rq;
import gmin332.hbase.utils.RHbaseGet;
import gmin332.hbase.utils.Triple;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class Dispatcher {
	public static void main(String[] args) {
		/*String str = "select ?CodeInsee ?NomCom "
				+ "where {?s project-communes:CodeInsee ?CodeInsee ."
				+ "?s project-communes:NomCom ?NomCom .}";//

		ResultSet f =RSDB.Send(str);
		
		ResultSetFormatter.out(System.out, f);//*/
		
		
		/*String str2  =	"select ?codeI (xsd:decimal(?o) as ?x)"
				+ "where{?s <http://www.ourProject/communes.fr#CodeInsee> ?codeI."
				+ "     ?s <http://www.ourProject/communes.fr#PopulationM> ?o."
				+ "filter (xsd:decimal(?o)> 2000)}";
		
		ResultSet f2 =RSesame.send(str2);
		ResultSetFormatter.out(System.out,f2);//*/
		
		/*String str3 = "select ?codeI (xsd:decimal(?o) as ?x)"
				+" where" +
				"{" +
				"?s insee:inseeCode ?codeI." +
				"?s insee:populationTotal ?o." +
				"filter (xsd:decimal(?o)> 22000)" +
				"}";
		
		String str4 = "select ?s ?p ?o"
				+" where" +
				"{" +
				"?s ?p ?o"+
				"}" +
				"limit 10";
		ResultSet f3 = RD2rq.send(str3);
		
		//String s;
		//ResultSetFormatter.out(s,f3);
		//*/
		
		List<Triple<String, String, String>> s =RHbaseGet.getLike("Montpellier", "geo", "feature_name", "feature");
		
		ConvertToMap.show(ConvertToMap.getMapFromList(s, "first", "second", "third"));
		
		
		//ResultSetToList.show(ResultSetToList.getListFromResultSet(f3));
		

	}

}
