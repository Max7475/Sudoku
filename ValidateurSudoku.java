public class ValidateurSudoku {

    // Méthode pour vérifier si un chiffre peut être placé dans la grille
    public static boolean peutPlacerChiffre(int[][] grille, int ligne, int colonne, int chiffre) {
        // Vérifier les bornes de la ligne et de la colonne
        if (ligne < 0 || ligne >= 9 || colonne < 0 || colonne >= 9) {
            return false; // Hors limites
        }

        // Vérifier si le chiffre est valide (entre 1 et 9)
        if (chiffre < 1 || chiffre > 9) {
            return false; // Chiffre invalide
        }

        // Vérifier si la case est déjà remplie
        if (grille[ligne][colonne] != 0) {
            return false; // Case non vide
        }

        // Vérifier la ligne pour s'assurer que le chiffre n'y est pas déjà
        for (int i = 0; i < 9; i++) {
            if (grille[ligne][i] == chiffre) {
                return false; // Chiffre déjà présent dans la ligne
            }
        }

        // Vérifier la colonne pour s'assurer que le chiffre n'y est pas déjà
        for (int i = 0; i < 9; i++) {
            if (grille[i][colonne] == chiffre) {
                return false; // Chiffre déjà présent dans la colonne
            }
        }

        // Vérifier la sous-grille 3x3 pour s'assurer que le chiffre n'y est pas déjà
        int debutLigne = (ligne / 3) * 3;
        int debutColonne = (colonne / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[debutLigne + i][debutColonne + j] == chiffre) {
                    return false; // Chiffre déjà présent dans le sous-bloc 3x3
                }
            }
        }

        // Si toutes les vérifications passent, le chiffre peut être placé
        return true;
    }
}
