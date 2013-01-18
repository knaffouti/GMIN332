package gmin332.database.ins;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name = "inseereg")
public class Region {

	@Id
	int regionCode;
	String regionName;
	int nbrCommunes;
	int populationTotale;
	
	public static Region findById(Session session, int regioncode) {
		Region out = (Region) session
				.createCriteria(Region.class)
				.add(Restrictions.eq("regionCode", regioncode)).uniqueResult();
		return out;
	}

	public int getRegionCode() {
		return regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public int getNbrCommunes() {
		return nbrCommunes;
	}

	public int getPopulationTotale() {
		return populationTotale;
	}
	
	@Override
	public String toString() {
		return regionCode+": "+ regionName+" "+nbrCommunes+" "+populationTotale;
	}
}
