import java.util.Scanner;

/**
 * This was a group project, therefore you'll 2 of my classmates names also as developers for certain parts
 * My parts :
    - affichageTousConcurrentsDansOrdreBrassards
    - podium
    - affichageTroisPlaces
    - trouverCles
    - trouverValeurs
    - redimensionnerTableau
 */
public class Sae101_4A4 {
    /**
     * -->Développé par Romain Da Chao<--
     * <p>
     * Méthode principale du programme permettant de gérer la competition
     * en saisissant le nombre de compétiteurs, la longueur de la piste, le nombre d'obstacles (avec un maximum en cohérence avec la piste)
     * et le nombre total de barres.
     *
     * @param args Les paramètres de la fonction main (qui sont inutilisés dans ce cas)
     */
    public static void main(String[] args) {

        System.out.println("Saisissez le nombre de competiteurs :");
        int nbCompetiteurs = saisieIntC(1, 50); //Saisie des compétiteurs en appelant la fonction de saisie contrôlée saisieIntC

        int[] temps = new int[nbCompetiteurs]; //Création de la liste pour insérer les temps réalisés par les joueurs
        SaisieJoueurs(nbCompetiteurs, temps); //Attribution des numéros de joueurs dans la liste joueurs avec l'appel de la fonction SaisieJoueurs

        System.out.println("Saisissez la longueur de la piste (en mètres) :"); //Saisie de la longueur de la piste
        int lgPiste = saisieIntC(400, 800); //Saisie de la longueur de la piste en appelant la fonction de saisie contrôlée saisieIntC

        System.out.println("Saisissez le nombre d'obstacle");
        int nbObstacles = saisieIntC(0, 25); //Saisie du nombre d'obstacles de la piste en appelant la fonction de saisie contrôlée saisieIntC

        System.out.println("Saisissez le nombre total de barres");
        int nbTotalesBarres = saisieIntC(nbObstacles * 2, nbObstacles * 4); //Saisie du total des barres en veillant sur la cohérence avec le nombre d'obstacles avec saisieIntC

        System.out.println("Le nombre de competiteurs est de " + nbCompetiteurs + " personnes.\n" +
                "La longueur de la piste est de " + lgPiste + " mètres.\n" +
                "Le nombre d'obstacle est de " + nbObstacles + " obstacles.\n" +
                "Le nombre totale de barres est de " + nbTotalesBarres + " barres. " +
                "Soit en moyenne " + (int) nbTotalesBarres / nbObstacles + " barres par obstacles."); //Récapitulatif des données attribuées

        //resultats(nbCompetiteurs, nbTotalesBarres, nbObstacles, lgPiste, temps); //Appel de la fonction resultats pour retourner la liste de numéro de joueurs et leur temps attribué
        int[] competiteursTableau = resultats(nbCompetiteurs, nbTotalesBarres, nbObstacles, lgPiste, temps);
        affichageTousConcurrentsDansOrdreBrassards(competiteursTableau);
        podium(competiteursTableau);
        System.out.println("------------------------------");
        System.out.println("Place à la manche 2 :");
        int[] competiteursTableau2 = manche2(nbCompetiteurs, nbTotalesBarres, nbObstacles, lgPiste, temps);
        affichageTousConcurrentsDansOrdreBrassards(competiteursTableau2);
        podium(competiteursTableau2);
    }

    /**
     * Demande à l'utilisateur de saisir un entier, jusqu'à ce que
     * l'entier saisi soit entre les deux bornes en paramètres.
     *
     * @param pfBorneInf IN : borne inférieure
     * @param pfBorneSup IN : borne supérieure
     * @return un entier entre pfBorneInf et pfBorneSup, compris
     */
    public static int saisieIntC(int pfBorneInf, int pfBorneSup) {
        int valeur;
        Scanner clavier = new Scanner(System.in);
        System.out.println("(Avec une valeur comprise entre " + pfBorneInf + " et " + pfBorneSup + ") :");
        valeur = clavier.nextInt();
        while (valeur < pfBorneInf || valeur > pfBorneSup) {
            System.out.println("Attention ! Donnez une valeur comprise entre " + pfBorneInf + " et " + pfBorneSup + " !");
            valeur = clavier.nextInt();
        }
        return valeur;
    }

