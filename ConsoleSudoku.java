import java.util.Scanner;

public class ConsoleSudoku {

    public static void main(String[] args) {
        GrilleSudoku grilleSudoku = new GrilleSudoku();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                grilleSudoku.afficherGrille();
                System.out.println("Entrez 'ligne colonne chiffre' pour placer un chiffre ou 'exit' pour quitter:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    System.out.println("Entrée invalide. Réessayez.");
                    continue;
                }

                try {
                    int ligne = Integer.parseInt(parts[0]);
                    int colonne = Integer.parseInt(parts[1]);
                    int chiffre = Integer.parseInt(parts[2]);

                    if (!grilleSudoku.placerChiffre(ligne, colonne, chiffre)) {
                        System.out.println("Impossible de placer le chiffre.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Utilisez des chiffres.");
                }
            }
        }
    }
}

