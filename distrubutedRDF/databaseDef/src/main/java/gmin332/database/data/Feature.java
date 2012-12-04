package gmin332.database.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name = "feature")
public class Feature {

	@Id
	long geoNameId;

	String name;
	String countryCode;
	String latitude;
	String longitude;
	String codePostal;

	public long getGeoNameId() {
		return geoNameId;
	}

	public void setGeoNameId(long geoNameId) {
		this.geoNameId = geoNameId;
	}

	public String getName() {
		return name.replace(';', '\'');
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public static Feature findById(Session session, long id) {
		Feature out = (Feature) session.createCriteria(Feature.class)
				.add(Restrictions.eq("geoNameId", id)).uniqueResult();
		return out;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Feature> findByLike (Session connect, String likeit)
	   {
	      String req = "from " + Feature.class.getSimpleName () + " where name like '%" + likeit + "%'";
	      return connect.createQuery (req).list ();
	   }
}
