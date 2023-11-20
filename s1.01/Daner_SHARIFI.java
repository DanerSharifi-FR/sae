import java.util.Scanner;

public class Sae101_4A4 {
    public static void main(String[] args) {
        int[] values1 = {9, 0, 44, 5, -5, 9, 120, 3, -1, 9, 0, 88, 9};
        System.out.println("int[] compétiteurs = {9, 0, 44, 5, -5, 9, 120, 3, -1, 9, 0, 88, 9}");
        System.out.println("testCas1() :\n");
        affichageTousConcurrentsDansOrdreBrassards(values1);
        podium(values1);
        System.out.println("-------------------------\n");

        System.out.println("int[] compétiteurs = {9, 0, 44, 5, 120, 3, 0, 88}");
        int[] values2 = {9, 0, 44, 5, 120, 3, 0, 88};
        System.out.println("testCas2() :\n");
        affichageTousConcurrentsDansOrdreBrassards(values2);
        podium(values2);
        System.out.println("-------------------------\n");

        System.out.println("int[] compétiteurs = {9, 0, 3, 5, 120, 3, 0, 88}");
        int[] values3 = {9, 0, 3, 5, 120, 3, 0, 88};
        System.out.println("testCas3() :\n");
        affichageTousConcurrentsDansOrdreBrassards(values3);
        podium(values3);
        System.out.println("-------------------------\n");

        System.out.println("int[] compétiteurs = {9, 0, 75, 5, 120, 3, 0, 88, 5}");
        int[] values4 = {9, 0, 75, 5, 120, 3, 0, 88, 5};
        System.out.println("testCas4() :\n");
        affichageTousConcurrentsDansOrdreBrassards(values4);
        podium(values4);
        System.out.println("-------------------------\n");

        System.out.println("int[] compétiteurs = {9, 0, 3, 5, 120, 3, 0, 88, 3, 928, 8, 3, 11, 0, 3}");
        int[] values5 = {9, 0, 3, 5, 120, 3, 0, 88, 3, 928, 8, 3, 11, 0, 3};
        System.out.println("testCas5() :\n");
        affichageTousConcurrentsDansOrdreBrassards(values5);
        podium(values5);
        System.out.println("-------------------------\n");

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
