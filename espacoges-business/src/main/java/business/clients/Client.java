package business.clients;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import static javax.persistence.DiscriminatorType.STRING;

/**
 * An abstract class representing a Client 
 *
 */
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@NamedQueries({
	@NamedQuery(name=Client.FIND_BY_ID, query="SELECT c FROM Client c WHERE c.id = :" + Client.CLIENT_ID),
})
@DiscriminatorColumn(name = "CLIENTTYPE", discriminatorType = STRING)
public abstract class Client {
	
	// Named query name constants

	public static final String FIND_BY_ID = "Client.findById";
	public static final String CLIENT_ID = "id";

	/**
	 * Client primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private int nif;
	
	@Column(nullable = false)
	private String address;
	
	/**
	 * Constructor needed by JPA 
	 */
	Client() {	
	}
	
	/**
	 * Creates a new Client given its data
	 * @param name The name of the client
	 * @param nif The NIF of the client
	 * @param address The address of the client
	 */
	public Client(String name, int nif, String address) {
		this.name = name;
		this.nif = nif;
		this.address = address;
	}
	
	/**
	 * Returns the name of the client
	 * @return The name of the client
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a string representing the client type, namely
	 * "Individual" if it's an individual client and "Club"
	 * if the client is a sport club
	 * @return A string representing the client type
	 */
	public abstract String getType();

	/**
	 * Returns the NIF of the client
	 * @return The NIF of the client
	 */
	public int getNif() {
		return nif;
	}

	/**
	 * Returns the address of the client
	 * @return The address of the client
	 */
	public String getAddress() {
		return address;
	}
	
}