    /**
     * -->Développé par Romain Da Chao<--
     *
     * Saisie des numéro de brassard des joueurs et stockage dans le tableau.
     *
     * @param pfNbCompetiteurs IN : Le nombre de compétiteurs.
     * @param pfTabJoueurs     IN : Le tableau de joueurs.
     */
    public static void SaisieJoueurs(int pfNbCompetiteurs, int pfTabJoueurs[]) {
        for (int i = 0; i < pfNbCompetiteurs; i++) { //boucle for permettant d'entrer les numéros de joueurs dans pfTabJoueurs (pour mieux                                                         se repérer dans le code)
            pfTabJoueurs[i] = i + 1; //Attribution des numéros pour chaques occurences.
        }
    }

    /**
     * @author Marwane Ibrahim
     *
     * Permet de saisir le nombre de barres tombées, le nombre de refus, s'il y a eu une chute, son temps en millisecondes et
     * de dire si le candidat de numéro pfBrassard est qualifié ou non.
     *
     * @param pfBrassard        IN : numéro de brassard du compétiteur
     * @param pfNbTotalesBarres IN : nombre totales de barres
     * @param pfNbObstacles     IN : nombre d'obstacles
     * @param pfLgPiste         IN : longueur de la piste en mètre
     * @return : le temps compensé du compétiteur s'il est qualifié 0 sinon
     */
    public static int manche(int pfBrassard, int pfNbTotalesBarres, int pfNbObstacles, int pfLgPiste) {
        // Saisies
        // Saisie contrôlée du nombre de barres tombées
        Scanner clavier = new Scanner(System.in);
        System.out.println("Saisissez le nombre de barres tombées par le compétiteur numéro " + pfBrassard + ": ");
        int nbBarresT = saisieIntC(0, pfNbTotalesBarres);
        while (nbBarresT > pfNbTotalesBarres) {
            System.out.println("Erreur ! Donnez une valeur comprise entre " + pfNbTotalesBarres / 2 + " et " + pfNbTotalesBarres + " ?");
            pfNbTotalesBarres = clavier.nextInt();
        }
        // Saisie contrôlée du nombre de refus
        System.out.println("Saisissez le nombre de refus effectués par le cheval du compétiteur numéro " + pfBrassard + ": ");
        int nbRefus = saisieIntC(0, pfNbObstacles);

        // Saisie de la chute ou non
        System.out.println("Saisissez si le compétiteur numéro " + pfBrassard + " a fait une chute: ");
        System.out.println("Saisissez en minuscule oui si il a fait une chute et non sinon : ");
        String chuteS = clavier.nextLine();

        boolean chute = false;
        // Saisie du temps effectué par le compétiteur
        System.out.println("Saisissez le temps en millisecondes : ");
        int temps = saisieIntC(0, 360000);
        // Contrôle de si le compétiteur a chuté ou non
        if (chuteS.equals("oui")) {
            chute = true;
        }

        //Traitement
        // Contrôle des conditions d'éliminations
        int tempsCompense;
        if (
                (nbRefus >= 3)
                        ||
                        (pfLgPiste <= 600 && temps >= 120000)
                        ||
                        (pfLgPiste >= 600 && temps >= 180000)
                        ||
                        (chute == true)

        ) {
            tempsCompense = 0;
        } else {
            // Calcul du temps compensé
            tempsCompense = 8000 * nbBarresT;
            tempsCompense += temps;
        }
        // Return de la valeur finale
        return tempsCompense;
    }

