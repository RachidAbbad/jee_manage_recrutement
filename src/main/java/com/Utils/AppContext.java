package com.Utils;

import com.models.Compte;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AppContext {
    public static int isAthorized(HttpServletRequest request) {
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();
        Cookie[] cookies = request.getCookies();

        int compteId = -1;
        Cookie ourCookie = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                ourCookie = cookie;
                break;
            };
        }

        if (ourCookie != null) {
            try {
                session.beginTransaction();
                Criteria criteria = session.createCriteria(Compte.class)
                        .add(Restrictions.eq("email", ourCookie.getValue()));
                Compte compte = (Compte) criteria.uniqueResult();

                compteId = compte.getId();
                session.getTransaction().commit();

            } catch (Exception exception) {
                exception.printStackTrace();
                return compteId;
            }
        }

        return compteId;
    }
}
