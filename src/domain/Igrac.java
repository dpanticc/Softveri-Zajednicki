/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Igrac extends AbstractDomainObject implements Serializable {
    
    private Tim tim;
    private int igracID;
    private String nickname;
    private String ime;
    private String prezime;

    public Igrac() {
    }

    public Igrac(Tim tim, int igracID, String nickname, String ime, String prezime) {
        this.tim = tim;
        this.igracID = igracID;
        this.nickname = nickname;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public int getIgracID() {
        return igracID;
    }

    public void setIgracID(int igracID) {
        this.igracID = igracID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return nickname + " (" + ime + " " + prezime + ")";
    }
    
    
    @Override
    public String nazivTabele() {
        return " igrac ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return " JOIN tim t ON (i.timid = t.timid) JOIN grad g ON (t.gradid = g.gradid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Grad g = new Grad(rs.getLong("GradID"),
                    rs.getString("NazivGrada"));
            
            Tim t = new Tim(rs.getLong("TimID"),
                    rs.getString("NazivTima"), rs.getString("Trener"),
                    rs.getInt("BrojPobeda"), rs.getInt("BrojGubitaka"), g, null);
            
            Igrac i = new Igrac(t, rs.getInt("IgracID"), rs.getString("Nickname"), 
                    rs.getString("Ime"), rs.getString("Prezime"));

            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TimID, IgracID, Nickname, Ime, Prezime) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " TimID = " + tim.getTimID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + tim.getTimID() + ", " + igracID + ", "
                + "'" + nickname + "', '" + ime + "', '" + prezime + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE T.TIMID = " + tim.getTimID();
    }
}
