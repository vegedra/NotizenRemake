// Notizen - Visib. Manager Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

// Lida com a transição de telas
public class VisibilityManager {

    // Para usar a classe UI aqui
    UI ui;

    // Para a tela de menu
    private Menu menu;

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

        // Mostra todos os botões de escolha
        showAllChoiceButtons();
    }

    // Métodos para a tela de status (simples)
    public void showMenuScreen() {
        // Esconde o botão de menu (stats) para não poder abrir outro menu sobreposto
        ui.statButtonPanel.setVisible(false);

        // Esconde todos os botões de escolha normais
        hideAllChoiceButtons();

        // Mostra apenas o botão 1 (Voltar)
        ui.choice1.setVisible(true);
        ui.choice1.setText("Voltar ao Jogo");
    }

    // Volta para o jogo - o Story vai restaurar o conteúdo anterior
    public void backToGame() {
        // Mostra o botão de menu novamente
        ui.statButtonPanel.setVisible(true);

        // Mostra todos os botões de escolha
        showAllChoiceButtons();
    }

    // Métodos auxiliares para controlar visibilidade dos botões
    private void showAllChoiceButtons() {
        ui.choice1.setVisible(true);
        ui.choice2.setVisible(true);
        ui.choice3.setVisible(true);
        ui.choice4.setVisible(true);
    }

    private void hideAllChoiceButtons() {
        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }
}
