/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ian
 */
public abstract class GenericDao<T> {
    protected EntityManager em;
    
    public GenericDao(EntityManager em){
        this.em = em;
    }
    
    protected abstract Class<T> getEntityClass(); //Methode die de klasse teruggeeft, zodat we die kunnen gebruiken in de findById-methode
    
    public T create(T entity){
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }
    public T findById(long id){
        em.getTransaction().begin();
        T entity = em.find(getEntityClass(), id);
        em.getTransaction().commit();
        return entity;
    }
    
    public abstract List<T> findAll(); //Lastig om met generics te implementeren, dus laat de subklasse het doen!
    
    public void update(T entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }
    
    public void delete(long id){
        em.getTransaction().begin();
        T entity = em.find(getEntityClass(), id); //een entity moet eerst gevonden worden, zodat Hibernate zeker weet dat we een persisted entity verwijderen
        em.remove(entity);
        em.getTransaction().commit();
    }
    
    @Override
    public void finalize(){
        em.close();
    }
    
}
