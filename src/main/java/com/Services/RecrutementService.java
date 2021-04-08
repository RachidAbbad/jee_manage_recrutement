package com.Services;

import com.Utils.AppHibernate;
import com.models.Candidat;
import com.models.Offre;
import com.models.Recrutement;
import com.models.Recruteur;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecrutementService {
    public static boolean addRecrutement(int idRecruteur, int idCandidat, int offreId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Offre.class)
                    .add(Restrictions.eq("id", offreId));
            Offre offre = (Offre) criteria.uniqueResult();

            offre.setEtat(0); // offre is closed

            Recrutement recrutement = new Recrutement(new Date(), idRecruteur, idCandidat);
            session.save(recrutement);
            session.save(offre);

            session.getTransaction().commit();


            ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
            emailExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String msg = Mail.recrutmentMsg(CandidatService.getCandidatById(idCandidat).getNomComplet(),OffreService.getOffreById(offreId).getTitre());
                        Mail.send(CompteService.getCompteById(CandidatService.getCandidatById(idCandidat).getIdCompte()).getEmail()
                                ,"[JobBoard] : Your application has been accepted"
                                ,msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            emailExecutor.shutdown();

            return true;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
            return false;
        } finally {
            factory.close();
        }
    }
}
