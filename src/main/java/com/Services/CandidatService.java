package com.Services;

import com.Utils.AppHibernate;
import com.google.gson.Gson;
import com.models.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Properties;


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

    public static void ajouterCv(int candidatId, String description, String formationsInput, String experiencesInput, String projetsInput, String competencesInput) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();
        Gson gson = new Gson();

        try {
            session.beginTransaction();

            // create cv
            Cv cv = new Cv(description, candidatId);
            session.save(cv);

            // create formations
            Properties[] formations = gson.fromJson(formationsInput, Properties[].class);

            for (Properties f:formations) {
                Formation formation = new Formation(f.get("etablissement").toString(), f.get("diplome").toString(), f.get("startDate").toString(), f.get("endDate").toString(), cv.getId());
                session.save(formation);
            }

            // create experiences
            Properties[] experiences = gson.fromJson(experiencesInput, Properties[].class);

            for (Properties e:experiences) {
                Experience experience = new Experience(e.get("entreprise").toString(), e.get("sujet").toString(), e.get("startDate").toString(), e.get("endDate").toString(), cv.getId());
                session.save(experience);
            }

            // create projets
            Properties[] projets = gson.fromJson(projetsInput, Properties[].class);

            for (Properties p:projets) {
                Projet projet = new Projet(p.get("titre").toString(), p.get("type").toString(), cv.getId());
                session.save(projet);
            }

            // create competences
            Properties[] competences = gson.fromJson(competencesInput, Properties[].class);

            for (Properties c:competences) {
                Competence competence = new Competence(c.get("nom").toString(), c.get("niveau").toString(), cv.getId());
                session.save(competence);
            }

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
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

    public static void updateCandidat(int compteId, int candidatId, String password, String ville, String numTel, String civilite, String nomComplet, String titreEmploi) throws Exception {
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

            // candidat
            Criteria criteria2 = session.createCriteria(Candidat.class)
                    .add(Restrictions.eq("id", candidatId));
            Candidat candidat = (Candidat) criteria2.uniqueResult();

            candidat.setCivilite(civilite);
            candidat.setNomComplet(nomComplet);
            candidat.setTitreEmploi(titreEmploi);

            // save
            session.save(compte);
            session.save(candidat);

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static boolean hasCv(int candidatId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Cv.class)
                    .add(Restrictions.eq("idCandidat", candidatId));
            Cv cv = (Cv) criteria.uniqueResult();
            session.getTransaction().commit();

            if (cv == null) return false;
            return true;

        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
