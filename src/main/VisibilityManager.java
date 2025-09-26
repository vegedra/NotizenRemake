// Notizen - Visib. Manager Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

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
        ui.backgroundPanel.setVisible(true);
        ui.subtitleLabel.setVisible(true);

        // Esconde
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
        ui.statButtonPanel.setVisible(false);
        ui.picturePanel.setVisible(false);
        ui.inventoryPanel.setVisible(false);
    }

    // Mostrar a tela de jogo
    public void showGameScreen() {

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);
        ui.exitButtonPanel.setVisible(false);
        ui.gameVersionLabel.setVisible(false);
        ui.backgroundPanel.setVisible(false);
        ui.subtitleLabel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(false);
        ui.statButtonPanel.setVisible(true);
        ui.picturePanel.setVisible(true);
        ui.inventoryPanel.setVisible(false);

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
        ui.choice1.setText("Voltar");
        ui.choice2.setVisible(true);
        ui.choice2.setText("Sair");
    }

    // Métodos para a tela de status (simples)
    public void gameOverScreen() {
        // Esconde as coisas
        ui.statButtonPanel.setVisible(false);
        ui.mainTextPanel.setVisible(true);
        ui.playerPanel.setVisible(false);
        ui.picturePanel.setVisible(true);
        ui.inventoryPanel.setVisible(false);

        // Esconde todos os botões de escolha normais
        hideAllChoiceButtons();

        // Mostra apenas os botões relevantes para game over
        ui.choice1.setVisible(true);
        ui.choice1.setText("Voltar ao início");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
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
