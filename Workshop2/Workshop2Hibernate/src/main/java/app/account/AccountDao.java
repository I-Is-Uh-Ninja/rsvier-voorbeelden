/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.account;

import app.GenericDao;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ian
 */
public class AccountDao extends GenericDao<Account>{

    public AccountDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public List<Account> findAll() {
        em.getTransaction().begin();
        List<Account> result = em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        em.getTransaction().commit();
        return result;
    }
    
    public Account findByGebruikersnaam(String gebruikersnaam){ //Gebruik een List<Account> als gebruikersnaam niet uniek is!
        em.getTransaction().begin();
        Account result = em.createQuery("SELECT a FROM Account a WHERE a.gebruikersnaam = :gebruikersnaam", Account.class)
                .setParameter("gebruikersnaam", gebruikersnaam)
                .getSingleResult();
        em.getTransaction().commit();
        return result;
    }
    
    public Account findByKlant(long klantId){
        em.getTransaction().begin();
        Account result = em.createQuery("SELECT a FROM Account a WHERE a.klant.id = :klantId", Account.class)
                .setParameter("klantId", klantId)
                .getSingleResult();
        em.getTransaction().commit();
        return result;
    }
}
