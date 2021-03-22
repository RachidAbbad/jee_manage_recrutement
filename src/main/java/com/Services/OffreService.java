package com.Services;

import com.Utils.AppHibernate;
import com.models.Offre;
import com.models.Recruteur;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class OffreService {
    public static void ajouterOffre(String titre, String desc, String emplacement, String typeContrat, String metier,
                                    String salairePrime, String competences, int departementId, int compteId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // get recruteur id
            Criteria criteria = session.createCriteria(Recruteur.class)
                    .add(Restrictions.eq("idCompte", compteId));
            Recruteur recruteur = (Recruteur) criteria.uniqueResult();

            Offre offre = new Offre(titre, desc, emplacement, typeContrat, metier, salairePrime, new Date(), competences, recruteur.getId(), departementId);
            session.save(offre);

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Offre> getListOffres() throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Offre");
            List<Offre> offres = query.list();

            session.getTransaction().commit();
            return offres;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static Offre getOffreById(Integer id) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Offre.class)
                    .add(Restrictions.eq("id", id));
            Offre offre = (Offre) criteria.uniqueResult();
            session.getTransaction().commit();

            return offre;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
