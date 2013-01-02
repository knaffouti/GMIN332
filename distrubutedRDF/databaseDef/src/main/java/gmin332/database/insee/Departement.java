package gmin332.database.insee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name = "inseedept")
public class Departement {

	@Id
	String deptCode;

	String deptName;
	int nbrCommunes;
	int populationTotal;

	@ManyToOne
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "regionCode")
	Region regionCode;

	public static Departement findById(Session session, String deptCode) {
		Departement out = (Departement) session
				.createCriteria(Departement.class)
				.add(Restrictions.eq("deptCode", deptCode)).uniqueResult();
		return out;
	}
	
	@Override
	public String toString() {
		return deptCode+" : "+deptName+" "+nbrCommunes+" "+populationTotal;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public int getNbrCommunes() {
		return nbrCommunes;
	}

	public int getPopulationTotal() {
		return populationTotal;
	}

	public Region getRegionCode() {
		return regionCode;
	}
}
