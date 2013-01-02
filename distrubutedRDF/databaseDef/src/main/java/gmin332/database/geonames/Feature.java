package gmin332.database.geonames;

import gmin332.database.insee.Commune;
import gmin332.database.insee.Departement;

import java.util.List;

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
@Table(name = "feature")
public class Feature {
	@Id
	long identifier;

	String name;
	String countryCode;
	double latitude;
	double longitude;
	
	@ManyToOne
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "inseeCode")
	Commune inseeCode;
	
	@ManyToOne
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "DeptCode")
	Departement DeptCode;
	int population;

	public static Feature findById(Session session, long id) {
		Feature out = (Feature) session.createCriteria(Feature.class)
				.add(Restrictions.eq("identifier", id)).uniqueResult();
		return out;
	}

	@SuppressWarnings("unchecked")
	public static List<Feature> findByLike(Session connect, String likeit) {
		String req = "from " + Feature.class.getSimpleName()
				+ " where name like '%" + likeit + "%'";
		return connect.createQuery(req).list();
	}

	public long getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Commune getInseeCode() {
		return inseeCode;
	}

	public Departement getDeptCode() {
		return DeptCode;
	}

	public int getPopulation() {
		return population;
	}
}
