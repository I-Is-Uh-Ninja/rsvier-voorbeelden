/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.klant.KlantDao;
import app.account.AccountDao;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ian
 */
public class DaoFactory {
    private static DaoFactory instance;
    private final EntityManagerFactory emf;
    
    private DaoFactory(){
        emf = Persistence.createEntityManagerFactory("HibernateRsvierPU");
    }
    
    public static synchronized DaoFactory getInstance(){
        if(instance == null){
            instance = new DaoFactory();
        }
        return instance;
    }
    
    public GenericDao getDao(String classname){
        classname = classname.toLowerCase();
        switch(classname){
            case "klant":
                return new KlantDao(emf.createEntityManager());
            case "account":
                return new AccountDao(emf.createEntityManager());
            default:
                throw new IllegalArgumentException("DAO with that name doesn't exist!");
        }
    }
}