    /**
     * -->Développé par Romain Da Chao<--
     * <p>
     * Saisie du nombre de competiteurs,du numéro de brassard, du nombre de barres, du nombre d'obstacles et longueur de la piste.
     *
     * @param pfNbObstacles     IN : nombre d'obstacles
     * @param pfLgPiste         IN : longueur de la piste en mètre
     * @param pfNbCompetiteurs  IN : Le nombre de compétiteurs
     * @param pfNbTotalesBarres IN : Le nombre total de barres
     * @param pfTabTemps        IN : Le tableau du temps des joueurs
     * @return La liste du temps attribué à chaque joueur
     */
    public static int[] resultats(int pfNbCompetiteurs, int pfNbTotalesBarres, int pfNbObstacles, int pfLgPiste, int[] pfTabTemps) {

        for (int i = 0; i < pfNbCompetiteurs; i++) { //boucle for pour insérer les joueurs et leurs temps dans resultats
            pfTabTemps[i] = manche(i + 1, pfNbTotalesBarres, pfNbObstacles, pfLgPiste); //Attribution du temps du joueur au lieu du numéro
        }
        return pfTabTemps;
    }

    /**
     * @author Romain Da Chao et Marwane Ibrahim
     * 
     * @param pfNbCompetiteurs IN : Le nombre de compétiteurs
     * @param pfNbTotalesBarres IN : Le nombre total de barres
     * @param pfNbObstacles IN : nombre d'obstacles
     * @param pfLgPiste IN : longueur de la piste en mètre
     * @param pfTabTemps IN : Le tableau du temps des joueurs
     * 
     * @return la liste de la moyenne des temps de la première et la deuxième manche
     */
    public static int[] manche2(int pfNbCompetiteurs, int pfNbTotalesBarres, int pfNbObstacles, int pfLgPiste, int[] pfTabTemps) {
        // Remplissage du tableau tabTemps2[] par les résultats de la première manche
        int tabTemps2[] = new int[pfNbCompetiteurs];
        for (int i = 0; i < pfNbCompetiteurs; i++) {
            tabTemps2[i] = pfTabTemps[i];
        }
        // Vérification du temps des compétiteurs, si la valeur vaut 0 il n'est pas qualifié et on ne s'occupe plus de la valeure, si il est qualifié on calcule la moyenne du temps de la première et de la deuxième manche.
        for (int i = 0; i < tabTemps2.length; i++) {
            if (tabTemps2[i] != 0) {
                tabTemps2[i] += manche(i + 1, pfNbTotalesBarres, pfNbObstacles, pfLgPiste);
                tabTemps2[i] = tabTemps2[i]/2;
            }
        }
        return tabTemps2;
    }

    /**
     * @author Daner SHARIFI
     *
     * Afficher les résultats (temps compensés) de la course dans l’ordre des brassards.
     *
     * @param competiteurs IN : Un tableau d'entiers représentant les temps des compétiteurs.
     */
    public static void affichageTousConcurrentsDansOrdreBrassards(int[] competiteurs) {
        System.out.println("Résultats (temps compensés) de la course dans l’ordre des brassards :");
        for (int i = 0; i < competiteurs.length; i++) {
            String print = "- Compétiteur N°" + (i + 1) + " | Temps : " + competiteurs[i];
            if (competiteurs[i] == 0) {
                print += " (éliminé)";
            }
            System.out.println(print);
        }
        System.out.println("-------------------------");
    }

