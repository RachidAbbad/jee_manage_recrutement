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
    public static int isRecruteur(int compteId) {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Recruteur.class)
                    .add(Restrictions.eq("idCompte", compteId));
            Recruteur recruteur = (Recruteur) criteria.uniqueResult();
            session.getTransaction().commit();

            if (recruteur == null) return -1;
            return recruteur.getId();

        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
            return -1;
        } finally {
            factory.close();
        }
    }

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

    public static void updateRecruteur(int compteId, int recruteurId, String password, String ville, String numTel, String siteweb, String nomRecr, String descRecr, String logoUrl) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // compte
            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("id", compteId));
            Compte compte = (Compte) criteria.uniqueResult();

            compte.setVille(ville);
            compte.setNumTel(numTel);
            if (!password.isEmpty())
                compte.setMoteDePasse(password);

            // recruteur
            Criteria criteria2 = session.createCriteria(Recruteur.class)
                    .add(Restrictions.eq("id", recruteurId));
            Recruteur recruteur = (Recruteur) criteria2.uniqueResult();

            recruteur.setSiteweb(siteweb);
            recruteur.setNom(nomRecr);
            recruteur.setDescription(descRecr);
            recruteur.setLogoUrl(logoUrl);

            // save
            session.save(compte);
            session.save(recruteur);

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Recruteur> getListRecruteurs() throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Recruteur");
            List<Recruteur> recruteurs = query.list();

            session.getTransaction().commit();
            return recruteurs;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
