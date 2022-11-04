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
public class Tim extends AbstractDomainObject implements Serializable {
    
    private Long timID;
    private String nazivTima;
    private String trener;
    private int brojPobeda;
    private int brojGubitaka;
    private Grad grad;
    private ArrayList<Igrac> igraci;

    public Tim() {
    }

    public Tim(Long timID, String nazivTima, String trener, int brojPobeda, int brojGubitaka, Grad grad, ArrayList<Igrac> igraci) {
        this.timID = timID;
        this.nazivTima = nazivTima;
        this.trener = trener;
        this.brojPobeda = brojPobeda;
        this.brojGubitaka = brojGubitaka;
        this.grad = grad;
        this.igraci = igraci;
    }

    @Override
    public String toString() {
        return nazivTima;
    }
    
    @Override
    public String nazivTabele() {
        return " tim ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN grad g ON (t.gradid = g.gradid) ";
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivTima, Trener, BrojPobeda, BrojGubitaka, GradID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " TimID = " + timID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivTima + "', '" + trener + "', "
                + brojPobeda + ", " + brojGubitaka + ", " + grad.getGradID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Trener = '" + trener + "', " +"BrojPobeda = "+brojPobeda+ ", " + "BrojGubitaka = "+brojGubitaka;
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getTimID() {
        return timID;
    }

    public void setTimID(Long timID) {
        this.timID = timID;
    }

    public String getNazivTima() {
        return nazivTima;
    }

    public void setNazivTima(String nazivTima) {
        this.nazivTima = nazivTima;
    }

    public String getTrener() {
        return trener;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }

    public int getBrojPobeda() {
        return brojPobeda;
    }

    public void setBrojPobeda(int brojPobeda) {
        this.brojPobeda = brojPobeda;
    }

    public int getBrojGubitaka() {
        return brojGubitaka;
    }

    public void setBrojGubitaka(int brojGubitaka) {
        this.brojGubitaka = brojGubitaka;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public ArrayList<Igrac> getIgraci() {
        return igraci;
    }

    public void setIgraci(ArrayList<Igrac> igraci) {
        this.igraci = igraci;
    }
}
