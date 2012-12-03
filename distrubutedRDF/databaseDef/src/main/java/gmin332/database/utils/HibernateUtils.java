
package gmin332.database.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtils
{

   private static final SessionFactory sessionFactory = HibernateUtils.buildSessionFactory ();

   private static SessionFactory buildSessionFactory ()
   {
      try {
         // Create the SessionFactory from hibernate.cfg.xml
         return new AnnotationConfiguration ().configure ("rdfdatabase.cfg.xml").buildSessionFactory ();
      }
      catch (Throwable ex) {
         // Make sure you log the exception, as it might be swallowed
         System.err.println ("Initial SessionFactory creation failed." + ex);
         ex.printStackTrace ();
         throw new ExceptionInInitializerError (ex);
      }
   }

   public static SessionFactory getSessionFactory ()
   {
      return HibernateUtils.sessionFactory;
   }

   public static void saveOrUpdateObject (Object op)
   {
      Session session = HibernateUtils.getSessionFactory ().openSession ();
      session.beginTransaction ();
      session.saveOrUpdate (op);
      session.getTransaction ().commit ();
      session.close ();
   }

   public static void saveOrUpdateObject (Session session, Object op)
   {
      session.beginTransaction ();
      session.saveOrUpdate (op);
      session.getTransaction ().commit ();
   }

}