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
public class StatistikaIgraca extends AbstractDomainObject implements Serializable {

    private Mec mec;
    private int rbStatistike;
    private int brojUbistva;
    private int brojSmrti;
    private boolean MVP;
    private Tim tim;
    private Igrac igrac;

    public StatistikaIgraca() {
    }

    public StatistikaIgraca(Mec mec, int rbStatistike, int brojUbistva, int brojSmrti, boolean MVP, Tim tim, Igrac igrac) {
        this.mec = mec;
        this.rbStatistike = rbStatistike;
        this.brojUbistva = brojUbistva;
        this.brojSmrti = brojSmrti;
        this.MVP = MVP;
        this.tim = tim;
        this.igrac = igrac;
    }

    @Override
    public String nazivTabele() {
        return " statistikaIgraca ";
    }

    @Override
    public String alijas() {
        return " si ";
    }

    @Override
    public String join() {
        return " JOIN mec m ON (m.mecid = si.mecid) "
                + "JOIN tim crveni ON (m.crveniTimID = crveni.timID) "
                + "JOIN tim plavi ON (m.plaviTimID = plavi.timID) "
                + "JOIN grad crveniGrad ON (crveni.gradid = crveniGrad.gradid) "
                + "JOIN grad plaviGrad ON (plavi.gradid = plaviGrad.gradid) "
                + "JOIN grad g ON (g.gradid = m.gradid) "
                + "JOIN igrac igrac ON (si.timid = igrac.timid AND si.igracid = igrac.igracid) "
                + "JOIN administrator a ON (a.administratorid = m.administratorid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Grad crveniGrad = new Grad(rs.getLong(28), rs.getString(29));

            Grad plaviGrad = new Grad(rs.getLong(30), rs.getString(31));

            Grad g = new Grad(rs.getLong(32), rs.getString(33));

            Tim crveniTimMeca = new Tim(rs.getLong("TimID"),
                    rs.getString("NazivTima"), rs.getString("Trener"),
                    rs.getInt("BrojPobeda"), rs.getInt("BrojGubitaka"), crveniGrad, null);

            Tim plaviTimMeca = new Tim(rs.getLong(22),
                    rs.getString(23), rs.getString(24),
                    rs.getInt(25), rs.getInt(26), plaviGrad, null);

            Mec m = new Mec(rs.getLong("MecID"), rs.getInt("DobijeneRundeCrveni"),
                    rs.getInt("DobijeneRundePlavi"), rs.getString("MVP"),
                    crveniTimMeca, plaviTimMeca, g, a, null);

            if (rs.getLong(27) == crveniTimMeca.getTimID()) {

                Igrac i = new Igrac(crveniTimMeca, rs.getInt("IgracID"), rs.getString("Nickname"),
                        rs.getString("Ime"), rs.getString("Prezime"));

                StatistikaIgraca si = new StatistikaIgraca(m,
                        rs.getInt("RbStatistike"), rs.getInt("BrojUbistva"),
                        rs.getInt("BrojSmrti"), rs.getBoolean("MVP"), i.getTim(), i);

                lista.add(si);
            } else {

                Igrac i = new Igrac(plaviTimMeca, rs.getInt("IgracID"), rs.getString("Nickname"),
                        rs.getString("Ime"), rs.getString("Prezime"));

                StatistikaIgraca si = new StatistikaIgraca(m,
                        rs.getInt("RbStatistike"), rs.getInt("BrojUbistva"),
                        rs.getInt("BrojSmrti"), rs.getBoolean("MVP"), i.getTim(), i);

                lista.add(si);
            }

        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (MecID, RbStatistike, BrojUbistva, BrojSmrti, MVP, TimID, IgracID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " MecID = " + mec.getMecID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + mec.getMecID() + ", " + rbStatistike + ", "
                + brojUbistva + ", " + brojSmrti + ", "
                + (MVP ? 1 : 0) + ", " + tim.getTimID() + ", " + igrac.getIgracID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE M.MECID = " + mec.getMecID()
                + " GROUP BY si.mecid, si.rbstatistike";
    }

    public Mec getMec() {
        return mec;
    }

    public void setMec(Mec mec) {
        this.mec = mec;
    }

    public int getRbStatistike() {
        return rbStatistike;
    }

    public void setRbStatistike(int rbStatistike) {
        this.rbStatistike = rbStatistike;
    }

    public int getBrojUbistva() {
        return brojUbistva;
    }

    public void setBrojUbistva(int brojUbistva) {
        this.brojUbistva = brojUbistva;
    }

    public int getBrojSmrti() {
        return brojSmrti;
    }

    public void setBrojSmrti(int brojSmrti) {
        this.brojSmrti = brojSmrti;
    }

    public boolean isMVP() {
        return MVP;
    }

    public void setMVP(boolean MVP) {
        this.MVP = MVP;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }
}
