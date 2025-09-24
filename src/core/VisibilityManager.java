// Notizen - Visib. Manager Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package core;

// Lida com a transição de telas
public class VisibilityManager {

    // Para usar a classe UI aqui
    UI ui;

    // Constructor
    public VisibilityManager(UI userInterface) {

        // Para usar a classe UI aqui
        ui = userInterface;
    }

    // Mostrar a tela inicial
    public void showTitleScreen() {

        // Mostra
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);
        ui.exitButtonPanel.setVisible(true);
        ui.gameVersionLabel.setVisible(true);

        // Esconde
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
        ui.statButtonPanel.setVisible(false);
    }

    // Mostrar a tela de jogo
    public void showGameScreen() {

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);
        ui.exitButtonPanel.setVisible(false);
        ui.gameVersionLabel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(false);
        ui.statButtonPanel.setVisible(true);
    }
}
