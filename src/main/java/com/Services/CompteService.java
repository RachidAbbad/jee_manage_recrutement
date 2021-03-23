package com.Services;

import com.Utils.AppHibernate;
import com.models.Compte;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class CompteService {
    public static Compte getCompteById(Integer id) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("id", id));
            Compte compte = (Compte) criteria.uniqueResult();
            session.getTransaction().commit();

            return compte;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
