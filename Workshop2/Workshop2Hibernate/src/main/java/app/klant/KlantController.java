/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.klant;

import app.DaoFactory;
import app.Dispatcher;
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
        klantView.printAlleKlanten(klantDao.findKlantenWithoutAccount());
        long id = klantView.selectKlant();
        Klant klant = klantDao.findById(id);
        if(klant != null && klant.getId() > 0){
            return klant;
        }
        else{
            klantView.printFailure("klant vinden");
            return selectKlantForAccount();
        }
    }
    
    public void createKlant(){
        Klant klant = new Klant();
        setVoornaam(klant);
        setTussenvoegsel(klant);
        setAchternaam(klant);
        if(klantView.verifyKlantGegevens(klant)){
            klant = klantDao.create(klant);
            if(klant.getId() > 0){
                klantView.printSuccess();
                if(klantView.addAccount()){
                    Dispatcher.getAccountController().createAccount(klant);
                }
                else {
                    start();
                }
            }
            else{
                klantView.printFailure("aanmaken klant");
                start();
            }
        }
        else{
            start();
        }
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
        Dispatcher.getHomeController().start();
    }
    
}
