package gmin332.database.insee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "inseecom")
public class Commune {

	@Id
	long inseeCode;

	@ManyToOne
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "deptCode")
	Departement deptCode;

	int communeCode;

	String communeName;

	int populationTotal;

}
