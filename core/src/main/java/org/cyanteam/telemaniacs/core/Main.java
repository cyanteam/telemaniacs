package org.cyanteam.telemaniacs.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by tinko on 10/29/2017.
 */
public class Main {
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");

		emf.close();
	}
}
