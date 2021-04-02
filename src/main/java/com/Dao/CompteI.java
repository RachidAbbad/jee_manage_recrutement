package com.Dao;

import com.models.Compte;
import org.hibernate.Session;

public interface CompteI {
    Compte getCompteByEmail(String email, Session session) throws Exception;
    boolean deleteCompte(int id);
}
