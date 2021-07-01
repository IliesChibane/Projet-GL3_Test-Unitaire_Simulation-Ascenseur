import java.util.LinkedList;

import enu.Direction;

public class Ascenceur {

    public static int etage;//variable indiquant l'etage dans lequel se trouve l'ascenseur
    public static Direction direction;//variable indiquant la direction que l'ascenseur doit prendre pour se rendre a sa destination
    public static LinkedList<Usager> destination;//variable indiquant les etage auquel veulent de rendrent les différents usagers
    public static LinkedList<Usager> appels;//variable indiquant les etage ou ont été fait les appels des usagers
    public static Boolean PeutDemarrer;//variable indiquant si l'ascenseur est en mesure de démarer ou non
    public static Boolean arret;//variable indiquant si l'ascenseur est a l'arret ou non

    //constructeur de la classe Ascenceur
    public Ascenceur(int e, LinkedList<Usager> d, LinkedList<Usager> a, Boolean p, Boolean ar)
    {
        etage =e;
        direction = Direction.Null;
        destination = d;
        appels = a;
        PeutDemarrer = p;
        arret = ar;
    }

    //méthode qui simule le changement d'étage de l'ascenseur 
    public void changeretage(Direction d)
    {
        //on vérifie la direction pour savoir si on doit monté ou descendre d'un étage
        if(Ascenceur.direction==Direction.Up)
            Ascenceur.etage++;
        if(Ascenceur.direction==Direction.Down)
            Ascenceur.etage--;
        System.out.println("+ Ascenseur:\t\t+ etage "+Ascenceur.etage);
    }

    //méthode qui vérifie si un usager se trouve a l'étage courant
    public static boolean IsUsagerEtage()
    {
        for(Usager u : appels)
        {
            if(u.etage==Ascenceur.etage)
                return true;
        }
        return false;
    }

    //méthode qui simule le déplacement de l'ascenseur de son étage courant a sa destination
    public boolean DeplacerAscenseur(LinkedList<Porte> portes,int et)
    {
        //on vérifie que toute les portes sont fermé avant de démarrer 
        ToutePorteFermer(portes);
        int i = 0;
            //dans le cas ou toutes les portes sont correcttement fermé
            if(Ascenceur.PeutDemarrer){
                    //on indique que le l'ascenseur n'est pas plus a l'arret
                    Ascenceur.arret = false;
                //tant que l'étage courant de l'ascenseur n'est pas égale a l'étage de la destination
                while(etage!=et)
                {
                    //il prend une seconde pour passer d'un étage a un autre 
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) { System.out.print("Erreur\n"); }
                    //dans le cas il parcours plusieurs étages on affiche sa diretion au passage de chaque étage
                    if(i>0)
                        System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);
                    i++;
                    changeretage(Ascenceur.direction);
                }
            System.out.println("+ Ascenseur:\t\t+ arret a l'etage "+Ascenceur.etage);
            return true;
            }
            else
                return false;
    }

    //méthode qui vérifie si toutes les portes sont fermée
    public void ToutePorteFermer(LinkedList<Porte> portes)
    {
        for(Porte p : portes)
        {
            if(p.etat=='O')
            {
                PeutDemarrer=false;
                break;
            }
        }
    }

    //méthode simulant le comportement globale de l'ascenseur
    public boolean comportement(LinkedList<Porte> portes)
    {
        //tant qu'il y'a des appels
        while(Ascenceur.appels.size()!=0){
                // si l'appel a été effectuer un étage au dessus de celui de l'ascenceur
                if(Ascenceur.etage<Ascenceur.appels.getFirst().etage){
                    //on défini la direction de l'ascenseur sur UP si se n'est pas dèja fait 
                    if(Ascenceur.direction!=Direction.Up){
                        Ascenceur.direction=Direction.Up;
                        System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);}
                }
                // sinon si l'appel a été effectuer un étage en dessous de celui de l'ascenceur
                else{
                    //on défini la direction de l'ascenseur sur DOWN si se n'est pas dèja fait 
                    if(Ascenceur.direction!=Direction.Down){
                        Ascenceur.direction=Direction.Down;
                        System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);}
                }
            //on déplace l'assenceur a la destionation de l'appel
            if(DeplacerAscenseur(portes, Ascenceur.appels.getFirst().etage)){}
            else
                return false;

                /*une fois a l"étage de l'appel on vérifie si l'ascenseur ne possède pas d'usager 
                qui ont leur destionation a cet étage*/
                SupprimerDestination(portes);

            /*une fois l'ascenseur a l'étage de l'appel on lance le comportement de l'usager souhaitant
            rentrer dans l'ascenseur*/
            Ascenceur.appels.getFirst().comportement(portes);
            System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);
            //une fois cela on supprime l'appel
            appels.removeFirst();

            //Changement de direction automatique si l'ascenseur se trouve au RDC ou au dernier étage
            ChangementDeDirection(portes);
        }
        
        //une fois tout les appels effectuer on dépose tout les usagers a leur destination
        while(Ascenceur.destination.size()!=0)
        {
            //si la direction de l'ascenseur ne correspond pas a la direction emmenant a la destination
            //on opère un changement 
            if(Ascenceur.direction!=Ascenceur.destination.getFirst().direction){
                Ascenceur.direction= Ascenceur.destination.getFirst().direction;
                System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);}
            //on déplace l'ascenseur a l'étage de sa destination
            if(DeplacerAscenseur(portes, destination.getFirst().destination)){}
            else
                return false;

            //on dépose les usager concerné 
            SupprimerDestination(portes);
            //Changement de direction automatique si l'ascenseur se trouve au RDC ou au dernier étage
            ChangementDeDirection(portes);
        }
        return true;
    }

    //Changement de direction automatique si l'ascenseur se trouve au RDC ou au dernier étage
    public void ChangementDeDirection(LinkedList<Porte> portes)
    {
        if(portes.getLast().etage==Ascenceur.etage){
            direction = Direction.Down;
            System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);
        }
        else if(portes.getFirst().etage==Ascenceur.etage){
            direction = Direction.Up;
            System.out.println("+ Ascenseur:\t\t+ direction "+Ascenceur.direction);
        }
    }

    //Supprime les destinations des usagers sorties de l'ascenseur
    public void SupprimerDestination(LinkedList<Porte> portes)
    {
        LinkedList<Usager> d = new LinkedList<>();
        for(Usager u : Ascenceur.destination)
        {
            if(u.destination==Ascenceur.etage)
                //si c'est le cas on les fait sortir de l'ascenseur 
                if(u.Sortir(portes)){
                    d.add(u);
                }
        }
        //on supprime leur destionation de la liste
        Ascenceur.destination.removeAll(d);
    }
}

