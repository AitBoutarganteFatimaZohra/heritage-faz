package com.faz.heritage.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Sous-classe representant un chef de projet.
 * Strategie JOINED : une table dediee "chef_projet_fazz_bout"
 * sera liee a la table parente par une cle etrangere.
 */
@Entity
@Table(name = "chef_projet_fazz_bout")
@PrimaryKeyJoinColumn(name = "id_chef_projet_fzb")
public class ChefDeProjetFazzBout extends CollaborateurFazzBout {

    @Column(name = "nb_equipes_gerees_fzb")
    private int nbEquipesGereesFzb;

    @Column(name = "budget_annuel_fzb")
    private double budgetAnnuelFzb;

    @Column(name = "niveau_management_fzb")
    private String niveauManagementFzb;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "chef_projet_projets_fazz_bout",
            joinColumns = @JoinColumn(name = "id_chef_projet_fzb")
    )
    @Column(name = "nom_projet_fzb")
    private Set<String> projetsGerésFzb = new HashSet<>();

    // ==================== CONSTRUCTEURS ====================

    public ChefDeProjetFazzBout() {}

    public ChefDeProjetFazzBout(String nomCompletFzb, int nbEquipesGereesFzb) {
        super(nomCompletFzb);
        this.nbEquipesGereesFzb = nbEquipesGereesFzb;
    }

    public ChefDeProjetFazzBout(String nomCompletFzb, String matriculeFzb,
                                int nbEquipesGereesFzb, double budgetAnnuelFzb) {
        super(nomCompletFzb, matriculeFzb, null, 0);
        this.nbEquipesGereesFzb = nbEquipesGereesFzb;
        this.budgetAnnuelFzb = budgetAnnuelFzb;
    }

    // ==================== GETTERS ET SETTERS ====================

    public int getNbEquipesGereesFzb() { return nbEquipesGereesFzb; }
    public void setNbEquipesGereesFzb(int nbEquipesGereesFzb) { this.nbEquipesGereesFzb = nbEquipesGereesFzb; }

    public double getBudgetAnnuelFzb() { return budgetAnnuelFzb; }
    public void setBudgetAnnuelFzb(double budgetAnnuelFzb) { this.budgetAnnuelFzb = budgetAnnuelFzb; }

    public String getNiveauManagementFzb() { return niveauManagementFzb; }
    public void setNiveauManagementFzb(String niveauManagementFzb) { this.niveauManagementFzb = niveauManagementFzb; }

    public Set<String> getProjetsGerésFzb() { return projetsGerésFzb; }
    public void setProjetsGerésFzb(Set<String> projetsGerésFzb) { this.projetsGerésFzb = projetsGerésFzb; }

    // ==================== METHODES METIER PERSONNALISEES ====================

    /**
     * Verifie si le chef gere plusieurs equipes (plus de 2).
     */
    public boolean gerePlusieursEquipesFzb() {
        return nbEquipesGereesFzb > 2;
    }

    /**
     * Verifie si le chef est un "manager senior" (plus de 5 equipes).
     */
    public boolean estManagerSeniorFzb() {
        return nbEquipesGereesFzb > 5;
    }

    /**
     * Ajoute un projet au chef de projet.
     */
    public void ajouterProjetFzb(String nomProjet) {
        if (nomProjet != null && !nomProjet.trim().isEmpty()) {
            this.projetsGerésFzb.add(nomProjet.trim());
        }
    }

    /**
     * Calcule le budget par equipe.
     */
    public double calculerBudgetParEquipeFzb() {
        if (nbEquipesGereesFzb <= 0) {
            return 0;
        }
        return budgetAnnuelFzb / nbEquipesGereesFzb;
    }

    /**
     * Evalue la charge de travail (sur une echelle de 1 a 10).
     */
    public int evaluerChargeTravailFzb() {
        int charge = 0;
        charge += Math.min(nbEquipesGereesFzb, 5);        // Max 5 points pour les equipes
        charge += Math.min(projetsGerésFzb.size(), 5);    // Max 5 points pour les projets
        return Math.min(charge, 10);
    }

    /**
     * Retourne une etiquette descriptive du chef.
     */
    public String obtenirEtiquetteFzb() {
        String niveau = estManagerSeniorFzb() ? "SENIOR" : "STANDARD";
        return "CHEF " + niveau + " - " + nbEquipesGereesFzb + " equipes";
    }

    // ==================== TOSTRING ====================

    @Override
    public String toString() {
        return "ChefDeProjetFazzBout [id=" + getId()
                + ", nom=" + getNomCompletFzb()
                + ", equipes=" + nbEquipesGereesFzb
                + ", budget=" + budgetAnnuelFzb
                + ", niveau=" + niveauManagementFzb
                + ", projets=" + projetsGerésFzb.size() + "]";
    }
}