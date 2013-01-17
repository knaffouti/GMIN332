package gmin332.sdb.fill;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.db.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
public class INSEE2008 {
	
	public static final String rdf_file = "jeuxDonnees2.rdf";

	public static void main(String[]args){
		
		IDBConnection  conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=new DBConnection("jdbc:mysql://localhost/projet", "root","rabah123","Mysql");
			if (conn != null)
				System.out.println("connexion reussie");
			
			ModelMaker maker = ModelFactory.createModelRDBMaker(conn) ;
			Model tmp = null;
			if (conn.containsModel("INSEE2008")){ 
				System.out.println("Opening existing model");
				tmp = maker.openModel("INSEE2008",true); }
				else {
					System.out.println("Creating new model");
					tmp = maker.createModel("INSEE2008",true);
				}
				FileManager.get().readModel( tmp, rdf_file );
				String the = tmp.getNsPrefixURI("thesaurus" );
				Resource ds = tmp.getResource( the + "Descripteur" );
				ResIterator res_i = tmp.listSubjectsWithProperty( RDF.type, ds );
				while (res_i.hasNext()){ 
					Resource s = res_i.nextResource();
					System.out.println(s.getLocalName());
				}
			
			System.out.println("Contenu "+tmp.size());
			//tmp.write(System.out, "RDF/XML");
			//maker.removeModel("INSEE2010");
			// Close the database connection
			//conn.cleanDB();
			conn.close();
		} 
		catch  (Exception e) {
	        System.out.println("Exception");
	        System.out.println(e);
		}
	  }
}


