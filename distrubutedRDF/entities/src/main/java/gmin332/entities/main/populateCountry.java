package gmin332.entities.main;

import gmin332.entities.data.Country;
import gmin332.entities.utils.ApiCaller;
import gmin332.entities.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class populateCountry {

	public static void main(String[] args) throws JSONException {

		String res = ApiCaller
				.cUrl(ApiCaller
						.getUrlFromString("http://api.geonames.org/countryInfoJSON?lang=fr&username=demo&style=full"));
		System.out.println(res);

		List<Country> pays = new ArrayList<Country>();
		JSONObject obj = new JSONObject(res);
		if (obj != null) {
			JSONArray contries = obj.getJSONArray("geonames");

			Gson gson = new Gson();

			for (int i = 0; i < contries.length(); i++) {
				pays.add(gson.fromJson(contries.get(i).toString(),
						Country.class));
			}
		}

		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		for (Country country : pays) {
			System.out.println(country.getCountryName());
			session.saveOrUpdate(country);
		}
		session.getTransaction().commit();
		session.close();

	}
}
