package com.faz.heritage.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Column;

/**
 * Sous-classe representant un deux-roues.
 * Stockee dans la meme table que EnginMoteurFazzBout (SINGLE_TABLE).
 */
@Entity
@DiscriminatorValue("DEUX_ROUES")
public class DeuxRouesFazzBout extends EnginMoteurFazzBout {

    @Column(name = "avec_sidecar_fzb")
    private boolean avecSidecarFzb;

    @Column(name = "cylindree_cm3_fzb")
    private int cylindreeCm3Fzb;

    @Column(name = "type_permis_requis_fzb")
    private String typePermisRequisFzb;

    @Column(name = "est_electrique_fzb")
    private boolean estElectriqueFzb;

    // ==================== CONSTRUCTEURS ====================

    public DeuxRouesFazzBout() {}

    public DeuxRouesFazzBout(String fabricantFzb, boolean avecSidecarFzb) {
        super(fabricantFzb);
        this.avecSidecarFzb = avecSidecarFzb;
    }

    public DeuxRouesFazzBout(String fabricantFzb, int anneeFabricationFzb,
                             String numeroSerieFzb, int cylindreeCm3Fzb,
                             String typePermisRequisFzb) {
        super(fabricantFzb, anneeFabricationFzb, numeroSerieFzb);
        this.cylindreeCm3Fzb = cylindreeCm3Fzb;
        this.typePermisRequisFzb = typePermisRequisFzb;
    }

    // ==================== GETTERS ET SETTERS ====================

    public boolean isAvecSidecarFzb() { return avecSidecarFzb; }
    public void setAvecSidecarFzb(boolean avecSidecarFzb) { this.avecSidecarFzb = avecSidecarFzb; }

    public int getCylindreeCm3Fzb() { return cylindreeCm3Fzb; }
    public void setCylindreeCm3Fzb(int cylindreeCm3Fzb) { this.cylindreeCm3Fzb = cylindreeCm3Fzb; }

    public String getTypePermisRequisFzb() { return typePermisRequisFzb; }
    public void setTypePermisRequisFzb(String typePermisRequisFzb) { this.typePermisRequisFzb = typePermisRequisFzb; }

    public boolean isEstElectriqueFzb() { return estElectriqueFzb; }
    public void setEstElectriqueFzb(boolean estElectriqueFzb) { this.estElectriqueFzb = estElectriqueFzb; }

    // ==================== METHODES METIER PERSONNALISEES ====================

    /**
     * Verifie si le deux-roues est une "grosse cylindree" (plus de 500 cm3).
     */
    public boolean estGrosseCylindreeFzb() {
        return cylindreeCm3Fzb > 500;
    }

    /**
     * Verifie si le deux-roues necessite un permis A (grosse cylindree).
     */
    public boolean necessitePermisAFzb() {
        return estGrosseCylindreeFzb();
    }

    /**
     * Verifie si le deux-roues est un "tricycle" (avec sidecar).
     */
    public boolean estTricycleFzb() {
        return avecSidecarFzb;
    }

    /**
     * Retourne une categorie descriptive du deux-roues.
     */
    public String obtenirCategorieFzb() {
        if (estElectriqueFzb) {
            return "DEUX-ROUES ELECTRIQUE";
        }
        if (estGrosseCylindreeFzb()) {
            return "DEUX-ROUES GROSSE CYLINDREE";
        }
        return "DEUX-ROUES STANDARD";
    }

    /**
     * Verifie si le deux-roues est adapte pour un debutant.
     */
    public boolean estAdapteDebutantFzb() {
        return cylindreeCm3Fzb > 0 && cylindreeCm3Fzb <= 125 && !avecSidecarFzb;
    }

    // ==================== TOSTRING ====================

    @Override
    public String toString() {
        return "DeuxRouesFazzBout [id=" + getId()
                + ", fabricant=" + getFabricantFzb()
                + ", cylindree=" + cylindreeCm3Fzb + "cm3"
                + ", sidecar=" + avecSidecarFzb
                + ", electrique=" + estElectriqueFzb
                + ", permis=" + typePermisRequisFzb + "]";
    }
}