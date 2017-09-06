/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.klant;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class KlantView {
    
    private final Scanner input;
    private final KlantController klantController;
    
    public KlantView(KlantController kc, Scanner in){
        input = in;
        klantController = kc;
    }
    
    void printKlantMenu(){
        System.out.println("Maak een keuze");
        System.out.println("1: Bekijk alle klanten");
        System.out.println("2: Voeg nieuwe klant toe");
        System.out.println("3: Terug naar hoofdmenu.");
        int keuze = input.nextInt();
        input.nextLine(); //nextLine() moet aangeroepen worden om het newline character (de 'Enter') mee te nemen, en de pointer van Scanner te avanceren naar de volgende regel.
        switch(keuze){
            case 1:
                klantController.listAlleKlanten();
                break;
            case 2:
                klantController.createKlant();
            break;
            case 3:
                klantController.goToHoofdMenu();
            break;
            default:
                System.out.println("Ongeldige keuze!");
                printKlantMenu();
        }
    }
    
    void printAlleKlanten(List<Klant> klanten){
        klanten.stream().forEach(k -> System.out.println(k.getId() + ": " + k.getNaam()));
    }
    
    void printKlantenKeuzeMenu(){
        System.out.println("Wat wilt u doen?");
        System.out.println("1: Bewerk de gegevens van een klant");
        System.out.println("2: Verwijder een klant");
        System.out.println("3: Terug naar klantmenu.");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                klantController.start();
                break;
            case 2:
                klantController.start();
                break;
            case 3:
                klantController.start();
                break;
            default:
                System.out.println("Ongeldige keuze!");
                printKlantenKeuzeMenu();
        }
    }
    
    long selectKlant(){
        System.out.println("Voer het nummer in van de klant die u wilt selecteren");
        long id = input.nextLong();
        input.nextLine();
        return id;
    }
    
    String vraagVoornaam(){
        System.out.println("Wat is de voornaam?");
        String vnaam = input.nextLine();
        vnaam = vnaam.trim();
        return vnaam;
    }
    
    String vraagTussenvoegsel(){
        System.out.println("Wat is het eventuele tussenvoegsel (druk op enter als u geen tussenvoegsel heeft)?");
        String tvoegsel = input.nextLine(); //matcht alles wat eindigt op een newline character
        if(tvoegsel != null){
            tvoegsel = tvoegsel.trim();
            if(tvoegsel.isEmpty()){
                tvoegsel = null; //Zorg ervoor dat het tussenvoegsel null is als de gebruiker alleen een newline (of spatie oid) invoert
            }
        }
        return tvoegsel;
    }
    
    String vraagAchternaam(){
        System.out.println("Wat is de achternaam?");
        String anaam = input.nextLine();
        anaam = anaam.trim();
        return anaam;
    }
    
    boolean verifyKlantGegevens(Klant klant){
        System.out.println("Kloppen deze gegevens?");
        System.out.println(klant.getNaam());
        System.out.println("1: Voltooien");
        System.out.println("2: Pas voornaam aan");
        System.out.println("3: Pas tussenvoegsel aan");
        System.out.println("4: Pas achternaam aan");
        System.out.println("5: Annuleren");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                return true;
            case 2:
                klantController.setVoornaam(klant);
                return verifyKlantGegevens(klant);
            case 3:
                klantController.setTussenvoegsel(klant);
                return verifyKlantGegevens(klant);
            case 4:
                klantController.setAchternaam(klant);
                return verifyKlantGegevens(klant);
            case 5:
                return false;
            default:
                System.out.println("Ongeldige keuze!");
                return verifyKlantGegevens(klant);
        }
    }
    
    boolean addAccount(){
        System.out.println("Wilt u een account toevoegen aan deze klant?");
        System.out.println("1: Ja.");
        System.out.println("2: Nee.");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                return true;
            case 2:
                return false;
            default:
                System.out.println("Ongeldige keuze!");
                return addAccount();
        }
    }
    
    void printSuccess(){
        System.out.println("Succes!");
    }
    
    void printFailure(String actie){
        System.out.println("Kon actie niet uitvoeren: " + actie);
    }
    
}
