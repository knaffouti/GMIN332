package gmin332.hbase.generator;

import gmin332.hbase.utils.HbaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

public class Generator {
	OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	Map<String, String> namespaces;
	String nsbase;
	String hbasetablename;

	/**
	 * Create a generator of rdf on hbase
	 * 
	 * @param filename
	 *            file of rdf brut data
	 * @param nsbase
	 *            the base of xml:base
	 * @param tablename
	 *            the name of table name
	 */
	public Generator(String filename, String tablename, String nsbase) {
		FileManager.get().readModel(m, filename);
		namespaces = m.getNsPrefixMap();
		this.nsbase = nsbase;
		this.hbasetablename = tablename;

	}

	/**
	 * create table
	 */
	public void generateTableHbase() {
		Set<String> ls = namespaces.keySet();
		List<String> fl = new ArrayList<String>();
		for (String string : ls) {
			fl.add(string);
		}
		HbaseUtils.createtable(hbasetablename, fl);
	}

	/**
	 * Filter by name space
	 * 
	 * @param str
	 * @return
	 */
	public String filterNS(String str) {
		String ns = "";
		String value = "";

		if (str.split("#").length == 2) {
			ns = str.split("#")[0];
			value = str.split("#")[1];

			if (ns.equals(nsbase))
				return value;
			else {
				for (Entry<String, String> entry : namespaces.entrySet()) {
					if (entry.getValue().equals(ns + "#"))
						return entry.getKey() + ":" + value;
				}
			}
		}

		return str;
	}

	/**
	 * Excute the parser
	 */
	public void parser() {
		List<Statement> list = m.listStatements().toList();
		int size = list.size();
		int i = 0;
		HbaseUtils.setHtable(hbasetablename);
		for (Statement statement : list) {
			Triple ele = statement.asTriple();
			String raw = filterNS(ele.getSubject().toString());
			String family = filterNS(ele.getPredicate().toString()).split(":")[0];
			String qualifier = filterNS(ele.getPredicate().toString()).split(
					":")[1];
			String value = filterNS(ele.getObject().toString());

			System.out.println("Insert into '" + hbasetablename + "' (" + raw
					+ "," + family + ":" + qualifier + "," + value + ") "
					+ (i++) + "/" + size);
			HbaseUtils.putRaw(raw, family, qualifier, value);

		}
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Generator f = new Generator("all1.rdf", "geodata",
				"http://localhost:2020/raw");
		f.generateTableHbase();
		f.parser();

	}
}
