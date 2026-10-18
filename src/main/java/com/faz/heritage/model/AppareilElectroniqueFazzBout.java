package com.faz.heritage.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "app_elec_fzb")
public class AppareilElectroniqueFazzBout extends ArticleCommercialFazzBout {

    @Column(name = "gar_mois_fzb")
    private int garMoisFzb;

    @Column(name = "mrq_fzb")
    private String mrqFzb;

    @Column(name = "mdl_fzb")
    private String mdlFzb;

    @Column(name = "tens_v_fzb")
    private int tensVFzb;

    @Column(name = "est_recond_fzb")
    private boolean estRecondFzb;

    @Column(name = "dt_fin_gar_fzb")
    private LocalDate dtFinGarFzb;

    public AppareilElectroniqueFazzBout() {}

    public AppareilElectroniqueFazzBout(String libFzb, int garMoisFzb) {
        super(libFzb);
        this.garMoisFzb = garMoisFzb;
    }

    public AppareilElectroniqueFazzBout(String libFzb, double pxUnitFzb, String mrqFzb, int garMoisFzb) {
        super(libFzb, pxUnitFzb, 0);
        this.mrqFzb = mrqFzb;
        this.garMoisFzb = garMoisFzb;
    }

    public int getGarMoisFzb() { return garMoisFzb; }
    public void setGarMoisFzb(int garMoisFzb) { this.garMoisFzb = garMoisFzb; }

    public String getMrqFzb() { return mrqFzb; }
    public void setMrqFzb(String mrqFzb) { this.mrqFzb = mrqFzb; }

    public String getMdlFzb() { return mdlFzb; }
    public void setMdlFzb(String mdlFzb) { this.mdlFzb = mdlFzb; }

    public int getTensVFzb() { return tensVFzb; }
    public void setTensVFzb(int tensVFzb) { this.tensVFzb = tensVFzb; }

    public boolean isEstRecondFzb() { return estRecondFzb; }
    public void setEstRecondFzb(boolean estRecondFzb) { this.estRecondFzb = estRecondFzb; }

    public LocalDate getDtFinGarFzb() { return dtFinGarFzb; }
    public void setDtFinGarFzb(LocalDate dtFinGarFzb) { this.dtFinGarFzb = dtFinGarFzb; }

    public boolean aGarLongFzb() {
        return garMoisFzb > 24;
    }

    public boolean estSousGarFzb() {
        if (dtFinGarFzb == null) return false;
        return dtFinGarFzb.isAfter(LocalDate.now());
    }

    public long calcMoisRestGarFzb() {
        if (dtFinGarFzb == null || !estSousGarFzb()) return 0;
        return java.time.temporal.ChronoUnit.MONTHS.between(LocalDate.now(), dtFinGarFzb);
    }

    public boolean estHdgFzb() {
        return getPxUnitFzb() > 800 && aGarLongFzb();
    }

    public boolean estDomFzb() {
        return tensVFzb == 220 || tensVFzb == 230;
    }

    public String getEtiqFzb() {
        String etat = estRecondFzb ? " [RECOND]" : "";
        String gam = estHdgFzb() ? " [HDG]" : "";
        return mrqFzb + " " + mdlFzb + etat + gam;
    }

    @Override
    public String toString() {
        return "AppareilElectroniqueFazzBout [id=" + getId() + ", lib=" + getLibFzb()
                + ", mrq=" + mrqFzb + ", mdl=" + mdlFzb
                + ", gar=" + garMoisFzb + "mois, tens=" + tensVFzb + "V"
                + ", recond=" + estRecondFzb + "]";
    }
}