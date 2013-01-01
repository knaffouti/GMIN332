package gmin332.database.insee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "inseedept")
public class Departement {

	@Id
	String deptCode;

	String deptName;
	int nbrCommunes;
	int populationTotal;

	@ManyToOne
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "regionCode")
	Region regionCode;

}
