import enu.Direction;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class UsagerTest {
    Ascenceur ascenceur;
    LinkedList<Porte> portes = new LinkedList<>();
    @Before
    public void setUp()
    {
        //on initialise les composantes nécessaire au teste des méthodes de la classe Usager
        portes.add(new Porte(0,'F'));//RDC
        portes.add(new Porte(1,'F'));//premier étage
        portes.add(new Porte(2,'F'));//deuxième étage
        portes.add(new Porte(3,'F'));//troisème étage
        portes.add(new Porte(4,'F'));//quatrième étage
        portes.add(new Porte(5,'F'));//cinquième étage
        portes.add(new Porte(6,'F'));//sixième étage
        portes.add(new Porte(7,'F'));//septième étage
        portes.add(new Porte(8,'F'));//huitième étage
        portes.add(new Porte(9,'F'));//neuvième étage
        portes.add(new Porte(10,'F'));//dixième étage
        LinkedList<Usager> usagerapp = new LinkedList<>();
        LinkedList<Usager> usagerdestination = new LinkedList<>();
        ascenceur = new Ascenceur(1, usagerapp, usagerdestination, true, true);
    }
    @Test
    public void TestIsDirection()
    {
        System.out.println("Test : IsDirection()");
        //on configure les conditions nécessaire au déroulement de la méthode
        Usager u = new Usager(2, Direction.Up, 3);
        Ascenceur.direction = Direction.Up;
        assertTrue(u.IsDirection());
    }
    @Test
    public void Testcomportement()
    {
        System.out.println("Test : comportement()");
        //on configure les conditions nécessaire au déroulement de la méthode
        Usager u = new Usager(2, Direction.Up, 3);
        assertTrue(u.comportement(portes));
    }
    @Test
    public void TestSortir()
    {
        System.out.println("Test : Sortir()");
        //on configure les conditions nécessaire au déroulement de la méthode
        Usager u = new Usager(2, Direction.Up, 3);
        u.EstEntree=true;
        Ascenceur.etage = u.destination;
        assertTrue(u.Sortir(portes));
    }
}
