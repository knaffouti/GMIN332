package gmin332.sdbsesame.request;

import org.openjena.atlas.io.IndentedWriter;
import org.openjena.atlas.logging.Log;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class RSesame {
	public static final String NL = System.getProperty("line.separator");

	static String pr1 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
	static String pr2 = "PREFIX project-communes:<http://www.ourProject/communes.fr#>";
	static String pr3 = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>";

	public static ResultSet send(String request) {

		String queryString = pr1 + NL + pr2 + NL + pr3 + NL + request;

		Query query = null;
		try {
			Log.info("rabah", "Entrerd");
			query = QueryFactory.create(queryString);
			query.serialize(new IndentedWriter(System.out, true));
		} catch (Exception e) {
			System.out.println("Error");
		}

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://localhost:8080/openrdf-sesame/repositories/GMIN33",
				query);
		try {

			return qexec.execSelect();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

}
