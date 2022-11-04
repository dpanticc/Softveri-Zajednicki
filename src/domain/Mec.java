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
public class Mec extends AbstractDomainObject implements Serializable {

    private Long mecID;
    private int dobijeneRundeCrveni;
    private int dobijeneRundePlavi;
    private String MVP;
    private Tim crveniTim;
    private Tim plaviTim;
    private Grad grad;
    private Administrator administrator;
    private ArrayList<StatistikaIgraca> stavkeStatistike;

    public Mec() {
    }

    public Mec(Long mecID, int dobijeneRundeCrveni, int dobijeneRundePlavi, String MVP, Tim crveniTim, Tim plaviTim, Grad grad, Administrator administrator, ArrayList<StatistikaIgraca> stavkeStatistike) {
        this.mecID = mecID;
        this.dobijeneRundeCrveni = dobijeneRundeCrveni;
        this.dobijeneRundePlavi = dobijeneRundePlavi;
        this.MVP = MVP;
        this.crveniTim = crveniTim;
        this.plaviTim = plaviTim;
        this.grad = grad;
        this.administrator = administrator;
        this.stavkeStatistike = stavkeStatistike;
    }

    @Override
    public String nazivTabele() {
        return " mec ";
    }

    @Override
    public String alijas() {
        return " m ";
    }

    @Override
    public String join() {
        return " JOIN tim crveni ON (m.crveniTimID = crveni.timID) "
                + "JOIN tim plavi ON (m.plaviTimID = plavi.timID) "
                + "JOIN grad crveniGrad ON (crveni.gradid = crveniGrad.gradid) "
                + "JOIN grad plaviGrad ON (plavi.gradid = plaviGrad.gradid)"
                + "JOIN grad g ON (g.gradid = m.gradid) "
                + "JOIN administrator a on (a.administratorid = m.administratorid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Grad crveniGrad = new Grad(rs.getLong(21), rs.getString(22));
            
            Grad plaviGrad = new Grad(rs.getLong(23), rs.getString(24));
            
            Grad g = new Grad(rs.getLong(25), rs.getString(26));
            
            Tim crveniTimMeca = new Tim(rs.getLong("TimID"),
                    rs.getString("NazivTima"), rs.getString("Trener"),
                    rs.getInt("BrojPobeda"), rs.getInt("BrojGubitaka"), crveniGrad, null);
            
            Tim plaviTimMeca = new Tim(rs.getLong(15),
                    rs.getString(16), rs.getString(17),
                    rs.getInt(18), rs.getInt(19), plaviGrad, null);
            
            Mec m = new Mec(rs.getLong("MecID"), rs.getInt("DobijeneRundeCrveni"), 
                    rs.getInt("DobijeneRundePlavi"), rs.getString("MVP"), 
                    crveniTimMeca, plaviTimMeca, g, a, null);

            lista.add(m);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DobijeneRundeCrveni, DobijeneRundePlavi, MVP, CrveniTimID, PlaviTimID, GradID, AdministratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " MecID = " + mecID;
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + dobijeneRundeCrveni + ", " + dobijeneRundePlavi + ", "
                + "'" + MVP + "', " + crveniTim.getTimID() + ", "
                + plaviTim.getTimID() + ", " + grad.getGradID() 
                + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getMecID() {
        return mecID;
    }

    public void setMecID(Long mecID) {
        this.mecID = mecID;
    }

    public int getDobijeneRundeCrveni() {
        return dobijeneRundeCrveni;
    }

    public void setDobijeneRundeCrveni(int dobijeneRundeCrveni) {
        this.dobijeneRundeCrveni = dobijeneRundeCrveni;
    }

    public int getDobijeneRundePlavi() {
        return dobijeneRundePlavi;
    }

    public void setDobijeneRundePlavi(int dobijeneRundePlavi) {
        this.dobijeneRundePlavi = dobijeneRundePlavi;
    }

    public Tim getCrveniTim() {
        return crveniTim;
    }

    public void setCrveniTim(Tim crveniTim) {
        this.crveniTim = crveniTim;
    }

    public Tim getPlaviTim() {
        return plaviTim;
    }

    public void setPlaviTim(Tim plaviTim) {
        this.plaviTim = plaviTim;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public String getMVP() {
        return MVP;
    }

    public void setMVP(String MVP) {
        this.MVP = MVP;
    }

    public ArrayList<StatistikaIgraca> getStavkeStatistike() {
        return stavkeStatistike;
    }

    public void setStavkeStatistike(ArrayList<StatistikaIgraca> stavkeStatistike) {
        this.stavkeStatistike = stavkeStatistike;
    }
}
