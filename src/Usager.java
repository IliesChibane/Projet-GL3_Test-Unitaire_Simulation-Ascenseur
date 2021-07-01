import java.util.LinkedList;
import java.util.Random;

import enu.Direction;

public class Usager {
    
    public int etage;//variable indiquant l'etage dans lequel se trouve l'usager
    public Direction direction;//variable indiquant la direction que l'usager doit prendre pour se rendre a sa destination
    public int destination;//variable indiquant l'etage auquel l'usager veut se rendre
    private static int nb_usager = 0;//variable indiquant le nombre totale d'usager
    public int num;//variable indiquant le numero de l'usager
    public boolean EstEntree;//variable indiquant si l'usager est a l'intérieur de l'asenceur ou non

    //constructeur de la classe Usager
    public Usager(int etage, Direction direction, int destination) {
        this.etage = etage;
        this.direction = direction;
        this.destination = destination;
        num = nb_usager;
        nb_usager++;
        EstEntree = false;
    }

    //méthode vérifiant si la direction de l'ascenseur est correcte 
    public boolean IsDirection()
    {
        return Ascenceur.direction == this.direction;
    }

    //méthode utiliser pour simuler l'appel de l'usager pour faire venir l'ascenseur
    public void FaireUnAppel()
    {
        Ascenceur.appels.add(this);
        System.out.println("# Usager["+num+"]: \t\t# effectue l'appel "+etage+"-"+direction);
    }

    //méthode simulant le comportement globale de l'usager 
    public boolean comportement(LinkedList<Porte> portes){
        boolean jump = false;
        /*tant que la direction de l'ascenseur est fausse
        que la porte est fermé et que jump = false
        les instructions suivantes sont exécuté en boucle*/
        while(((!IsDirection())||(portes.get(etage).etat=='F'))&&(!jump))
        {
            //dans le cas ou l'usager n'a pas encore faire d'appel 
            if(!Ascenceur.appels.contains(this))
            {
                this.FaireUnAppel();
                jump = true;
            }
            //si il a dèja un appel il exécute les actions suivantes
            else{
                //ouverture de la porte 
                portes.get(etage).OuverturePorte();

                //etant donné que l'usager peut etre distrait nous avons simulé cela avec une variable aléatoire
                //50% d'etre distrait 
                Random rand = new Random();
                boolean b = rand.nextBoolean();
                int TempsAttente = 0;
                while(!b)
                {
                    //l'usager reste distrait pendant une seconde
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) { System.out.print("Erreur\n"); return false;}
                    b = rand.nextBoolean();
                    //on comptabilise le temps globale pendant lequel il est resté distrait
                    TempsAttente+=1000;
                    System.out.println("# Usager["+num+"]: \t\t# est distre il ne peut pas entrer");
                }

                //si il a été distrait pendant 3 secondes ou plus la porte se ferme
                if(TempsAttente>=3000){
                    portes.get(etage).etat='F'; 
                    System.out.println("* PORTE etage "+this.etage+":\t* Fermeture");
                }
                else{
                    //si l'usager a été distrait et que la porte c'est refermé on la re ouvre
                    if(portes.get(etage).etat=='F'){
                        portes.get(etage).etat='O'; 
                        System.out.println("* PORTE etage "+this.etage+":\t* Ouverture");
                    }
                    //par la suite l'usager peut rentrer et indiquer sa destionation 
                    System.out.println("# Usager["+num+"]: \t\t# entre dans l'ascenseur");
                    EstEntree=true;
                    Ascenceur.direction = this.direction;
                }
            }
        }
        /*si l'usager avait dèja fait son appel et par conséquant est rentré dans l'assenceur on exéute cette étpae
        dans le cas contraire on jump cette partie*/
        if(!jump){
        System.out.println("# Usager["+num+"]: \t\t# entre la destination "+this.destination);
        // on ajoute la destination de l'usager a la liste 
        Ascenceur.destination.add(this);
        //on ferme la porte
        portes.get(etage).FermerPorte();}

        return true;
    }

    //méthode qui simule la sortie de l'usager de l'assenceur 
    public boolean Sortir(LinkedList<Porte> portes)
    {
        //dans le cas ou un usager est à l'intérieur de l'ascenseur et qu'il est arrivé a destination
        if((this.destination==Ascenceur.etage)&&(this.EstEntree))
        {
            //on ouvre la porte 
            portes.get(Ascenceur.etage).OuverturePorte();
            System.out.println("# Usager["+num+"]: \t\t# est sortie de l'accenseur");
            //dans le cas ou un usager se trouve a cet etage et désire rentrer on ne ferme pas la porte
            if(!Ascenceur.IsUsagerEtage())
                portes.get(Ascenceur.etage).FermerPorte();
            return true;
        }
        return false;
    }
}
