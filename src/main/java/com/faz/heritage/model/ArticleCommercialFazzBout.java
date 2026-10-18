package com.faz.heritage.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ArticleCommercialFazzBout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_art_fzb")
    private Long id;

    @Column(name = "lib_art_fzb", nullable = false)
    private String libFzb;

    @Column(name = "px_unit_fzb")
    private double pxUnitFzb;

    @Column(name = "qte_stk_fzb")
    private int qteStkFzb;

    @Column(name = "dt_aj_stk_fzb")
    private LocalDate dtAjStkFzb;

    public ArticleCommercialFazzBout() {}

    public ArticleCommercialFazzBout(String libFzb) {
        this.libFzb = libFzb;
    }

    public ArticleCommercialFazzBout(String libFzb, double pxUnitFzb, int qteStkFzb) {
        this.libFzb = libFzb;
        this.pxUnitFzb = pxUnitFzb;
        this.qteStkFzb = qteStkFzb;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibFzb() { return libFzb; }
    public void setLibFzb(String libFzb) { this.libFzb = libFzb; }

    public double getPxUnitFzb() { return pxUnitFzb; }
    public void setPxUnitFzb(double pxUnitFzb) { this.pxUnitFzb = pxUnitFzb; }

    public int getQteStkFzb() { return qteStkFzb; }
    public void setQteStkFzb(int qteStkFzb) { this.qteStkFzb = qteStkFzb; }

    public LocalDate getDtAjStkFzb() { return dtAjStkFzb; }
    public void setDtAjStkFzb(LocalDate dtAjStkFzb) { this.dtAjStkFzb = dtAjStkFzb; }

    public double calcValStkFzb() {
        return pxUnitFzb * qteStkFzb;
    }

    public boolean estRupStkFzb() {
        return qteStkFzb <= 0;
    }

    public boolean estStkFaibleFzb() {
        return qteStkFzb > 0 && qteStkFzb < 5;
    }

    public boolean estArtCherFzb() {
        return pxUnitFzb > 500;
    }

    public double calcPrixTtcFzb(double tvaFzb) {
        return pxUnitFzb * (1 + tvaFzb / 100.0);
    }

    public boolean estArtRecentFzb() {
        if (dtAjStkFzb == null) return false;
        return dtAjStkFzb.plusDays(30).isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return "ArticleCommercialFazzBout [id=" + id + ", lib=" + libFzb
                + ", prix=" + pxUnitFzb + ", stock=" + qteStkFzb + "]";
    }
}