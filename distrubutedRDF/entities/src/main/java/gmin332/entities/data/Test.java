package gmin332.entities.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class Test {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   long id;
	
	   String name;

}
