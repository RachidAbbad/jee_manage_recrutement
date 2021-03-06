package com.Services;

import com.Utils.AppHibernate;
import com.models.Candidat;
import com.models.Offre;
import com.models.Postulation;
import com.models.Recruteur;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PostulationService {
    public static boolean ajouterPostulation(int candidatId, int offreId, String body) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Offre.class)
                    .add(Restrictions.eq("id", offreId));
            Offre offre = (Offre) criteria.uniqueResult();

            if (offre.getEtat() == 0) {
                return false;
            }

            Postulation postulation = new Postulation(candidatId, offreId, new Date(), body);
            session.save(postulation);


            session.getTransaction().commit();

            ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
            emailExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Candidat c = CandidatService.getCandidatById(candidatId);
                        Offre o = OffreService.getOffreById(offreId);
                        Recruteur r = RecruteurService.getRecruteurById(o.getIdRecruteur());

                        String msgCand = Mail.msgApplyJob(c.getNomComplet(),"",o.getTitre());
                        String msgRec = Mail.MsgApplyJobMsgToRecu(c.getNomComplet(),"",o.getTitre(),r.getNom(),"");

                        Mail.send(CompteService.getCompteById(r.getIdCompte()).getEmail(),"[JobBoard] "+c.getNomComplet()+" has just applied to your job offer",msgRec);
                        Mail.send(CompteService.getCompteById(c.getIdCompte()).getEmail(),"[JobBoard] You just applied to job offer : "+o.getTitre(),msgCand);

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

    public static List<Postulation> getPostulationsByOffreId(int offreId) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("from Postulation where id_offre = :oid").setParameter("oid", offreId);
            List<Postulation> postulations = query.list();

            session.getTransaction().commit();

            return postulations;
        } catch (Exception exception) {
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }
    public static int getPostulation(int idCandidat,int idOffre){
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Postulation.class);
            criteria.add(Restrictions.eq("idCandidat", idCandidat));
            criteria.add(Restrictions.eq("idOffre", idOffre));

            List<Postulation> p = ( List<Postulation>) criteria.list();


            session.getTransaction().commit();

                return p.size();
        }catch (Exception exception){
            exception.printStackTrace();

        }
        return 0;
    }



}
