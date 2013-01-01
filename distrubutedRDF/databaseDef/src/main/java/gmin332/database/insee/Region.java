package gmin332.database.insee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inseereg")
public class Region {

	@Id
	int regionCode;
	String regionName;
	int nbrCommunes;
	int populationTotale;
}
