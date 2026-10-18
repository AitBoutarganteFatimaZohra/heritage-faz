package com.faz.heritage.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe de base abstraite representant un collaborateur de l'entreprise.
 * Strategie d'heritage : JOINED (une table par classe).
 * La table "collaborateur_fazz_bout" contient les colonnes communes,
 * et chaque sous-classe aura sa propre table liee par cle etrangere.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "collaborateur_fazz_bout")
public abstract class CollaborateurFazzBout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborateur_fzb")
    private Long id;

    @Column(name = "nom_complet_fzb", nullable = false)
    private String nomCompletFzb;

    @Column(name = "matricule_fzb", unique = true)
    private String matriculeFzb;

    @Column(name = "date_embauche_fzb")
    private LocalDate dateEmbaucheFzb;

    @Column(name = "salaire_base_fzb")
    private double salaireBaseFzb;

    // ==================== CONSTRUCTEURS ====================

    public CollaborateurFazzBout() {}

    public CollaborateurFazzBout(String nomCompletFzb) {
        this.nomCompletFzb = nomCompletFzb;
    }

    public CollaborateurFazzBout(String nomCompletFzb, String matriculeFzb,
                                 LocalDate dateEmbaucheFzb, double salaireBaseFzb) {
        this.nomCompletFzb = nomCompletFzb;
        this.matriculeFzb = matriculeFzb;
        this.dateEmbaucheFzb = dateEmbaucheFzb;
        this.salaireBaseFzb = salaireBaseFzb;
    }

    // ==================== GETTERS ET SETTERS ====================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomCompletFzb() { return nomCompletFzb; }
    public void setNomCompletFzb(String nomCompletFzb) { this.nomCompletFzb = nomCompletFzb; }

    public String getMatriculeFzb() { return matriculeFzb; }
    public void setMatriculeFzb(String matriculeFzb) { this.matriculeFzb = matriculeFzb; }

    public LocalDate getDateEmbaucheFzb() { return dateEmbaucheFzb; }
    public void setDateEmbaucheFzb(LocalDate dateEmbaucheFzb) { this.dateEmbaucheFzb = dateEmbaucheFzb; }

    public double getSalaireBaseFzb() { return salaireBaseFzb; }
    public void setSalaireBaseFzb(double salaireBaseFzb) { this.salaireBaseFzb = salaireBaseFzb; }

    // ==================== METHODES METIER PERSONNALISEES ====================

    /**
     * Calcule l'anciennete du collaborateur en annees.
     * Retourne -1 si la date d'embauche n'est pas definie.
     */
    public int calculerAncienneteFzb() {
        if (dateEmbaucheFzb == null) {
            return -1;
        }
        return LocalDate.now().getYear() - dateEmbaucheFzb.getYear();
    }

    /**
     * Verifie si le collaborateur a droit a une prime d'anciennete (plus de 5 ans).
     */
    public boolean aDroitPrimeAncienneteFzb() {
        return calculerAncienneteFzb() > 5;
    }

    /**
     * Retourne les initiales du collaborateur (ex: "Fatima Zahra Ait" -> "FZA").
     */
    public String obtenirInitialesFzb() {
        if (nomCompletFzb == null || nomCompletFzb.trim().isEmpty()) {
            return "??";
        }
        StringBuilder initiales = new StringBuilder();
        String[] parties = nomCompletFzb.trim().split("\\s+");
        for (String partie : parties) {
            if (!partie.isEmpty()) {
                initiales.append(partie.charAt(0));
            }
        }
        return initiales.toString().toUpperCase();
    }

    /**
     * Verifie si les informations de base sont completes.
     */
    public boolean estProfilCompletFzb() {
        return nomCompletFzb != null && !nomCompletFzb.trim().isEmpty()
                && matriculeFzb != null && !matriculeFzb.trim().isEmpty()
                && dateEmbaucheFzb != null;
    }

    // ==================== TOSTRING ====================

    @Override
    public String toString() {
        return "CollaborateurFazzBout [id=" + id
                + ", nom=" + nomCompletFzb
                + ", matricule=" + matriculeFzb
                + ", embauche=" + dateEmbaucheFzb + "]";
    }
}