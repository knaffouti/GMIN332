package gmin332.entities.main;

import gmin332.entities.data.Country;
import gmin332.entities.utils.HibernateUtils;

import org.hibernate.Session;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("rabah");
		Session f = HibernateUtils.getSessionFactory().openSession();
		f.beginTransaction();

		
		Country s = new Country();
		s.setGeonameId(1);
		s.setContinentName("Algeria");
		s.setIsoAlpha3("DZ");
		
		
		f.save(s);
		
		f.getTransaction().commit();
		f.close();
	}

}
