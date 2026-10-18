package com.faz.heritage.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe de base abstraite representant un engin motorise.
 * Strategie d'heritage : SINGLE_TABLE (toutes les sous-classes dans une seule table).
 * Le champ discriminant "categorie_engin_fzb" identifie le type reel de l'entite.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "categorie_engin_fzb", discriminatorType = DiscriminatorType.STRING)
public abstract class EnginMoteurFazzBout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_engin_fzb")
    private Long id;

    @Column(name = "fabricant_fzb", nullable = false)
    private String fabricantFzb;

    @Column(name = "annee_fabrication_fzb")
    private int anneeFabricationFzb;

    @Column(name = "numero_serie_fzb", unique = true)
    private String numeroSerieFzb;

    @Column(name = "date_mise_circulation_fzb")
    private LocalDate dateMiseCirculationFzb;

    // ==================== CONSTRUCTEURS ====================

    public EnginMoteurFazzBout() {}

    public EnginMoteurFazzBout(String fabricantFzb) {
        this.fabricantFzb = fabricantFzb;
    }

    public EnginMoteurFazzBout(String fabricantFzb, int anneeFabricationFzb, String numeroSerieFzb) {
        this.fabricantFzb = fabricantFzb;
        this.anneeFabricationFzb = anneeFabricationFzb;
        this.numeroSerieFzb = numeroSerieFzb;
    }

    // ==================== GETTERS ET SETTERS ====================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFabricantFzb() { return fabricantFzb; }
    public void setFabricantFzb(String fabricantFzb) { this.fabricantFzb = fabricantFzb; }

    public int getAnneeFabricationFzb() { return anneeFabricationFzb; }
    public void setAnneeFabricationFzb(int anneeFabricationFzb) { this.anneeFabricationFzb = anneeFabricationFzb; }

    public String getNumeroSerieFzb() { return numeroSerieFzb; }
    public void setNumeroSerieFzb(String numeroSerieFzb) { this.numeroSerieFzb = numeroSerieFzb; }

    public LocalDate getDateMiseCirculationFzb() { return dateMiseCirculationFzb; }
    public void setDateMiseCirculationFzb(LocalDate dateMiseCirculationFzb) { this.dateMiseCirculationFzb = dateMiseCirculationFzb; }

    // ==================== METHODES METIER PERSONNALISEES ====================

    /**
     * Calcule l'age de l'engin en annees.
     * Retourne -1 si l'annee de fabrication n'est pas definie.
     */
    public int calculerAgeEnginFzb() {
        if (anneeFabricationFzb <= 0) {
            return -1;
        }
        return LocalDate.now().getYear() - anneeFabricationFzb;
    }

    /**
     * Verifie si l'engin est considere comme "ancien" (plus de 15 ans).
     */
    public boolean estEnginAncienFzb() {
        int age = calculerAgeEnginFzb();
        return age > 15;
    }

    /**
     * Retourne une description courte de l'engin.
     * Format : "FABRICANT (ANNEE)" ou "FABRICANT (annee inconnue)".
     */
    public String obtenirDescriptionCourteFzb() {
        if (anneeFabricationFzb <= 0) {
            return fabricantFzb + " (annee inconnue)";
        }
        return fabricantFzb + " (" + anneeFabricationFzb + ")";
    }

    /**
     * Verifie si l'engin a un numero de serie valide (non vide).
     */
    public boolean aNumeroSerieValideFzb() {
        return numeroSerieFzb != null && !numeroSerieFzb.trim().isEmpty();
    }

    // ==================== TOSTRING ====================

    @Override
    public String toString() {
        return "EnginMoteurFazzBout [id=" + id
                + ", fabricant=" + fabricantFzb
                + ", annee=" + anneeFabricationFzb
                + ", serie=" + numeroSerieFzb + "]";
    }
}