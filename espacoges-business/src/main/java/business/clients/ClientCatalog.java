package business.clients;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import business.clients.exceptions.ClientNotFoundException;

/**
 * A catalog of clients
 */
@Stateless
public class ClientCatalog {
	
	/**
	 * Entity manager for accessing the persistence service 
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Constructs a client catalog given an entity manager
	 */
	public ClientCatalog() {
		
	}

	/**
	 * Finds a client given its id
	 * @param number The client's id
	 * @return The client with given id
	 * @throws ClientNotFoundException When the client does not exist.
	 */
	public Client getClient(int id) throws ClientNotFoundException {
		try {
			TypedQuery<Client> query = em.createNamedQuery(Client.FIND_BY_ID, Client.class);
			query.setParameter(Client.CLIENT_ID, id);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new ClientNotFoundException("Client with id " + id + " does not exist.");
		}
	}
	
	/**
	 * Adds a new individual client
	 * @param name The name of the client
	 * @param nif The NIF of the client
	 * @param address The address of the client
	 * @param birthDate The client's birth date
	 */
	@Transactional
	public void addIndividual(String name, int nif, String address, Date birthDate) {
		Client client = new Individual(name, nif, address, birthDate);
		em.persist(client);
	}
	
	/**
	 * Adds a new club client
	 * @param name The name of the client 
	 * @param nif The NIF of the client
	 * @param address The address of the client
	 */
	@Transactional
	public void addClub(String name, int nif, String address) {
		Client client = new Club(name, nif, address);
		em.persist(client);
	}
	
}
