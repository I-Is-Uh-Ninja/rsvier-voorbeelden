/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.account;

import app.klant.Klant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="username")
    private String gebruikersnaam;
    
    private String wachtwoord;
    
    @Column(name="accounttype")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    
    @OneToOne(optional=false)
    @JoinColumn(name="klant_id", referencedColumnName="id")
    private Klant klant;

    public Account() {
        this.accountType = AccountType.KLANT;
    }

    public Account(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.accountType = AccountType.KLANT;
    }

    public Account(String gebruikersnaam, String wachtwoord, Klant klant) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.klant = klant;
        this.accountType = AccountType.KLANT;
    }

    public Account(String gebruikersnaam, String wachtwoord, AccountType accountType) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.accountType = accountType;
    }

    public Account(String gebruikersnaam, String wachtwoord, AccountType accountType, Klant klant) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.accountType = accountType;
        this.klant = klant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    
    
    
}
