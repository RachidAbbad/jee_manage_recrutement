package com.Services;

import com.Utils.AppHibernate;
import com.models.Compte;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class LoginService {
    public static boolean Login(String email, String password) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("email", email));
            Compte compte = (Compte) criteria.uniqueResult();

            if (compte == null || !compte.getMoteDePasse().equals(password))
                return false;

            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
