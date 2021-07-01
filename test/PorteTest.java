import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
public class PorteTest {
    Ascenceur ascenceur;
    @Before
    public void setUp()
    {
        //on initialise les composantes nécessaire au teste des méthodes de la classe Porte
        LinkedList<Usager> usagerapp = new LinkedList<>();
        LinkedList<Usager> usagerdestination = new LinkedList<>();
        ascenceur = new Ascenceur(1, usagerapp, usagerdestination, true, true);
    }
    @Test
    public void TestFermerPorte()
    {
        System.out.println("Test : FermerPorte()");
        //on configure les conditions nécessaire au déroulement de la méthode
        Porte p = new Porte(1,'O');
        assertTrue(p.FermerPorte());
    }
    @Test
    public void TestOuverturePorte()
    {
        System.out.println("Test : OuverturePorte()()");
        //on configure les conditions nécessaire au déroulement de la méthode
        Porte p = new Porte(1,'F');
        Ascenceur.arret=false;
        assertTrue(p.OuverturePorte());
    }
}
