package gmin332.sdbsesame.request;

import org.openjena.atlas.io.IndentedWriter;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.vocabulary.RDF;

public class RSDB {
	public static final String NL = System.getProperty("line.separator");

	public static ResultSet send(String request) {

		IDBConnection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = new DBConnection("jdbc:mysql://localhost/projet", "root",
					"rabah123", "MySql");
			if (conn != null)
				System.out.println("connexion reussie");

			ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			Model m = null;
			if (conn.containsModel("INSEE2010")) {
				System.out.println("Opening existing model");
				m = maker.openModel("INSEE2010", true);
				String INSEE2010 = m.getNsPrefixURI("project-communes");
				String prolog1 = "PREFIX project-communes: <" + INSEE2010 + ">";
				String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";
				String queryString = prolog1 + NL + prolog2 + NL + request;

				
				Query query = QueryFactory.create(queryString);
				query.serialize(new IndentedWriter(System.out, true));
				System.out.println();
				QueryExecution qexec = QueryExecutionFactory.create(query, m);
				Query query1 = QueryFactory.create(queryString);

				return qexec.execSelect();
				
			}
		} catch (Exception e) {
			System.out.println("Exception");
			System.out.println(e);
		}
		return null;
	}
}