package com.Services;

import com.Utils.AppHibernate;
import com.models.Departement;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DepartementService {
    public static List<Departement> getListDepartement() throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Departement");
            List<Departement> departements = query.list();

            session.getTransaction().commit();
            return departements;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
