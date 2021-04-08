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
    public static boolean deleteOffre(int idOffre) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("delete from Postulation where id_offre = :oid");
            query1.setParameter("oid",idOffre);
            query1.executeUpdate();
            Query query2 = session.createQuery("delete from Offre where id = :oid");
            query2.setParameter("oid",idOffre);
            query2.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
            return false;
        } finally {
            factory.close();
        }
    }
    public static List<Offre> searchOffer(String jobTitle,String cityName,int dep) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Offre where titre='"+jobTitle+"' and emplacement='"+cityName+"' and id_departement="+dep);
            List<Offre> offres = query.list();

            session.getTransaction().commit();
            return offres;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        } finally {
            factory.close();
        }

    }
    public static List<Offre> searchOfferByDep(int dep) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Offre where id_departement="+dep);
            List<Offre> offres = query.list();

            session.getTransaction().commit();
            return offres;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        } finally {
            factory.close();
        }

    }

    public static void main(String[] args) throws Exception {
        for (Offre o : OffreService.searchOffer("Full Stack Developer","marrakech",1)) {
            System.out.println(o.getTitre());
        }
    }


}
