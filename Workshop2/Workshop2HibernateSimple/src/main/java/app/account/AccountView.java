/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.account;

import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Ian
 */
public class AccountView {
    
    private final Scanner input;
    private final AccountController accountController;
    
    AccountView(AccountController ac, Scanner in){
        input = in;
        accountController = ac;
    }
    
    void printStartMenu(){
        System.out.println("Maak een keuze.");
        System.out.println("1: Bekijk alle accounts");
        System.out.println("2: Maak een nieuw account aan");
        System.out.println("3: Terug naar hoofdmenu");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                accountController.listAccountsKeuzeMenu();
                break;
            case 2:
                accountController.createAccount();
                break;
            case 3:
                accountController.goToHoofdMenu();
        }
    }
    
    void printAlleAccounts(List<Account> accounts){
        accounts.stream().forEach(a -> System.out.println(a.getGebruikersnaam()));
    }
    
    void printAccountsKeuzeMenu(){
        System.out.println("1: Bewerk een account");
        System.out.println("2: Verwijder een account");
        System.out.println("3: Terug naar account-menu");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                //TODO
                break;
            case 2:
                //TODO
                break;
            case 3:
                accountController.start();
        }
    }
    
    String vraagGebruikersnaam(){
        System.out.println("Wat is de gebruikersnaam?");
        String uname = input.nextLine();
        uname = uname.trim();
        return uname;
    }
    
    String vraagWachtwoord(){
        System.out.println("Wat is het wachtwoord?");
        String pw = input.nextLine();
        System.out.println("Herhaal het wachtwoord.");
        String pwCheck = input.nextLine();
        if(!pw.equals(pwCheck)){
            System.out.println("Wachtwoorden komen niet overeen!");
            return vraagWachtwoord();
        }
        return pw;
    }
    
    int vraagAccountType(TreeMap<Integer, String> types){
        System.out.println("Wat is het accounttype?");
        types.forEach((k,v) -> System.out.println(k + ": " + v));
        return input.nextInt();
    }
    
    boolean verifyAccountGegevens(Account account){
        System.out.println("Kloppen deze gegevens?");
        String hiddenPw = account.getWachtwoord().replaceAll(".", "\\*");
        System.out.println(account.getKlant().getNaam() + ", gebruikersnaam: " + account.getGebruikersnaam() + ", wachtwoord: " + hiddenPw + ", accounttype: " + account.getAccountType().toString());
        System.out.println("1: Voltooien.");
        System.out.println("2: Pas gebruikersnaam aan.");
        System.out.println("3: Pas wachtwoord aan.");
        System.out.println("4: Pas accounttype aan.");
        System.out.println("5: Koppel aan andere klant.");
        System.out.println("6: Annuleren.");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                return true;
            case 2:
                accountController.setGebruikersnaam(account);
                return verifyAccountGegevens(account);
            case 3:
                accountController.setWachtwoord(account);
                return verifyAccountGegevens(account);
            case 4:
                accountController.setAccountType(account);
                return verifyAccountGegevens(account);
            case 5:
                accountController.setKlant(account);
                return verifyAccountGegevens(account);
            case 6:
                return false;
            default:
                System.out.println("Ongeldige keuze!");
                return verifyAccountGegevens(account);
        }
    }
    
    void printSuccess(){
        System.out.println("Succes!");
    }
    
    void printFailure(String action){
        System.out.println("Kon actie niet uitvoeren: " + action);
    }
}
