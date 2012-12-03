package gmin332.database.main;

import gmin332.database.utils.HibernateUtils;

import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FeatureSearch {

	String username;
	String language;
	int maxRows;

	public FeatureSearch() {
		language = "en";
		username = "demo";
		maxRows = 10;
	}

	public FeatureSearch(String user, String lang, int maxrows) {
		language = lang;
		username = user;
		maxRows = maxrows;
	}

	public void search(String query) {
		WebService.setUserName(username);

		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(query);
		searchCriteria.setStyle(Style.LONG);
		searchCriteria.setMaxRows(maxRows);
		searchCriteria.setLanguage(language);

		Gson gson = new GsonBuilder().create();

		ToponymSearchResult searchResult = null;
		try {
			searchResult = WebService.search(searchCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		  Session session =HibernateUtils.getSessionFactory().openSession();
		  session.beginTransaction();//
		 
		for (Toponym toponym : searchResult.getToponyms()) {
			String res = gson.toJson(toponym, Toponym.class);
			System.out.println(res);
			SQLQuery q = session.createSQLQuery("insert into feature values ('"+toponym.getGeoNameId()+"','"+
			toponym.getName()+"','"+toponym.getCountryCode()+"','"+toponym.getLatitude()+"','"
					+toponym.getLongitude()+"')"); 
			q.executeUpdate();
		}

		
		 session.getTransaction().commit(); 
		 session.close();//
		

	}

	public static void main(String[] args) throws Exception {
		FeatureSearch ff = new FeatureSearch("bibouh123", "fr", 5);
		ff.search("paris");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

}
