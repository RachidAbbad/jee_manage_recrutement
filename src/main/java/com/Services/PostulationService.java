package com.Services;

import com.Utils.AppHibernate;
import com.models.Offre;
import com.models.Postulation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PostulationService {
    public static void ajouterPostulation(int candidatId, int offreId, String body) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Offre.class)
                    .add(Restrictions.eq("id", offreId));
            Offre offre = (Offre) criteria.uniqueResult();

            if (offre.getEtat() == 0) {
                throw new Exception("Offre is closed");
            }

            Postulation postulation = new Postulation(candidatId, offreId, new Date(), body);
            session.save(postulation);

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Postulation> getPostulationsByOffreId(int offreId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("from Postulation where id_offre = :oid").setParameter("oid", offreId);
            List<Postulation> postulations = query.list();

            session.getTransaction().commit();

            return postulations;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

}
