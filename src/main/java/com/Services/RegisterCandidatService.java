package com.Services;

import com.Utils.AppHibernate;
import com.models.Candidat;
import com.models.Compte;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class RegisterCandidatService {
    public static void register(String ville, String email, String mot_de_passe, String telephone, int verified, String type_compte, String nomcomplet, String titreemploi, String photoUrl, String civilite) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            // validation by email
            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("email", email));
            Compte checkingCompte = (Compte) criteria.uniqueResult();
            if (checkingCompte != null)
                throw new Exception("Email is already used");

            // save
            Compte compte = new Compte(ville, email, mot_de_passe, telephone, verified, type_compte);
            session.save(compte);

            Candidat candidat = new Candidat(compte.getId(), nomcomplet, titreemploi, photoUrl, civilite);
            session.save(candidat);

            session.getTransaction().commit();

            String msg = Mail.msgWelcome(nomcomplet,"");
            Mail.send(email,"[JobBoard] : Welcome to joining us",msg);
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
