import enu.Direction;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class AscenceurTest {
    LinkedList<Usager> usagerapp = new LinkedList<>();
    LinkedList<Usager> usagerdestination = new LinkedList<>();
    LinkedList<Porte> portes = new LinkedList<>();
    Ascenceur ascenceur = new Ascenceur(1, usagerapp, usagerdestination, true, true);
    @Before
    public void setUp()
    {
        System.out.println("Debut Simulation");
        //on initialise les composantes nécessaire au teste des méthodes de la classe ascenseur
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
        Usager u1 = new Usager(2, Direction.Up, 3);
        Usager u2 = new Usager(3, Direction.Down, 1);
        u1.comportement(portes);
        u2.comportement(portes);

    }
    //teste du comportement globale de l'accennseur
    @Test
    public void TestComportement()
    {
        assertTrue(ascenceur.comportement(portes));
        System.out.println("Fin Simulation");
    }
    //cette classe contenant l'intégralité des autres méthodes de la classe la testé uniquement est suffisant
}
