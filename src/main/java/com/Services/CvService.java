package com.Services;

import com.Utils.AppHibernate;
import com.models.Candidat;
import com.models.Cv;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
}
