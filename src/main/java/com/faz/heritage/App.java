package com.faz.heritage;

import com.faz.heritage.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        // 1. Initialisation de l'EntityManagerFactory et de l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniteHeritageFaz");
        EntityManager em = emf.createEntityManager();

        try {
            // 2. Démarrer la transaction
            em.getTransaction().begin();

            // ============================================================
            // STRATEGIE 1 : SINGLE_TABLE
            // ============================================================
            BerlineUrbaineFazzBout berline = new BerlineUrbaineFazzBout();
            berline.setFabricantFzb("Toyota");
            berline.setAnneeFabricationFzb(2022);
            berline.setNumeroSerieFzb("BER-001");
            berline.setNbPortieresFzb(4);
            berline.setTypeCarburantFzb("Essence");
            berline.setConsommationL100Fzb(5.5);
            berline.setEstHybrideFzb(true);

            DeuxRouesFazzBout deuxRoues = new DeuxRouesFazzBout();
            deuxRoues.setFabricantFzb("Harley");
            deuxRoues.setAnneeFabricationFzb(2020);
            deuxRoues.setNumeroSerieFzb("MOT-001");
            deuxRoues.setAvecSidecarFzb(true);
            deuxRoues.setCylindreeCm3Fzb(1200);
            deuxRoues.setTypePermisRequisFzb("A");
            deuxRoues.setEstElectriqueFzb(false);

            em.persist(berline);
            em.persist(deuxRoues);

            // ============================================================
            // STRATEGIE 2 : JOINED
            // ============================================================
            IngenieurLogicielFazzBout ingenieur = new IngenieurLogicielFazzBout();
            ingenieur.setNomCompletFzb("Alice Martin");
            ingenieur.setMatriculeFzb("ING-001");
            ingenieur.setDateEmbaucheFzb(LocalDate.of(2019, 3, 15));
            ingenieur.setSalaireBaseFzb(3500);
            ingenieur.setLangagePrincipalFzb("Java");
            ingenieur.setAnneesExperienceFzb(7);
            ingenieur.setEstCertifieFzb(true);
            ingenieur.ajouterCompetenceFzb("Spring");
            ingenieur.ajouterCompetenceFzb("Hibernate");
            ingenieur.ajouterCompetenceFzb("JPA");

            ChefDeProjetFazzBout chef = new ChefDeProjetFazzBout();
            chef.setNomCompletFzb("Bob Dupont");
            chef.setMatriculeFzb("CDP-001");
            chef.setDateEmbaucheFzb(LocalDate.of(2015, 1, 10));
            chef.setSalaireBaseFzb(5000);
            chef.setNbEquipesGereesFzb(3);
            chef.setBudgetAnnuelFzb(150000);
            chef.setNiveauManagementFzb("SENIOR");
            chef.ajouterProjetFzb("Projet Alpha");
            chef.ajouterProjetFzb("Projet Beta");

            em.persist(ingenieur);
            em.persist(chef);

            // ============================================================
            // STRATEGIE 3 : TABLE_PER_CLASS
            // ============================================================
            OuvrageLitteraireFazzBout ouvrage = new OuvrageLitteraireFazzBout();
            ouvrage.setLibFzb("Clean Code");
            ouvrage.setPxUnitFzb(35.0);
            ouvrage.setQteStkFzb(10);
            ouvrage.setAutPrFzb("Robert C. Martin");
            ouvrage.setEditFzb("Prentice Hall");
            ouvrage.setNbPagFzb(464);
            ouvrage.setIsbnFzb("978-0132350884");
            ouvrage.setGenLittFzb("Informatique");
            ouvrage.setDtPubFzb(LocalDate.of(2008, 8, 1));

            AppareilElectroniqueFazzBout appareil = new AppareilElectroniqueFazzBout();
            appareil.setLibFzb("Smartphone XYZ");
            appareil.setPxUnitFzb(899.0);
            appareil.setQteStkFzb(25);
            appareil.setGarMoisFzb(24);
            appareil.setMrqFzb("Samsung");
            appareil.setMdlFzb("Galaxy S24");
            appareil.setTensVFzb(220);
            appareil.setEstRecondFzb(false);
            appareil.setDtFinGarFzb(LocalDate.now().plusMonths(24));

            em.persist(ouvrage);
            em.persist(appareil);

            // 3. Valider la transaction
            em.getTransaction().commit();
            System.out.println("✅ Toutes les entites ont ete persistees avec succes.\n");

            // ============================================================
            // LECTURE POLYMORPHIQUE AVEC JPQL
            // ============================================================

            // --- Liste des engins (SINGLE_TABLE) ---
            System.out.println("--- Liste des engins motorises ---");
            em.createQuery("SELECT e FROM EnginMoteurFazzBout e", EnginMoteurFazzBout.class)
                    .getResultList()
                    .forEach(e -> System.out.println("  " + e.getClass().getSimpleName()
                            + " : " + e.getFabricantFzb()
                            + " (" + e.getAnneeFabricationFzb() + ")"));

            // --- Liste des collaborateurs (JOINED) ---
            System.out.println("\n--- Liste des collaborateurs ---");
            em.createQuery("SELECT c FROM CollaborateurFazzBout c", CollaborateurFazzBout.class)
                    .getResultList()
                    .forEach(c -> System.out.println("  " + c.getClass().getSimpleName()
                            + " : " + c.getNomCompletFzb()
                            + " (matricule=" + c.getMatriculeFzb() + ")"));

            // --- Liste des articles (TABLE_PER_CLASS) ---
            System.out.println("\n--- Liste des articles commerciaux ---");
            em.createQuery("SELECT a FROM ArticleCommercialFazzBout a", ArticleCommercialFazzBout.class)
                    .getResultList()
                    .forEach(a -> System.out.println("  " + a.getClass().getSimpleName()
                            + " : " + a.getLibFzb()
                            + " (prix=" + a.getPxUnitFzb() + " EUR)"));

            System.out.println("\n🎉 Toutes les lectures polymorphiques sont terminees.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("❌ Erreur : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 4. Fermer les ressources
            em.close();
            emf.close();
        }
    }
}