import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GrilleSudoku {
    private int[][] grille;

    public GrilleSudoku() {
        grille = new int[9][9];
    }

    public int[][] getGrille() {
        return grille;
    }

    public boolean placerChiffre(int ligne, int colonne, int chiffre) {
        if (ValidateurSudoku.peutPlacerChiffre(grille, ligne, colonne, chiffre)) {
            grille[ligne][colonne] = chiffre;
            return true;
        } else {
            System.out.println("Placement du chiffre " + chiffre + " à la position (" + ligne + ", " + colonne + ") non valide.");
            return false;
        }
    }

    public void genererGrillePartielle(String niveau) {
        Random rand = new Random();
        int casesARemplir = 0;

        if (niveau.equals("facile")) {
            casesARemplir = 40; 
        } else if (niveau.equals("moyen")) {
            casesARemplir = 30;
        } else if (niveau.equals("difficile")) {
            casesARemplir = 20; 
        }

        while (casesARemplir > 0) {
            int ligne = rand.nextInt(9);
            int colonne = rand.nextInt(9);
            int chiffre = rand.nextInt(9) + 1;

            if (grille[ligne][colonne] == 0 && ValidateurSudoku.peutPlacerChiffre(grille, ligne, colonne, chiffre)) {
                grille[ligne][colonne] = chiffre;
                casesARemplir--;
            }
        }
    }

    public boolean resoudreGrille() {
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (grille[ligne][colonne] == 0) {
                    for (int chiffre = 1; chiffre <= 9; chiffre++) {
                        if (ValidateurSudoku.peutPlacerChiffre(grille, ligne, colonne, chiffre)) {
                            grille[ligne][colonne] = chiffre;
                            if (resoudreGrille()) {
                                return true;
                            } else {
                                grille[ligne][colonne] = 0; 
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    
    public void afficherGrille() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-----+-----+-----"); 
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| "); 
                }
                System.out.print(grille[i][j] == 0 ? ". " : grille[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    public void sauvegarderGrille(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    writer.write(grille[i][j] + " ");
                }
                writer.newLine();
            }
            System.out.println("Grille sauvegardée dans " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    
    public void chargerGrille(String nomFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            for (int i = 0; i < 9; i++) {
                String[] ligne = reader.readLine().split(" ");
                if (ligne.length != 9) {
                    throw new IOException("La ligne " + (i + 1) + " ne contient pas 9 valeurs.");
                }
                for (int j = 0; j < 9; j++) {
                    grille[i][j] = Integer.parseInt(ligne[j]);
                }
            }
            System.out.println("Grille chargée depuis " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format de nombre dans le fichier : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GrilleSudoku grilleSudoku = new GrilleSudoku();
        grilleSudoku.genererGrillePartielle("moyen");
        System.out.println("Grille après remplissage :");
        grilleSudoku.afficherGrille();
        
        grilleSudoku.sauvegarderGrille("grille.txt");
        
        GrilleSudoku nouvelleGrille = new GrilleSudoku();
        nouvelleGrille.chargerGrille("grille.txt");
        System.out.println("Grille chargée :");
        nouvelleGrille.afficherGrille();
    }
}
