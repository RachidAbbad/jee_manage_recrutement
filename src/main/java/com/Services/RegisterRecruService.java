package com.Services;

import com.Utils.AppHibernate;
import com.models.Compte;
import com.models.Recruteur;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class RegisterRecruService {
    public static void register(String ville, String email, String mot_de_passe, String telephone, int verified, String type_compte, String nom, String registerDescRec, String registerSiteweb) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            // Validation by email and name
            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("email", email));
            Compte checkingCompte = (Compte) criteria.uniqueResult();
            if (checkingCompte != null)
                throw new Exception("Email is already used");

            Criteria criteria2 = session.createCriteria(Recruteur.class)
                    .add(Restrictions.eq("nom", nom));
            Recruteur checkingRecruteur = (Recruteur) criteria2.uniqueResult();
            if (checkingRecruteur != null)
                throw new Exception("Nom is already used");

            // save
            Compte compte = new Compte(ville, email, mot_de_passe, telephone, verified, type_compte);
            session.save(compte);

            Recruteur recruteur = new Recruteur(compte.getId(), nom, registerDescRec, registerSiteweb);
            session.save(recruteur);

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
