package com.faz.heritage.test;

import com.faz.heritage.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class HeritageFazTest {

    private EntityManagerFactory emfFzb;
    private EntityManager emFzb;

    @Before
    public void setUp() {
        emfFzb = Persistence.createEntityManagerFactory("UniteHeritageFaz");
        emFzb = emfFzb.createEntityManager();
    }

    @After
    public void tearDown() {
        emFzb.close();
        emfFzb.close();
    }

    @Test
    public void testPersistanceSingleTable() {
        emFzb.getTransaction().begin();

        BerlineUrbaineFazzBout berline = new BerlineUrbaineFazzBout();
        berline.setFabricantFzb("Toyota");
        berline.setNbPortieresFzb(4);
        emFzb.persist(berline);

        DeuxRouesFazzBout deuxRoues = new DeuxRouesFazzBout();
        deuxRoues.setFabricantFzb("Harley");
        deuxRoues.setAvecSidecarFzb(true);
        emFzb.persist(deuxRoues);

        emFzb.getTransaction().commit();

        List<EnginMoteurFazzBout> engins = emFzb.createQuery(
                "SELECT e FROM EnginMoteurFazzBout e", EnginMoteurFazzBout.class).getResultList();
        assertEquals(2, engins.size());
        assertTrue(engins.get(0) instanceof BerlineUrbaineFazzBout);
        assertTrue(engins.get(1) instanceof DeuxRouesFazzBout);
    }

    @Test
    public void testPersistanceJoined() {
        emFzb.getTransaction().begin();

        IngenieurLogicielFazzBout ingenieur = new IngenieurLogicielFazzBout();
        ingenieur.setNomCompletFzb("Alice Martin");
        ingenieur.setLangagePrincipalFzb("Java");
        emFzb.persist(ingenieur);

        ChefDeProjetFazzBout chef = new ChefDeProjetFazzBout();
        chef.setNomCompletFzb("Bob Dupont");
        chef.setNbEquipesGereesFzb(3);
        emFzb.persist(chef);

        emFzb.getTransaction().commit();

        List<CollaborateurFazzBout> collaborateurs = emFzb.createQuery(
                "SELECT c FROM CollaborateurFazzBout c", CollaborateurFazzBout.class).getResultList();
        assertEquals(2, collaborateurs.size());
        assertTrue(collaborateurs.get(0) instanceof IngenieurLogicielFazzBout);
        assertTrue(collaborateurs.get(1) instanceof ChefDeProjetFazzBout);
    }

    @Test
    public void testPersistanceTablePerClass() {
        emFzb.getTransaction().begin();

        OuvrageLitteraireFazzBout ouvrage = new OuvrageLitteraireFazzBout();
        ouvrage.setLibFzb("Clean Code");
        ouvrage.setAutPrFzb("Robert C. Martin");
        emFzb.persist(ouvrage);

        AppareilElectroniqueFazzBout appareil = new AppareilElectroniqueFazzBout();
        appareil.setLibFzb("Smartphone XYZ");
        appareil.setMrqFzb("Samsung");
        emFzb.persist(appareil);

        emFzb.getTransaction().commit();

        List<OuvrageLitteraireFazzBout> ouvrages = emFzb.createQuery(
                "SELECT o FROM OuvrageLitteraireFazzBout o", OuvrageLitteraireFazzBout.class).getResultList();
        assertEquals(1, ouvrages.size());

        List<AppareilElectroniqueFazzBout> appareils = emFzb.createQuery(
                "SELECT a FROM AppareilElectroniqueFazzBout a", AppareilElectroniqueFazzBout.class).getResultList();
        assertEquals(1, appareils.size());
    }

    @Test
    public void testLecturePolymorphique() {
        emFzb.getTransaction().begin();

        BerlineUrbaineFazzBout berline = new BerlineUrbaineFazzBout();
        berline.setFabricantFzb("Toyota");
        emFzb.persist(berline);

        OuvrageLitteraireFazzBout ouvrage = new OuvrageLitteraireFazzBout();
        ouvrage.setLibFzb("Clean Code");
        emFzb.persist(ouvrage);

        emFzb.getTransaction().commit();

        List<EnginMoteurFazzBout> engins = emFzb.createQuery(
                "SELECT e FROM EnginMoteurFazzBout e", EnginMoteurFazzBout.class).getResultList();
        assertEquals(1, engins.size());
        assertTrue(engins.get(0) instanceof BerlineUrbaineFazzBout);

        List<ArticleCommercialFazzBout> articles = emFzb.createQuery(
                "SELECT a FROM ArticleCommercialFazzBout a", ArticleCommercialFazzBout.class).getResultList();
        assertEquals(1, articles.size());
        assertTrue(articles.get(0) instanceof OuvrageLitteraireFazzBout);
    }
}