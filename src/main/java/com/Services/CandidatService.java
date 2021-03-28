package com.Services;

import com.Utils.AppHibernate;
import com.google.gson.Gson;
import com.models.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
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

    public static void updateCv(int candidatId, String description, String formationsInput, String experiencesInput, String projetsInput, String competencesInput) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();
        Gson gson = new Gson();

        try {
            session.beginTransaction();

            Cv oldCv = CvService.getCvByCandidatId(candidatId);

            // delete old cv
            Query query1 = session.createQuery("delete from Formation where id_cv = :oid").setParameter("oid", oldCv.getId());
            query1.executeUpdate();
            Query query2 = session.createQuery("delete from Experience where id_cv = :oid").setParameter("oid", oldCv.getId());
            query2.executeUpdate();
            Query query3 = session.createQuery("delete from Competence where id_cv = :oid").setParameter("oid", oldCv.getId());
            query3.executeUpdate();
            Query query4 = session.createQuery("delete from Projet where id_cv = :oid").setParameter("oid", oldCv.getId());
            query4.executeUpdate();
            Query query5 = session.createQuery("delete from Cv where id_candidat = :oid").setParameter("oid", candidatId);
            query5.executeUpdate();

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

    public static Compte getCandidatByEmail(String email) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("email", email));
            Compte acc = (Compte) criteria.uniqueResult();
            session.getTransaction().commit();

            return acc;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static Candidat getCandidatByIdCompte(int idCompte) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Candidat.class)
                    .add(Restrictions.eq("idCompte", idCompte));
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

    public static void updateCandidat(int compteId, int candidatId, String password, String ville, String numTel, String civilite, String nomComplet, String titreEmploi, String photoUrl) throws Exception {
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
            candidat.setPhotoUrl(photoUrl);

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

    public static Cv hasCv(int candidatId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Cv.class)
                    .add(Restrictions.eq("idCandidat", candidatId));
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

    public static List<Candidat> getListCandidat() throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Candidat");
            List<Candidat> candidats = query.list();

            session.getTransaction().commit();
            return candidats;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
