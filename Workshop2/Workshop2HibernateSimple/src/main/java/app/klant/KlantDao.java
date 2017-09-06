/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.klant;

import app.GenericDao;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ian
 */
public class KlantDao extends GenericDao<Klant>{

    public KlantDao(EntityManager em) {
        super(em);
    }

    @Override
    public List<Klant> findAll() {
        em.getTransaction().begin();
        List<Klant> result = em.createQuery("SELECT k FROM Klant k", Klant.class).getResultList(); //Gebruik van JPQL: vorm van SQL van JPA die gebruik maakt van de namen van de klassen en variabelen
        em.getTransaction().commit();
        return result;
    }
    
    public List<Klant> findByVoornaam(String voornaam){
        em.getTransaction().begin();
        List<Klant> result = em.createQuery("SELECT k FROM Klant k WHERE k.voornaam = :voornaam", Klant.class) // de ":" voor "voornaam" geeft aan dat "voornaam" de naam van een parameter is
                .setParameter("voornaam", voornaam)
                .getResultList();
        em.getTransaction().commit();
        return result;
    }
    
    public List<Klant> findByAchternaam(String tussenvoegsel, String achternaam){
        em.getTransaction().begin();
        List<Klant> result = em.createQuery("SELECT k FROM Klant k WHERE k.tussenvoegsel = :tussenvoegsel AND k.achternaam = :achternaam", Klant.class)
                .setParameter("tussenvoegsel", tussenvoegsel)
                .setParameter("achternaam", achternaam)
                .getResultList();
        em.getTransaction().commit();
        return result;
    }
    
    public Klant findByAccount(long accountId){
        em.getTransaction().begin();
        Klant result = em.createQuery("SELECT k FROM Klant k WHERE k.account.id = :accountId", Klant.class)
                .setParameter("accountId", accountId)
                .getSingleResult();
        em.getTransaction().commit();
        return result;
    }
    
    public List<Klant> findAllWithoutAccount(){
        em.getTransaction().begin();
        List<Klant> result = em.createQuery("SELECT k FROM Klant k LEFT OUTER JOIN Account a ON k.id=a.klant.id WHERE a.klant.id IS NULL", Klant.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    protected Class<Klant> getEntityClass() {
        return Klant.class;
    }
    
}
