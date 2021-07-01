public class Porte {
    
    public int etage; //variable indiquant l'etage de la porte
    public char etat; //variable indiquant si la porte est fermé "F" ou ouvere "O"

    //constructeur de la classe Porte
    public Porte(int etage, char etat) {
        this.etage = etage;
        this.etat = etat;
    }

    //méthode permettant de fermer la porte 
    public boolean FermerPorte()
    {
        //on verifie si la porte est bien a l'arret
        if(Ascenceur.arret)
        {
            //attendre 3 secondes
            try
            {
                Thread.sleep(3000);
            }
            catch(InterruptedException e) { System.out.print("Erreur\n"); return false; }
            //fermeture de la porte
            this.etat = 'F';
            System.out.println("* PORTE etage "+this.etage+":\t* Fermeture");
            //l'ascenseur est maintenant autorisé à redémarer 
            Ascenceur.PeutDemarrer = true;
            System.out.println("+ Ascensure:\t\t+ fin arret");
            return true;
        }
        return false;
    }

    //méthode permettant d'ouvrir la porte 
    public boolean OuverturePorte()
    {
        //on vérife que l'ascenseur se trouve bien a l'étage de la porte 
        if((!Ascenceur.arret)&&(Ascenceur.etage==this.etage))
        {
            //attendre 3 secondes
            try
            {
                Thread.sleep(3000);
            }
            catch(InterruptedException e) { System.out.print("Erreur\n"); return false; }
            //ouverture de la porte
            this.etat = 'O';
            //on met l'ascenseur a l'arret 
            Ascenceur.PeutDemarrer = false;
            Ascenceur.arret = true;
            System.out.println("* PORTE etage "+this.etage+":\t* Ouverture");
            return true;
        }
        return false;
    }
}
