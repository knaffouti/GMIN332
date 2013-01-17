package gmin332.sdbsesame.request;

import java.util.ArrayList;

import org.openjena.atlas.io.IndentedWriter;
import org.openjena.atlas.logging.Log;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class RSesame {

	public static void main(String[] args) {
		/*
		 * Repository repository = new
		 * HTTPRepository("http://localhost:8080/openrdf-sesame","GMIN33");
		 * repository.initialize();
		 * 
		 * RepositoryConnection connection = repository.getConnection();
		 * TupleQuery selectQuery = connection.prepareTupleQuery(
		 * QueryLanguage.SPARQL, "select ?codeI ?o  "+
		 * "where{?s <http://www.ourProject/communes.fr#CodeInsee> ?codeI. "+
		 * "?s <http://www.ourProject/communes.fr#PopulationM> ?o.}" ); /*select
		 * ?codeI (<http://www.w3.org/2001/XMLSchema#decimal>(?o) as ?a)
		 * where{?s <http://www.ourProject/communes.fr#CodeInsee> ?codeI. ?s
		 * <http://www.ourProject/communes.fr#PopulationM> ?o} // on l'exécute
		 * TupleQueryResult selectQueryResult = selectQuery.evaluate();
		 * 
		 * // on itère sur les résultats while(selectQueryResult.hasNext()) {
		 * BindingSet aBinding = selectQueryResult.next();
		 * 
		 * System.out.println(aBinding.getValue("codeI")+"------------->"+aBinding
		 * .getValue("o"));//
		 */

		int nbr = 22000;
		String qu = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
				"PREFIX project-communes:<http://www.ourProject/communes.fr#>" +
				"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>" +
				"select ?codeI (xsd:decimal(?o) as ?x)" +
				"where{?s <http://www.ourProject/communes.fr#CodeInsee> ?codeI." +
				"     ?s <http://www.ourProject/communes.fr#PopulationM> ?o." +
				"filter (xsd:decimal(?o)> "+nbr+")}";
		  
		  
		Query query = null;
		try {
			Log.info("rabah", "Entrerd");
			query = QueryFactory.create(qu);
			query.serialize(new IndentedWriter(System.out, true));
		} catch (Exception e) {
			System.out.println("Error");
		}

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://localhost:8080/openrdf-sesame/repositories/GMIN33", query);
		try {
			ResultSet results = qexec.execSelect();
			
			ResultSetFormatter.out(System.out,results,query );
		
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			qexec.close();
		}

	}

}
