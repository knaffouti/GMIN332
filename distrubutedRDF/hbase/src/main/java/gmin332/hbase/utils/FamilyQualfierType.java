package gmin332.hbase.utils;

public enum FamilyQualfierType {
	geo$feature_countryCode, 
	geo$feature_identifier, 
	geo$feature_latitude, 
	geo$feature_longitude, 
	geo$feature_name, 
	geo$feature_population, 
	rdf$type, 
	rdfs$label;
	
	public String getValue(){
		return this.name().replace("$",":");
	}
}
