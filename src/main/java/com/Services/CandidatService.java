package com.Services;

import com.Utils.AppHibernate;
import com.models.Candidat;
import com.models.Offre;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class CandidatService {
    public static int isCandidat(int compteId) {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Candidat.class)
                    .add(Restrictions.eq("idCompte", compteId));
            Candidat candidat = (Candidat) criteria.uniqueResult();
            session.getTransaction().commit();

            if (candidat == null) return -1;
            return candidat.getId();

        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
            return -1;
        } finally {
            factory.close();
        }
    }

    public static void ajouterCv() {

    }

    public static Candidat getCandidatById(Integer id) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Candidat.class)
                    .add(Restrictions.eq("id", id));
            Candidat candidat = (Candidat) criteria.uniqueResult();
            session.getTransaction().commit();

            return candidat;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

}
