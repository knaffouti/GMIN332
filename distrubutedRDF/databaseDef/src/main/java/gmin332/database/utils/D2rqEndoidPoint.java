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
import com.hp.hpl.jena.rdf.model.Resource;

public class D2rqEndoidPoint {

	public static final String DB = "db:";
	public static final String RDFS = "rdfs:";
	public static final String OWL = "owl:";
	public static final String MAP = "map:";
	public static final String XSD = "xsd:";
	public static final String RDF = "rdf:";
	public static final String VOCAB = "vocab:";

	public static List<Resource> executeSPARQLToD2rq(String s, String p,
			String o,int limit) {
		Query query = null;
		try {
			Log.info("rabah", "Entrerd");
			query = QueryFactory.create(createQueryForD2rq(s, p, o,limit));
			query.serialize(new IndentedWriter(System.out, true));
		} catch (Exception e) {
			return null;
		}
		ArrayList<Resource> res = new ArrayList<Resource>();

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://localhost:2020/sparql", query);
		try {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution rb = results.nextSolution();
				res.add(rb.getResource("s"));
			}
		} catch (Exception e) {
			System.out.println("Impossible to find Resource for " + o + " !");
			return null;
		} finally {
			qexec.close();
		}
		return res;
	}

	public static String createQueryForD2rq(String s, String p, String o, int limit) {

		
		String result = "PREFIX db: <http://localhost:2020/resource/>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX map: <http://localhost:2020/resource/#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX vocab: <http://localhost:2020/resource/vocab/>\n"
				+ "SELECT DISTINCT * WHERE {\n" 
				+ (s == null ? "?s" : s) +" " 
				+ (p == null ? "?p" : p) +" "
				+ (o == null ? "?o" : o) +" "
				+ "} LIMIT "+limit;

		return result;
	}
}
