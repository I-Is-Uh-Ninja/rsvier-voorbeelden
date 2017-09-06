/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.klant;

import app.DaoFactory;
import app.MainMenu;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class KlantController {
    
    private final KlantDao klantDao;
    private final KlantView klantView;
    
    public KlantController(Scanner in){
        klantDao = (KlantDao)DaoFactory.getInstance().getDao("klant");
        klantView = new KlantView(this, in);
    }
    
    public void start(){
        klantView.printKlantMenu();
    }
    
    public void listAlleKlanten(){
        klantView.printAlleKlanten(klantDao.findAll());
        klantView.printKlantenKeuzeMenu();
    }
    
    public Klant selectKlantForAccount(){
        List<Klant> klanten = klantDao.findAllWithoutAccount();
        Klant klant = null;
        if(klanten.isEmpty()){
            klantView.printFailure("klanten zonder account vinden");
            klant = newKlant();
            if(klant != null){
                klantDao.create(klant);
            }
        }
        else {
            klantView.printAlleKlanten(klanten);
            long id = klantView.selectKlant();
            klant = klantDao.findById(id);
            if(klant == null || klant.getId() <= 0){
                klantView.printFailure("klant vinden");
                return selectKlantForAccount();
            }
        }
        return klant;
    }
    
    public void createKlant(){
        Klant klant = newKlant();
        if(klant != null){
            klant = klantDao.create(klant);
            if(klant.getId() > 0){
                klantView.printSuccess();
                if(klantView.addAccount()){
                    MainMenu.getAccountController().createAccount(klant);
                }
                else start();
            }
            else{
                klantView.printFailure("aanmaken klant");
                start();
            }
        }
        else start();
    }
    
    Klant newKlant(){
        Klant klant = new Klant();
        setVoornaam(klant);
        setTussenvoegsel(klant);
        setAchternaam(klant);
        if(klantView.verifyKlantGegevens(klant)){
            return klant;
        }
        else return null;
    }
    
    void setVoornaam(Klant klant){
        klant.setVoornaam(klantView.vraagVoornaam());
    }
    
    void setTussenvoegsel(Klant klant){
        klant.setTussenvoegsel(klantView.vraagTussenvoegsel());
    }
    
    void setAchternaam(Klant klant){
        klant.setAchternaam(klantView.vraagAchternaam());
    }
    
    void goToHoofdMenu(){
        MainMenu.goToMainMenu();
    }
    
}
