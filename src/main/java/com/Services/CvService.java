package com.Services;

import com.Utils.AppHibernate;
import com.models.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CvService {
    public static Cv getCvByCandidatId(Integer id) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Cv.class)
                    .add(Restrictions.eq("idCandidat", id));
            Cv cv = (Cv) criteria.uniqueResult();
            session.getTransaction().commit();

            return cv;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Formation> getFormationsByCvId(int cvId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Formation where id_cv = :oid").setParameter("oid", cvId);
            List<Formation> formations = query.list();

            session.getTransaction().commit();
            return formations;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Experience> getExperiencesByCvId(int cvId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Experience where id_cv = :oid").setParameter("oid", cvId);
            List<Experience> experiences = query.list();

            session.getTransaction().commit();
            return experiences;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Competence> getCompetencesByCvId(int cvId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Competence where id_cv = :oid").setParameter("oid", cvId);
            List<Competence> competences = query.list();

            session.getTransaction().commit();
            return competences;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static List<Projet> getProjetsByCvId(int cvId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Projet where id_cv = :oid").setParameter("oid", cvId);
            List<Projet> projets = query.list();

            session.getTransaction().commit();
            return projets;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static void deleteCvById(int cvId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // delete old cv
            Query query1 = session.createQuery("delete from Formation where id_cv = :oid").setParameter("oid", cvId);
            query1.executeUpdate();
            Query query2 = session.createQuery("delete from Experience where id_cv = :oid").setParameter("oid", cvId);
            query2.executeUpdate();
            Query query3 = session.createQuery("delete from Competence where id_cv = :oid").setParameter("oid", cvId);
            query3.executeUpdate();
            Query query4 = session.createQuery("delete from Projet where id_cv = :oid").setParameter("oid", cvId);
            query4.executeUpdate();
            Query query5 = session.createQuery("delete from Cv where id = :oid").setParameter("oid", cvId);
            query5.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
