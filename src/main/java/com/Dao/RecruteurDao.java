package com.Dao;

import com.Utils.AppHibernate;
import com.models.Recruteur;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class RecruteurDao implements RecruteurI {
    @Override
    public Recruteur getRecruteurByName(String name) {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        Criteria criteria2 = session.createCriteria(Recruteur.class)
                .add(Restrictions.eq("nom", name));
        return (Recruteur) criteria2.uniqueResult();
    }
}
