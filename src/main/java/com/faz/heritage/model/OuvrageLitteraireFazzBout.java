package com.faz.heritage.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ouv_litt_fzb")
public class OuvrageLitteraireFazzBout extends ArticleCommercialFazzBout {

    @Column(name = "aut_pr_fzb")
    private String autPrFzb;

    @Column(name = "edit_fzb")
    private String editFzb;

    @Column(name = "nb_pag_fzb")
    private int nbPagFzb;

    @Column(name = "isbn_fzb", unique = true)
    private String isbnFzb;

    @Column(name = "dt_pub_fzb")
    private LocalDate dtPubFzb;

    @Column(name = "gen_litt_fzb")
    private String genLittFzb;

    public OuvrageLitteraireFazzBout() {}

    public OuvrageLitteraireFazzBout(String libFzb, String autPrFzb) {
        super(libFzb);
        this.autPrFzb = autPrFzb;
    }

    public OuvrageLitteraireFazzBout(String libFzb, double pxUnitFzb, String autPrFzb, int nbPagFzb) {
        super(libFzb, pxUnitFzb, 0);
        this.autPrFzb = autPrFzb;
        this.nbPagFzb = nbPagFzb;
    }

    public String getAutPrFzb() { return autPrFzb; }
    public void setAutPrFzb(String autPrFzb) { this.autPrFzb = autPrFzb; }

    public String getEditFzb() { return editFzb; }
    public void setEditFzb(String editFzb) { this.editFzb = editFzb; }

    public int getNbPagFzb() { return nbPagFzb; }
    public void setNbPagFzb(int nbPagFzb) { this.nbPagFzb = nbPagFzb; }

    public String getIsbnFzb() { return isbnFzb; }
    public void setIsbnFzb(String isbnFzb) { this.isbnFzb = isbnFzb; }

    public LocalDate getDtPubFzb() { return dtPubFzb; }
    public void setDtPubFzb(LocalDate dtPubFzb) { this.dtPubFzb = dtPubFzb; }

    public String getGenLittFzb() { return genLittFzb; }
    public void setGenLittFzb(String genLittFzb) { this.genLittFzb = genLittFzb; }

    public boolean estOuvVolFzb() {
        return nbPagFzb > 500;
    }

    public boolean estLivPocheFzb() {
        return nbPagFzb > 0 && nbPagFzb < 200;
    }

    public double estTpsLectFzb() {
        if (nbPagFzb <= 0) return 0;
        return nbPagFzb / 50.0;
    }

    public boolean aIsbnValFzb() {
        if (isbnFzb == null) return false;
        String net = isbnFzb.replaceAll("[^0-9]", "");
        return net.length() == 13;
    }

    public double calcPrixPagFzb() {
        if (nbPagFzb <= 0) return 0;
        return getPxUnitFzb() / nbPagFzb;
    }

    public String getEtiqFzb() {
        String fmt = estOuvVolFzb() ? "PAVE" : (estLivPocheFzb() ? "POCHE" : "STD");
        return "[" + fmt + "] " + getLibFzb() + " par " + autPrFzb;
    }

    @Override
    public String toString() {
        return "OuvrageLitteraireFazzBout [id=" + getId() + ", lib=" + getLibFzb()
                + ", aut=" + autPrFzb + ", edit=" + editFzb
                + ", pag=" + nbPagFzb + ", isbn=" + isbnFzb + "]";
    }
}