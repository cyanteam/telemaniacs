package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Simona Tinkova
 */
@Transactional
@Repository
public class TransmissionDaoImpl implements TransmissionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Transmission transmission) {
		if (transmission == null){
			throw new IllegalArgumentException("Transmission is null");
		}
		entityManager.persist(transmission);
	}

	@Override
	public void delete(Transmission transmission) {
		if(transmission == null){
			throw new IllegalArgumentException("You are trying to delete null transmission");
		}

		entityManager.remove(findById(transmission.getId()));
	}

	@Override
	public void update(Transmission transmission) {
		if(transmission == null) {
			throw new IllegalArgumentException("You are trying to update null transmission.");
		}
		if (findById(transmission.getId()) == null) {
			throw new IllegalArgumentException("Transmission is not stored in the datebase.");
		}

		entityManager.merge(transmission);
	}
	@Override
	public Transmission findById(Long id) {
		if (id == null){
			throw new IllegalArgumentException("id is null");
		}
		return entityManager.find(Transmission.class, id);
	}

	@Override
	public Transmission findByName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("You tried to find transmission with null name!");
		}
		return entityManager
				.createQuery("SELECT u FROM Transmission u WHERE u.name = :name", Transmission.class)
				.setParameter("name", name)
				.getSingleResult();
	}


}
