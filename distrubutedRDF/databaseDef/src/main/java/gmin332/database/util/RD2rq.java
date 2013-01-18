package gmin332.database.util;

import java.util.ArrayList;
import java.util.List;

import org.openjena.atlas.io.IndentedWriter;
import org.openjena.atlas.logging.Log;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class RD2rq {

	public static final String NL = System.getProperty("line.separator");

	public static final String GEO = "geo:";
	public static final String INS = "insee:";

	public static final String RDFS = "rdfs:";
	public static final String OWL = "owl:";
	public static final String XSD = "xsd:";
	public static final String RDF = "rdf:";

	static String pr1 = "PREFIX geo: <http://localhost:2020/geonames#>";
	static String pr2 = " PREFIX insee: <http://localhost:2020/insee#>";
	static String pr3 = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
	static String pr4 = " PREFIX owl: <http://www.w3.org/2002/07/owl#>";
	static String pr5 = " PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
	static String pr6 = " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

	public static List<StatmetInter> executeSPARQLToD2rq(String s, String p,
			String o, int limit) {
		Query query = null;
		try {
			Log.info("rabah", "Entrerd");
			query = QueryFactory.create(createQueryForD2rq(s, p, o, limit));
			query.serialize(new IndentedWriter(System.out, true));
		} catch (Exception e) {
			return null;
		}
		ArrayList<StatmetInter> res = new ArrayList<StatmetInter>();

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://localhost:2020/sparql", query);
		try {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution rb = results.nextSolution();

				res.add(new StatmetInter((s == null ? rb.getResource("s")
						.toString() : s), (p == null ? rb.getResource("p")
						.toString() : p), (o == null ? rb.getResource("o")
						.toString() : o)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			qexec.close();
		}
		return res;
	}

	public static String createQueryForD2rq(String s, String p, String o,
			int limit) {
		String result = pr1 + NL + pr2 + NL  + pr5 + NL
				+ pr6 + NL + "SELECT DISTINCT * WHERE {\n"
				+ (s == null ? "?s" : s) + " " + (p == null ? "?p" : p) + " "
				+ (o == null ? "?o" : o) + " " + "} LIMIT " + limit;
		return result;
	}

	
	public static ResultSet send(String request) {

		String queryString =  pr1 + NL + pr2 + NL + pr3 + NL + pr4 + NL + pr5 + NL
				+ request;

		Query query = null;
		try {
			Log.info("rabah", "Entrerd");
			query = QueryFactory.create(queryString);
			query.serialize(new IndentedWriter(System.out, true));
		} catch (Exception e) {
			System.out.println("Error");
		}

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://localhost:2020/sparql",
				query);
		try {

			return qexec.execSelect();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
}