    /**
     * @author Daner SHARIFI
     *
     * Cette fonction affiche un podium virtuel pour les compétiteurs en fonction de leurs temps.
     *
     * @param competiteurs IN : Un tableau d'entiers représentant les temps des compétiteurs.
     */
    public static void podium(int[] competiteurs) {
        // Initialiser un tableau pour stocker les trois plus petits nombres trouvés dans le tableau d'entrée.
        int[] troisPlusPetitsTemps = {360000, 360000, 360000};

        // Initialiser un tableau pour stocker les indices correspondants des trois plus petits temps.
        int[] indices = {-1, -1, -1};

        // Itérer à travers le tableau d'entrée pour trouver les trois plus petits temps et leurs indices.
        for (int i = 0; i < competiteurs.length; i++) {
            // On stocke le nombre actuel à la position i dans le tableau "competiteurs".
            int nombre = competiteurs[i];
            // On initialise une variable "k" à zéro pour suivre le nombre de plus petits temps déjà trouvés.
            int k = 0;
            // On initialise une variable booléenne "termine" à "false" pour indiquer si le nombre actuel a été placé parmi les trois plus petits temps.
            boolean termine = false;

            // Trouver la position appropriée pour le nombre actuel dans le tableau des trois plus petits temps.
            while (k < 3 && !termine) {
                // Vérifie si le nombre actuel n'est pas égal à zéro et s'il est plus petit que le k-ième plus petit temps trouvé jusqu'à présent.
                if (nombre != 0 && (nombre < troisPlusPetitsTemps[k] || troisPlusPetitsTemps[k] < 360000)) {
                    // Décaler les valeurs existantes pour faire de la place pour le nouveau plus petit temps.
                    for (int j = 2; j > k; j--) {
                        troisPlusPetitsTemps[j] = troisPlusPetitsTemps[j - 1];
                        indices[j] = indices[j - 1];
                    }

                    // Mettre à jour les trois plus petits temps et leurs indices correspondants.
                    troisPlusPetitsTemps[k] = nombre;
                    indices[k] = i;
                    termine = true;
                }
                k++;
            }
        }

        // Cas 1 : 3 places différentes pour 3 temps différents avec la 3ème place ex aequo
        int[] exTroisiemeKeys = trouverCles(competiteurs, troisPlusPetitsTemps[2]);
        boolean troisTempsDifferents = troisPlusPetitsTemps[0] != troisPlusPetitsTemps[1] && troisPlusPetitsTemps[1] != troisPlusPetitsTemps[2];
        if (troisTempsDifferents && exTroisiemeKeys.length > 1) {
            int[] exTroisiemeValues = trouverValeurs(competiteurs, troisPlusPetitsTemps[2]);
            // Afficher les trois plus petits temps et leurs indices.
            affichageTroisPlaces(troisPlusPetitsTemps, indices);
            for (int ex = 1; ex < exTroisiemeKeys.length; ex++) {
                System.out.println("    - Compétiteur N°" + (exTroisiemeKeys[ex] + 1) + " | Temps : " + exTroisiemeValues[ex] + " ms");
            }
        }

        // Cas 2 : 3 places différentes pour 3 temps différents
        else if (troisTempsDifferents) {
            // Afficher les trois plus petits temps et leurs indices.
            affichageTroisPlaces(troisPlusPetitsTemps, indices);
        }

        // Cas 3 : 2 compétiteurs en 1ᵉʳᵉ place et 1 compétiteur en 2ᵉ
        else if (troisPlusPetitsTemps[0] == troisPlusPetitsTemps[1] && troisPlusPetitsTemps[1] != troisPlusPetitsTemps[2]) {
            // Afficher les trois plus petits temps et leurs indices.
            System.out.println("Place N°1 :");
            for (int k = 0; k < 2; k++) {
                int tempNumber = troisPlusPetitsTemps[k];
                int tempIndex = indices[k];
                if (tempNumber < 360000) {
                    System.out.println("    - Compétiteur N°" + (tempIndex + 1) + " | Temps : " + tempNumber + " ms");
                }
            }
            if (troisPlusPetitsTemps[2] < 360000) {
                System.out.println("Place N°2 :");
                System.out.println("    - Compétiteur N°" + (indices[2] + 1) + " | Temps : " + troisPlusPetitsTemps[2] + " ms");
            }
        }

        // Cas 4 : 1 compétiteur en 1ᵉʳᵉ place et 2 compétiteurs en 2ᵉ
        else if (troisPlusPetitsTemps[0] != troisPlusPetitsTemps[1] && troisPlusPetitsTemps[0] < 360000) {
            // Afficher les trois plus petits temps et leurs indices.
            System.out.println("Place N°1 :");
            System.out.println("    - Compétiteur N°" + (indices[0] + 1) + " | Temps : " + troisPlusPetitsTemps[0] + " ms");
            for (int k = 1; k < 3; k++) {
                int tempNumber = troisPlusPetitsTemps[k];
                int tempIndex = indices[k];
                if (tempNumber < 360000) {
                    System.out.println("Place N°2 :");
                    System.out.println("    - Compétiteur N°" + (tempIndex + 1) + " | Temps : " + tempNumber + " ms");
                }
            }
        }

        // Cas 5 : 3 >= compétiteurs en 1ᵉʳᵉ place
        else {
            int[] exPremieresKeys = trouverCles(competiteurs, troisPlusPetitsTemps[0]);
            // Afficher les trois plus petits temps et leurs indices.
            if (exPremieresKeys.length >= 3 && troisPlusPetitsTemps[0] < 360000) {
                int[] exPremieresValues = trouverValeurs(competiteurs, troisPlusPetitsTemps[0]);
                System.out.println("Place N°1 :");
                for (int k = 0; k < exPremieresKeys.length; k++) {
                    int tempNumber = exPremieresValues[k];
                    int tempIndex = exPremieresKeys[k];
                    System.out.println("    - Compétiteur N°" + (tempIndex + 1) + " | Temps : " + tempNumber + " ms");
                }
            }
        }
    }

