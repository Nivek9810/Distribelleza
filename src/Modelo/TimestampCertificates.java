/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TimestampCertificates {

    private CertificateFactory cf;

    public TimestampCertificates() {
    }

    public CertPath getCertPath() {
        CertPath cp = null;
        try {

            cf = CertificateFactory.getInstance("X.509");
            List<Certificate> list = new ArrayList<>();
            cp = cf.generateCertPath(list);
            Iterator<String> itr = cp.getEncodings();
        } catch (CertificateException ex) {
            System.out.println("Error con el CertPath: " + ex.getMessage());
        }
        return cp;
    }
}
