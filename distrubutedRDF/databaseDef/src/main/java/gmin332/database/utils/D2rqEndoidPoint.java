package gmin332.database.utils;

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


public class D2rqEndoidPoint {

	public static final String GEO = "geo:";
	public static final String INS = "ins:";

	public static final String RDFS = "rdfs:";
	public static final String OWL = "owl:";
	public static final String XSD = "xsd:";
	public static final String RDF = "rdf:";

	public static List<StatmentInter> executeSPARQLToD2rq(String s, String p,
			String o, int limit) {
		Query query = null;
		try {
			Log.info("rabah", "Entrerd");
			query = QueryFactory.create(createQueryForD2rq(s, p, o, limit));
			query.serialize(new IndentedWriter(System.out, true));
		} catch (Exception e) {
			return null;
		}
		ArrayList<StatmentInter> res = new ArrayList<StatmentInter>();

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://localhost:2020/sparql", query);
		try {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution rb = results.nextSolution();

				res.add(new StatmentInter((s == null ? rb.getResource("s")
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
		String result = "PREFIX geo: <http://localhost:2020/geonames#>"
				+ "PREFIX ins: <http://localhost:2020/insee#>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "SELECT DISTINCT * WHERE {\n" + (s == null ? "?s" : s) + " "
				+ (p == null ? "?p" : p) + " " + (o == null ? "?o" : o) + " "
				+ "} LIMIT " + limit;
		return result;
	}
}
