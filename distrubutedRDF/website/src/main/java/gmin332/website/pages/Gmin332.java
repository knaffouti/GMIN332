package gmin332.website.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Gmin332 {

	private List<String> dbs;

	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private String textAreaValue;
	
	@PageLoaded
	void getSMDSCheckBox() {
		if (dbs == null) {
			dbs = new ArrayList<String>();
			dbs.add("2008");
			dbs.add("2011");
			dbs.add("2013");
			dbs.add("2015");
		}
	}

	@Property
	private String db;

	@Persist
	private Set<String> selecteddbs;

	@Log
	public void onSubmitFromMyForm() {
		System.out.println(selecteddbs.size());
		for (String siteName : this.selecteddbs) {
			System.out.println(siteName);
		}
	}
	
	public boolean isSelected() {
		return false;
	}

	/**
	 * Add color to the selected set if selected.
	 */
	
	public void setSelected(boolean selected) {
		if (selected) {
			this.selecteddbs.add(this.db);
		} else {
			this.selecteddbs.remove(this.db);
		}
	}

	/**
	 * 	 * @return SMDs Names
	 */
	public List<String> getDbs() {
		return this.dbs;
	}

	/**
	 * @return Initialized Ok or NNot
	 */
	public boolean beginRender() {
		if (this.selecteddbs == null) {
			this.selecteddbs = new HashSet<String>();
		}
		return true;
	}

}
