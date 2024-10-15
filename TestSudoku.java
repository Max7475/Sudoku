public class TestSudoku {
    public static void main(String[] args) {
        // Créer une instance de GrilleSudoku
        GrilleSudoku sudoku = new GrilleSudoku();

        // Générer une grille partielle avec un niveau de difficulté donné
        String niveau = "facile";  // Modifiable selon les besoins
        System.out.println("Génération d'une grille partielle de niveau " + niveau + "...");
        sudoku.genererGrillePartielle(niveau);

        // Afficher la grille générée
        System.out.println("Grille partielle générée :");
        sudoku.afficherGrille();

        // Tenter de résoudre la grille en utilisant l'algorithme de backtracking
        System.out.println("Résolution de la grille...");
        if (sudoku.resoudreGrille()) {
            System.out.println("Grille résolue avec succès :");
            sudoku.afficherGrille();
        } else {
            System.out.println("Impossible de résoudre la grille.");
        }

        // Optionnel : Vous pouvez sauvegarder et recharger la grille pour tester ces fonctionnalités également.
        String nomFichier = "grille_sudoku_test.txt";
        sudoku.sauvegarderGrille(nomFichier);
        System.out.println("Grille sauvegardée dans " + nomFichier);

        // Charger la grille sauvegardée et la réafficher
        GrilleSudoku grilleChargee = new GrilleSudoku();
        grilleChargee.chargerGrille(nomFichier);
        System.out.println("Grille chargée depuis le fichier :");
        grilleChargee.afficherGrille();
    }
}
