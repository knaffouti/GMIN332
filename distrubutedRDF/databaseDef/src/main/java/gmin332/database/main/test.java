package gmin332.database.main;

import gmin332.database.geonames.Feature;
import gmin332.database.insee.Commune;
import gmin332.database.insee.Departement;
import gmin332.database.insee.Region;
import gmin332.database.utils.D2rqEndoidPoint;
import gmin332.database.utils.HibernateUtils;
import gmin332.database.utils.StatmentInter;

import java.util.List;

import org.hibernate.Session;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<StatmentInter> fs = D2rqEndoidPoint.executeSPARQLToD2rq("<http://localhost:2020/resource/inseecom/971101>",
				null, null, 10);

		for (StatmentInter resource : fs) {
			System.out.println(resource);
		}

	}

	public void testHibernate() {
		System.out.println("rabah");
		Session f = HibernateUtils.getSessionFactory().openSession();
		Region r = Region.findById(f, 91);
		Departement p = Departement.findById(f, "34");
		Commune c = Commune.findById(f, "34172");
		List<Feature> fs = Feature.findByLike(f, "paris");

		f.close();

		System.out.println(r);
		System.out.println(p + " " + p.getRegionCode());
		System.out.println(c + " " + c.getDeptCode() + " "
				+ c.getDeptCode().getRegionCode());

		for (Feature feature : fs) {
			System.out.println(feature.getName() + " "
					+ feature.getPopulation());
		}// */

	}

}
