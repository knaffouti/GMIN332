package gmin332.database.main;

import gmin332.database.data.Feature;
import gmin332.database.utils.HibernateUtils;

import java.util.List;

import org.hibernate.Session;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.println("rabah");
		Session f = HibernateUtils.getSessionFactory().openSession();
		
		List<Feature> s = Feature.findByLike(f, "bouira");
		
		f.close();
		
		for (Feature feature : s) {
			System.out.println(feature.getName());	
		}//*/
		
		
	}

}