    /**
     * @author Daner SHARIFI
     *
     * Cette fonction affiche les trois premières places du podium virtuel, y compris les temps et les indices.
     *
     * @param troisPlusPetitsTemps IN : Un tableau d'entiers contenant les trois plus petits temps.
     * @param indices              IN : Un tableau d'entiers contenant les indices correspondants des trois plus petits temps.
     */
    public static void affichageTroisPlaces(int[] troisPlusPetitsTemps, int[] indices) {
        for (int k = 0; k < 3; k++) {
            int tempNumber = troisPlusPetitsTemps[k];
            int tempIndex = indices[k];
            if (indices[k] >= 0) {
                System.out.println("Place N°" + (k + 1) + " :");
                System.out.println("    - Compétiteur N°" + (tempIndex + 1) + " | Temps : " + tempNumber + " ms");
            }
        }
    }

    /**
     * @author Daner SHARIFI
     *
     * Cette fonction recherche les indices des éléments dans le tableau qui correspondent à une valeur donnée.
     *
     * @param tableau          IN : Le tableau d'entiers dans lequel effectuer la recherche.
     * @param valeurRecherchee IN : La valeur à rechercher dans le tableau.
     * @return Un tableau d'entiers contenant les indices des éléments correspondants.
     */
    public static int[] trouverCles(int[] tableau, int valeurRecherchee) {
        int[] cles = new int[0];
        int count = 0;

        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] == valeurRecherchee) {
                if (count == cles.length) {
                    cles = redimensionnerTableau(cles, count + 1);
                }
                cles[count] = i;
                count++;
            }
        }

        return cles;
    }

    /**
     * @author Daner SHARIFI
     *
     * Cette fonction recherche les valeurs correspondant à une valeur donnée dans le tableau.
     *
     * @param tableau          IN : Le tableau d'entiers dans lequel effectuer la recherche.
     * @param valeurRecherchee IN : La valeur à rechercher dans le tableau.
     * @return Un tableau d'entiers contenant les valeurs correspondantes.
     */
    public static int[] trouverValeurs(int[] tableau, int valeurRecherchee) {
        int[] valeurs = new int[0];
        int count = 0;

        for (int i : tableau) {
            if (i == valeurRecherchee) {
                if (count == valeurs.length) {
                    valeurs = redimensionnerTableau(valeurs, count + 1);
                }
                valeurs[count] = i;
                count++;
            }
        }

        return valeurs;
    }

    /**
     * @author Daner SHARIFI
     *
     * Cette fonction redimensionne un tableau d'entiers à une nouvelle taille spécifiée.
     *
     * @param original       IN : Le tableau d'entiers original à redimensionner.
     * @param nouvelleTaille IN : La nouvelle taille du tableau redimensionné.
     * @return Un nouveau tableau d'entiers redimensionné.
     */
    public static int[] redimensionnerTableau(int[] original, int nouvelleTaille) {
        // Initialisation de notre nouveau Tableau
        int[] redimensionne = new int[nouvelleTaille];
        for (int i = 0; i < original.length; i++) {
            redimensionne[i] = original[i];
        }
        return redimensionne;
    }

}
