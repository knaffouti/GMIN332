package gmin332.website.pages;

import gmin332.database.util.RD2rq;
import gmin332.dispatch.main.ConvertToMap;
import gmin332.hbase.utils.FamilyQualfierType;
import gmin332.hbase.utils.RHbaseGet;
import gmin332.sdbsesame.request.RSDB;
import gmin332.sdbsesame.request.RSesame;

import java.util.ArrayList;
import java.util.Map;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import com.hp.hpl.jena.query.ResultSet;

/**
 * Start page of application website.
 */
public class Index {

	@Property
	@Persist
	int currentTab ;

	
	@Property
	@Persist
	String ReqSDB;

	public Object onSubmitFromFormSDB() {
		System.out.println("I am HERE " + ReqSDB);
		if (ReqSDB != null) {
			ResultSet f3 = RSDB.send(ReqSDB);
			results = ConvertToMap.getListFromResultSet(f3);//

			if (sujets != null)
				sujets = null;

			for (String ele : results.keySet()) {
				if (sujets == null)
					sujets = results.get(ele);
			}
		}
		return this;

	}

	@Property
	@Persist
	String ReqSESAME;

	public Object onSubmitFromFormSESAME() {
		System.out.println("I am HERE " + ReqSESAME);
		if (ReqSESAME != null) {
			ResultSet f3 = RSesame.send(ReqSESAME);
			results = ConvertToMap.getListFromResultSet(f3);

			if (sujets != null)
				sujets = null;

			for (String ele : results.keySet()) {
				if (sujets == null)
					sujets = results.get(ele);
			}

		}
		return this;
	}

	@Property
	@Persist
	String ReqD2RQ;

	public Index onSubmitFromFormD2RQ() {
		System.out.println("I am HERE " + ReqD2RQ);

		if (ReqD2RQ != null) {
			ResultSet f3 = RD2rq.send(ReqD2RQ);
			results = ConvertToMap.getListFromResultSet(f3);// */

			if (sujets != null)
				sujets = null;

			for (String ele : results.keySet()) {
				if (sujets == null)
					sujets = results.get(ele);
			}

		}
		return this;
	}

	@Property
	String head;

	@Persist
	@Property
	Map<String, ArrayList<String>> results;

	@Property
	int indexRaw;

	@Property
	@Persist
	ArrayList<String> sujets;

	public String getCourantArrayValue() {
		return results.get(head).get(indexRaw);
	}

	@Property
	@Persist
	String ReqHBASE;

	@Property
	@Persist
	FamilyQualfierType typeHBASE;

	public Index onSubmitFromFormHBASE() {
		System.out
				.println("I am HERE " + ReqHBASE + " " + typeHBASE.getValue());

		if (ReqHBASE != null) {
			String family = typeHBASE.getValue().split(":")[0];
			String qualifier = typeHBASE.getValue().split(":")[1];

			results = ConvertToMap.getMapFromList(
					RHbaseGet.getLike(ReqHBASE, family, qualifier, "feature"),
					"raw", "colmn family", "value");

			if (sujets != null)
				sujets = null;

			for (String ele : results.keySet()) {
				if (sujets == null)
					sujets = results.get(ele);
			}

		}
		return this;
	}

	
	@Property
	@Persist
	String ReqHBASERaw;

	public Index onSubmitFromFormHBASERaw() {
		System.out
				.println("I am HERE " + ReqHBASERaw );

		if (ReqHBASE != null) {
			
			results = ConvertToMap.getMapFromList(
					RHbaseGet.getRaw(ReqHBASERaw, "feature"),
					"raw", "colmn family", "value");

			if (sujets != null)
				sujets = null;

			for (String ele : results.keySet()) {
				if (sujets == null)
					sujets = results.get(ele);
			}
		}
		return this;
	}

}
