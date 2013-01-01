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
	double latitude;
	double longitude;
	int inseeCode;
	int DeptCode;
	int population;
	
	
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
