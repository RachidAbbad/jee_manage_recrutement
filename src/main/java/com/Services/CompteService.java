package com.Services;

import com.Utils.AppHibernate;
import com.models.Compte;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class CompteService {
    public static Compte getCompteById(Integer id) throws Exception {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("id", id));
            Compte compte = (Compte) criteria.uniqueResult();
            session.getTransaction().commit();

            return compte;
        } catch (Exception exception) {
            session.getTransaction().rollback();
            throw new Exception(exception);
        } finally {
            factory.close();
        }
    }

    public static String uploadPhoto(int compteId, HttpServletRequest request) throws IOException, ServletException {
        String uploadPath = request.getServletContext().getRealPath("") + "Assets" + File.separator + "photos";

        // delete old photo if there is any
        File folder = new File(uploadPath);
        File fList[] = folder.listFiles();
        for (File file:fList) {
            if (file.getName().contains("compte-" + compteId)) {
                file.delete();
            }
        }

        // upload photo
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String photoUrl = "";

        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                String extension = FilenameUtils.getExtension(uploadPath + File.separator + part.getSubmittedFileName());
                photoUrl = "compte-" + compteId + "." + extension;
                part.write(uploadPath + File.separator + photoUrl);
            }
        }

        return photoUrl;
    }
}
