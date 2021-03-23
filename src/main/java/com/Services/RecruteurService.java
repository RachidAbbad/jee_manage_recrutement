package com.Services;

import com.Utils.AppHibernate;
import com.models.Compte;
import com.models.Offre;
import com.models.Recruteur;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RecruteurService {
    public static List<Offre> getOffresOfRecruteur(int recruteurId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Offre where id_recruteur = :rid").setParameter("rid", recruteurId);
            List<Offre> offres = query.list();

            session.getTransaction().commit();
            return offres;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static Recruteur getRecruteurById(Integer id) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Recruteur.class)
                    .add(Restrictions.eq("id", id));
            Recruteur recruteur = (Recruteur) criteria.uniqueResult();
            session.getTransaction().commit();

            return recruteur;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
