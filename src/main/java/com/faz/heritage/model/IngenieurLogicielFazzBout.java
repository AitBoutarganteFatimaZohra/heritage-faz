package com.faz.heritage.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Sous-classe representant un ingenieur logiciel.
 * Strategie JOINED : une table dediee "ingenieur_logiciel_fazz_bout"
 * sera liee a la table parente par une cle etrangere.
 */
@Entity
@Table(name = "ingenieur_logiciel_fazz_bout")
@PrimaryKeyJoinColumn(name = "id_ingenieur_fzb")
public class IngenieurLogicielFazzBout extends CollaborateurFazzBout {

    @Column(name = "langage_principal_fzb")
    private String langagePrincipalFzb;

    @Column(name = "annees_experience_fzb")
    private int anneesExperienceFzb;

    @Column(name = "est_certifie_fzb")
    private boolean estCertifieFzb;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "ingenieur_competences_fazz_bout",
            joinColumns = @JoinColumn(name = "id_ingenieur_fzb")
    )
    @Column(name = "competence_fzb")
    private Set<String> competencesFzb = new HashSet<>();

    // ==================== CONSTRUCTEURS ====================

    public IngenieurLogicielFazzBout() {}

    public IngenieurLogicielFazzBout(String nomCompletFzb, String langagePrincipalFzb) {
        super(nomCompletFzb);
        this.langagePrincipalFzb = langagePrincipalFzb;
    }

    public IngenieurLogicielFazzBout(String nomCompletFzb, String matriculeFzb,
                                     String langagePrincipalFzb, int anneesExperienceFzb) {
        super(nomCompletFzb, matriculeFzb, null, 0);
        this.langagePrincipalFzb = langagePrincipalFzb;
        this.anneesExperienceFzb = anneesExperienceFzb;
    }

    // ==================== GETTERS ET SETTERS ====================

    public String getLangagePrincipalFzb() { return langagePrincipalFzb; }
    public void setLangagePrincipalFzb(String langagePrincipalFzb) { this.langagePrincipalFzb = langagePrincipalFzb; }

    public int getAnneesExperienceFzb() { return anneesExperienceFzb; }
    public void setAnneesExperienceFzb(int anneesExperienceFzb) { this.anneesExperienceFzb = anneesExperienceFzb; }

    public boolean isEstCertifieFzb() { return estCertifieFzb; }
    public void setEstCertifieFzb(boolean estCertifieFzb) { this.estCertifieFzb = estCertifieFzb; }

    public Set<String> getCompetencesFzb() { return competencesFzb; }
    public void setCompetencesFzb(Set<String> competencesFzb) { this.competencesFzb = competencesFzb; }

    // ==================== METHODES METIER PERSONNALISEES ====================

    /**
     * Verifie si l'ingenieur est considere comme "senior" (plus de 5 ans d'experience).
     */
    public boolean estSeniorFzb() {
        return anneesExperienceFzb > 5;
    }

    /**
     * Verifie si l'ingenieur est considere comme "expert" (plus de 10 ans).
     */
    public boolean estExpertFzb() {
        return anneesExperienceFzb > 10;
    }

    /**
     * Ajoute une competence a l'ingenieur (evite les doublons).
     */
    public void ajouterCompetenceFzb(String competence) {
        if (competence != null && !competence.trim().isEmpty()) {
            this.competencesFzb.add(competence.trim());
        }
    }

    /**
     * Verifie si l'ingenieur maitrise une competence donnee.
     */
    public boolean maitriseCompetenceFzb(String competence) {
        return competencesFzb.contains(competence);
    }

    /**
     * Calcule un score de qualification (sur 100).
     * Base sur l'experience, les competences et la certification.
     */
    public int calculerScoreQualificationFzb() {
        int score = 0;
        score += Math.min(anneesExperienceFzb * 5, 50);  // Max 50 points pour l'experience
        score += Math.min(competencesFzb.size() * 5, 30); // Max 30 points pour les competences
        score += estCertifieFzb ? 20 : 0;                 // 20 points si certifie
        return Math.min(score, 100);
    }

    /**
     * Retourne une etiquette descriptive de l'ingenieur.
     */
    public String obtenirEtiquetteFzb() {
        String niveau = estExpertFzb() ? "EXPERT" : (estSeniorFzb() ? "SENIOR" : "JUNIOR");
        return niveau + " - " + langagePrincipalFzb + " (" + anneesExperienceFzb + " ans)";
    }

    // ==================== TOSTRING ====================

    @Override
    public String toString() {
        return "IngenieurLogicielFazzBout [id=" + getId()
                + ", nom=" + getNomCompletFzb()
                + ", langage=" + langagePrincipalFzb
                + ", experience=" + anneesExperienceFzb + " ans"
                + ", certifie=" + estCertifieFzb
                + ", competences=" + competencesFzb.size() + "]";
    }
}