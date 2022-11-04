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
public class Grad extends AbstractDomainObject implements Serializable {
    
    private Long gradID;
    private String nazivGrada;

    public Grad() {
    }

    public Grad(Long gradID, String nazivGrada) {
        this.gradID = gradID;
        this.nazivGrada = nazivGrada;
    }

    @Override
    public String toString() {
        return nazivGrada;
    }
    
    @Override
    public String nazivTabele() {
        return " grad ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Grad g = new Grad(rs.getLong("GradID"),
                    rs.getString("NazivGrada"));

            lista.add(g);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " GradID = " + gradID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getGradID() {
        return gradID;
    }

    public void setGradID(Long gradID) {
        this.gradID = gradID;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }
}
