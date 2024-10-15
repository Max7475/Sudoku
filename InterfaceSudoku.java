import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceSudoku extends JFrame {
    private JTextField[][] champsGrille;
    private GrilleSudoku grille;

    public InterfaceSudoku() {
        grille = new GrilleSudoku();
        initialiserInterface();
    }

    private void initialiserInterface() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Créer le panel de la grille
        JPanel panelGrille = new JPanel(new GridLayout(9, 9));
        champsGrille = new JTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                champsGrille[i][j] = new JTextField();
                champsGrille[i][j].setHorizontalAlignment(JTextField.CENTER);
                champsGrille[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                panelGrille.add(champsGrille[i][j]);
            }
        }

        JButton boutonResoudre = new JButton("Resoudre");
        boutonResoudre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resoudreSudoku();
            }
        });

        JButton boutonRemplir = new JButton("Remplir la grille");
        boutonRemplir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remplirGrille();
            }
        });

        add(panelGrille, BorderLayout.CENTER);
        JPanel panelBoutons = new JPanel();
        panelBoutons.add(boutonRemplir);
        panelBoutons.add(boutonResoudre);
        add(panelBoutons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resoudreSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String texte = champsGrille[i][j].getText();
                if (!texte.isEmpty()) {
                    try {
                        int chiffre = Integer.parseInt(texte);
                        grille.getGrille()[i][j] = chiffre;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Entree invalide a la position (" + (i + 1) + "," + (j + 1) + ").", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    grille.getGrille()[i][j] = 0;
                }
            }
        }

    
        if (grille.resoudreGrille()) {
            afficherGrille();
            JOptionPane.showMessageDialog(this, "Grille resolue avec succes !");
        } else {
            JOptionPane.showMessageDialog(this, "Impossible de resoudre la grille.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void afficherGrille() {
        int[][] grilleData = grille.getGrille();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grilleData[i][j] != 0) {
                    champsGrille[i][j].setText(String.valueOf(grilleData[i][j]));
                } else {
                    champsGrille[i][j].setText("");
                }
            }
        }
    }

    private void remplirGrille() {
        grille.genererGrillePartielle("moyen");
        afficherGrille();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceSudoku());
    }
}
