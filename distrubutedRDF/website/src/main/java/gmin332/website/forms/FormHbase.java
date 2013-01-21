package gmin332.website.forms;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.beaneditor.Validate;

import gmin332.hbase.utils.FamilyQualfierType;

public class FormHbase {
	
	@Persist
	@Validate("required")
	FamilyQualfierType type;

	@Persist
	@Validate("required")
	String requete;

	public String getRequete() {
		return requete;
	}

	public FamilyQualfierType getType() {
		return type;
	}

	public void setRequete(String requete) {
		this.requete = requete;
	}

	public void setType(FamilyQualfierType type) {
		this.type = type;
	}

}
