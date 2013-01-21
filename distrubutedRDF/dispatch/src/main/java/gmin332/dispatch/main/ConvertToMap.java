package gmin332.dispatch.main;

import gmin332.hbase.utils.Triple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class ConvertToMap {

	public static Map<String, ArrayList<String>> getListFromResultSet(
			ResultSet result) {
		Map<String, ArrayList<String>> params = new HashMap<String, ArrayList<String>>();

		for (String ele : result.getResultVars()) {
			params.put(ele, new ArrayList<String>());
		}

		while (result.hasNext()) {
			QuerySolution res = result.nextSolution();
			for (String ele : result.getResultVars()) {
				params.get(ele)
						.add(res.get(ele).toString().replace("^^", "-")
								.split("-")[0]);
			}
		}

		return params;
	}

	
	public static void show(Map<String, ArrayList<String>> params) {

		int lenth = 0;
		for (String ele : params.keySet()) {
			System.out.print(ele + "\t");
			lenth = params.get(ele).size();
		}

		System.out.println();

		for (int j = 0; j < lenth; j++) {
			for (String ele : params.keySet()) {
				System.out.print(params.get(ele).get(j) + "\t");
			}
			System.out.println();
		}

	}
	
	

	public static Map<String, ArrayList<String>> getMapFromList(
			List<Triple<String, String, String>> result, String first,String second, String third) {
		Map<String, ArrayList<String>> params = new HashMap<String, ArrayList<String>>();

		params.put(first,new ArrayList<String>());
		params.put(second,new ArrayList<String>());
		params.put(third,new ArrayList<String>());
		
		for (Triple<String, String, String> triple : result) {
			params.get(first).add(triple.getFirst());
			params.get(second).add(triple.getSecond());
			params.get(third).add(triple.getThird());
		}
		return params;
	}

}
