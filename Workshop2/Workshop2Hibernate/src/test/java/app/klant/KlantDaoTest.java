/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.klant;

import java.util.List;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ian
 */
public class KlantDaoTest {
    
    private static KlantDao klantDao;
    private static Klant klant;
    
    public KlantDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        klantDao = new KlantDao(Persistence.createEntityManagerFactory("HibernateRsvierPU").createEntityManager());
        klant = klantDao.create(new Klant("Theo", "de", "Tester"));
    }
    
    @AfterClass
    public static void tearDownClass(){
        klantDao.delete(klant.getId());
    }

    @Test
    public void testFindAll() {
        List<Klant> result = klantDao.findAll();
        assertTrue(result.contains(klant));
    }

    @Test
    public void testFindByVoornaam() {
        String voornaam = "Theo";
        List<Klant> result = klantDao.findByVoornaam(voornaam);
        assertTrue(result.contains(klant));
    }

    @Test
    public void testFindByAchternaam() {
        String tussenvoegsel = "de";
        String achternaam = "Tester";
        List<Klant> result = klantDao.findByAchternaam(tussenvoegsel, achternaam);
        assertTrue(result.contains(klant));
    }
/*
    @Test
    public void testFindByAccount() {
        fail("The test case is a prototype.");
    }
    */
    @Test
    public void testCreateKlant(){
        Klant klant = new Klant("John", null, "Doe");
        klant = klantDao.create(klant);
        assertTrue(klant.getId() > 0);
        if(klant.getId()>0){
            klantDao.delete(klant.getId());
        }
    }
    
    @Test
    public void updateKlant(){
        Klant klant = new Klant("John", null, "Doe");
        klant = klantDao.create(klant);
        if(klant.getId()>0){
            klant.setVoornaam("Jane");
            klantDao.update(klant);
            Klant result = klantDao.findById(klant.getId());
            assertEquals(result, klant);
            assertEquals(result.getVoornaam(), "Jane");
            klantDao.delete(klant.getId());
        }
    }
    
}
