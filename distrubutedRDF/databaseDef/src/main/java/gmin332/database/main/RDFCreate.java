package gmin332.database.main;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class RDFCreate {
	OntModel base;
	String nsgeo = "http://localhost:2020/geonames#";
	String nsins = "http://localhost:2020/insee#";

	OntClass feature;
	OntClass inseereg;
	OntClass inseecom;
	OntClass inseedept;

	public RDFCreate() {

		base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		base.setNsPrefix("geo",nsgeo);
		base.setNsPrefix("ins",nsins);
		
		createInseeRegion();
		createInseeDept();
		createInseeCom();
		createFeature();
		
		
		
	}

	private void createFeature() {
		feature = base.createClass(nsgeo + "feature");
		addProperty("countryCode", nsgeo, XSD.integer, feature);
		addProperty("deptCode", nsgeo, inseedept, feature);
		addProperty("identifier", nsgeo, XSD.integer, feature);
		addProperty("inseeCode", nsgeo, XSD.integer, feature);
		addProperty("latitude", nsgeo, XSD.decimal, feature);
		addProperty("longitude", nsgeo, XSD.decimal, feature);
		addProperty("name", nsgeo, XSD.xstring, feature);
		addProperty("population", nsgeo, XSD.integer, feature);
	}

	
	private void createInseeCom() {
		inseecom = base.createClass(nsins + "commune");
		addProperty("communeCode", nsins, XSD.integer, inseecom);
		addProperty("communeName", nsins, XSD.xstring, inseecom);
		addProperty("deptCode", nsins, inseedept, inseecom);
		addProperty("inseeCode", nsins, XSD.integer, inseecom);
		addProperty("populationTotal", nsins, XSD.integer, inseecom);
		
	}
	
	private void createInseeDept() {
		inseedept = base.createClass(nsins + "dept");
		addProperty("deptName", nsins, XSD.integer, inseedept);
		addProperty("regionCode", nsins, inseereg, inseedept);
		addProperty("deptCode", nsins, XSD.xstring, inseedept);
		addProperty("nbrCommunes", nsins, XSD.integer, inseedept);
		addProperty("populationTotal", nsins, XSD.integer, inseedept);
		
	
	}
	
	private void createInseeRegion() {
		inseereg = base.createClass(nsins + "region");
		addProperty("regionCode", nsins, XSD.integer, inseereg);
		addProperty("regionName", nsins, XSD.xstring, inseereg);
		addProperty("nbrCommunes", nsins, XSD.integer, inseereg);
		addProperty("populationTotal", nsins, XSD.integer, inseereg);
	}


	
	public OntProperty addProperty(String propertyName, String ns,
			Resource type, OntClass domain) {
		OntProperty property = this.base.createOntProperty(ns + propertyName);
		property.setDomain(domain);
		property.setRange(type);
		property.setLabel(propertyName, "en");
		return property;
	}
	
	public void Sortie() {
		base.write(System.out);
	}
	
	
	public OntModel getBase() {
		return base;
	}

	public String getNsgeo() {
		return nsgeo;
	}

	public String getNsins() {
		return nsins;
	}

	public OntClass getFeature() {
		return feature;
	}

	public OntClass getInseereg() {
		return inseereg;
	}

	public OntClass getInseecom() {
		return inseecom;
	}

	public OntClass getInseedept() {
		return inseedept;
	}

	public static void main(String[] args) {
		RDFCreate r =new RDFCreate();
		System.out.println(r.getBase().listClasses().toList().size());
		r.Sortie();
	}
	
}
