
/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

      import csulb.cecs323.model.Student;

      import javax.persistence.EntityManager;
      import javax.persistence.EntityManagerFactory;
      import javax.persistence.EntityTransaction;
      import javax.persistence.Persistence;
      import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 *
 * This is for demonstration and educational purposes only.
 */
public class JpaStarterApp {
   private EntityManager entityManager;

   private static final Logger LOGGER = Logger.getLogger(JpaStarterApp.class.getName());

   public JpaStarterApp(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("starter_PU");
      EntityManager manager = factory.createEntityManager();
      JpaStarterApp hw4application = new JpaStarterApp(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      hw4application.createStudentEntity();

      tx.commit();
      LOGGER.fine("End of Transaction");

   }

   /**
    * Create and persist a Student object to the database.
    */
   public void createStudentEntity() {
      LOGGER.fine("Creating Student object");

      Student graceHopper = new Student();
      graceHopper.setFirstName("Grace");
      graceHopper.setLastName("Hopper");
      graceHopper.setGpa(4);

      Student jobs = new Student();
      jobs.setFirstName("Steve");
      jobs.setLastName("Wozniak");
      jobs.setGpa(3.9);

      LOGGER.fine("Persisting Student object to DB");
      this.entityManager.persist(jobs);
   }
}
