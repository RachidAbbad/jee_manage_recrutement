package com.Utils;

import com.Services.OffreService;
import com.Services.RecruteurService;
import com.models.Recruteur;

import java.security.MessageDigest;

public class Md5 {
    public static String md5crypt(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }

    }
}
