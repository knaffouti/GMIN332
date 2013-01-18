package gmin332.dispatch.main;

import gmin332.database.util.RD2rq;
import gmin332.sdbsesame.request.RSesame;

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
		
		String str3 = "select ?codeI (xsd:decimal(?o) as ?x)"
				+" where" +
				"{" +
				"?s insee:inseeCode ?codeI." +
				"?s insee:populationTotal ?o." +
				"filter (xsd:decimal(?o)> 22000)" +
				"}";
		ResultSet f3 = RD2rq.send(str3);
		ResultSetFormatter.out(System.out,f3);
		

	}

}
