import java.io.*;

public class GestionnaireFichierSudoku {

    public static void sauvegarderGrille(GrilleSudoku grille, String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            int[][] grilleData = grille.getGrille();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    writer.write(grilleData[i][j] + " ");
                }
                writer.newLine();
            }
            System.out.println("Grille sauvegardée dans " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }

    public static void chargerGrille(GrilleSudoku grille, String nomFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            int[][] grilleData = grille.getGrille();
            for (int i = 0; i < 9; i++) {
                String[] ligne = reader.readLine().split(" ");
                for (int j = 0; j < 9; j++) {
                    grilleData[i][j] = Integer.parseInt(ligne[j]);
                }
            }
            System.out.println("Grille chargée depuis " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur de chargement : " + e.getMessage());
        }
    }
}

