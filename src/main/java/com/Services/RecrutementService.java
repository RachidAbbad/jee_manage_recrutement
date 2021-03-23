package com.Services;

import com.Utils.AppHibernate;
import com.models.Candidat;
import com.models.Offre;
import com.models.Recrutement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class RecrutementService {
    public static void addRecrutement(int idRecruteur, int idCandidat, int offreId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Offre.class)
                    .add(Restrictions.eq("id", offreId));
            Offre offre = (Offre) criteria.uniqueResult();

            if (offre.getEtat() == 0) {
                throw new Exception("Offre is closed");
            }

            offre.setEtat(0); // offre is closed

            Recrutement recrutement = new Recrutement(new Date(), idRecruteur, idCandidat);
            session.save(recrutement);
            session.save(offre);

            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
}
