package com.faz.heritage.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Column;

/**
 * Sous-classe representant une berline urbaine.
 * Stockee dans la meme table que EnginMoteurFazzBout (SINGLE_TABLE).
 */
@Entity
@DiscriminatorValue("BERLINE")
public class BerlineUrbaineFazzBout extends EnginMoteurFazzBout {

    @Column(name = "nb_portieres_fzb")
    private int nbPortieresFzb;

    @Column(name = "type_carburant_fzb")
    private String typeCarburantFzb;

    @Column(name = "consommation_l100_fzb")
    private double consommationL100Fzb;

    @Column(name = "est_hybride_fzb")
    private boolean estHybrideFzb;

    // ==================== CONSTRUCTEURS ====================

    public BerlineUrbaineFazzBout() {}

    public BerlineUrbaineFazzBout(String fabricantFzb, int nbPortieresFzb) {
        super(fabricantFzb);
        this.nbPortieresFzb = nbPortieresFzb;
    }

    public BerlineUrbaineFazzBout(String fabricantFzb, int anneeFabricationFzb,
                                  String numeroSerieFzb, int nbPortieresFzb,
                                  String typeCarburantFzb) {
        super(fabricantFzb, anneeFabricationFzb, numeroSerieFzb);
        this.nbPortieresFzb = nbPortieresFzb;
        this.typeCarburantFzb = typeCarburantFzb;
    }

    // ==================== GETTERS ET SETTERS ====================

    public int getNbPortieresFzb() { return nbPortieresFzb; }
    public void setNbPortieresFzb(int nbPortieresFzb) { this.nbPortieresFzb = nbPortieresFzb; }

    public String getTypeCarburantFzb() { return typeCarburantFzb; }
    public void setTypeCarburantFzb(String typeCarburantFzb) { this.typeCarburantFzb = typeCarburantFzb; }

    public double getConsommationL100Fzb() { return consommationL100Fzb; }
    public void setConsommationL100Fzb(double consommationL100Fzb) { this.consommationL100Fzb = consommationL100Fzb; }

    public boolean isEstHybrideFzb() { return estHybrideFzb; }
    public void setEstHybrideFzb(boolean estHybrideFzb) { this.estHybrideFzb = estHybrideFzb; }

    // ==================== METHODES METIER PERSONNALISEES ====================

    /**
     * Verifie si la berline est consideree comme "familiale" (4 ou 5 portieres).
     */
    public boolean estBerlineFamilialeFzb() {
        return nbPortieresFzb >= 4;
    }

    /**
     * Verifie si la consommation est raisonnable (moins de 6 L/100km).
     */
    public boolean aConsommationReduiteFzb() {
        return consommationL100Fzb > 0 && consommationL100Fzb < 6.0;
    }

    /**
     * Calcule le cout estime pour un trajet donne (en euros).
     * @param distanceKm distance du trajet
     * @param prixCarburant prix du carburant au litre
     * @return cout estime ou -1 si la consommation n'est pas definie
     */
    public double calculerCoutTrajetFzb(double distanceKm, double prixCarburant) {
        if (consommationL100Fzb <= 0) {
            return -1;
        }
        double litresNecessaires = (consommationL100Fzb * distanceKm) / 100.0;
        return litresNecessaires * prixCarburant;
    }

    /**
     * Retourne une etiquette descriptive de la berline.
     */
    public String obtenirEtiquetteFzb() {
        String hybride = estHybrideFzb ? " [HYBRIDE]" : "";
        return "BERLINE " + getFabricantFzb() + " (" + nbPortieresFzb + " portes)" + hybride;
    }

    // ==================== TOSTRING ====================

    @Override
    public String toString() {
        return "BerlineUrbaineFazzBout [id=" + getId()
                + ", fabricant=" + getFabricantFzb()
                + ", nbPortieres=" + nbPortieresFzb
                + ", carburant=" + typeCarburantFzb
                + ", consommation=" + consommationL100Fzb + "L/100km"
                + ", hybride=" + estHybrideFzb + "]";
    }
}