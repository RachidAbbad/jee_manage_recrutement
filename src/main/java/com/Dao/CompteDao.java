package com.Dao;

import com.models.Compte;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CompteDao implements CompteI {
    @Override
    public Compte getCompteByEmail(String email, Session session) throws Exception {
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("email", email));
            session.getTransaction().commit();
            return (Compte) criteria.uniqueResult();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        }
    }

    @Override
    public boolean deleteCompte(int id) {
        return false;
    }
}
