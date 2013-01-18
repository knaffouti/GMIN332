package gmin332.database.ins;

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
@Table(name = "inseecom")
public class Commune {

	@Id
	String inseeCode;

	@ManyToOne
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "deptCode")
	Departement deptCode;

	int communeCode;

	
	String communeName;

	int populationTotal;

	
	public static Commune findById(Session session, String inseeCode) {
		Commune out = (Commune) session.createCriteria(Commune.class)
				.add(Restrictions.eq("inseeCode", inseeCode)).uniqueResult();
		return out;
	}
	
	@Override
	public String toString() {
		return inseeCode+" : "+communeName+" "+communeCode+" "+populationTotal;
	}

	public String getInseeCode() {
		return inseeCode;
	}

	public Departement getDeptCode() {
		return deptCode;
	}

	public int getCommuneCode() {
		return communeCode;
	}

	public String getCommuneName() {
		return communeName;
	}

	public int getPopulationTotal() {
		return populationTotal;
	}
}
