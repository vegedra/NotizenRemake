// Notizen - Game Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    // Instancia
    ChoiceHandler cHandler = new ChoiceHandler();
    UI ui = new UI();
    VisibilityManager vm = new VisibilityManager(ui);
    Player player = new Player();
    Inventario inventario = new Inventario();
    Story story = new Story(this, ui, vm, player, inventario);

    // Variaveis para os botões de escolha
    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    // Posição atual do jogador no jogo
    public String playerPosition;

    // Variáveis para salvar o estado antes de abrir a tela de menu etc
    public String savedPosition;
    public String savedText;
    public String savedChoice1, savedChoice2, savedChoice3, savedChoice4;
    public String savedNext1, savedNext2, savedNext3, savedNext4;

    public static void main(String[] args) {
        new Game();
    }

    // Inicializa o jogo
    public Game() {

        // Cria as telas
        ui.createUI(cHandler);
        // Inicializa o jogador
        gameSetup();
        // Carrega a tela inicial
        vm.showTitleScreen();
    }

    // Inicializa valores iniciais para o jogo
    public void gameSetup() {
        // Cada classe se inicia com seus valores
        player.setup();
        inventario.setup();

        // Coloca os valores na GUI
        ui.hpLabelNum.setText("" + player.hp);
        ui.playerMoneyLabelNum.setText("" + player.money);
        ui.gameDate.setText("Dia " + player.day);

        // Atualiza o inventário na UI
        inventario.atualizarInventarioUI(ui);
    }

    public void resetGame() {
        System.out.println("Resetando jogo...");

        // Reseta completamente o jogo
        player.setup();
        inventario.setup();
        inventario.atualizarInventarioUI(ui);
        gameSetup();

        // Limpa estados salvos
        savedPosition = null;
        savedText = null;
        savedChoice1 = savedChoice2 = savedChoice3 = savedChoice4 = null;
        savedNext1 = savedNext2 = savedNext3 = savedNext4 = null;

        // Reseta a posição do jogador para um estado limpo
        playerPosition = "titleScreen";

        // Volta para a tela inicial
        vm.showTitleScreen();
    }

    // Verifica se é game over
    public boolean isGameOver() {
        return player.hp <= 0;
    }


    // Lida com o input do jogador nos botões-GUI
    public class ChoiceHandler implements ActionListener {

        // Metodo chamado quando um botão é clicado
        public void actionPerformed(ActionEvent event) {

            // Recebe o botão apertado e o armazena na String
            String playerChoice = event.getActionCommand();

            // Verifica se o jogador está na tela de game over
            if ("gameOverScreen".equals(playerPosition)) {
                // Se estiver na tela de game over, permite apenas o botão c1 funcionar
                if (playerChoice.equals("c1")) {
                    story.selectPosition(nextPosition1);
                }
                return; // Impede outras ações na tela de game over
            }

            // Verifica se o jogador está vivo e permite apertar no botão c1
            if ((isGameOver() || player.suspicion > 10) && playerChoice.equals("c1")) {
                story.showGameOverScreen();
                return;
            }

            switch (playerChoice) {
                // Botão START
                case "start":
                    vm.showGameScreen();
                    story.gameIntro();
                    break;
                // Botões de escolha
                case "c1": story.selectPosition(nextPosition1); break;
                case "c2": story.selectPosition(nextPosition2); break;
                case "c3": story.selectPosition(nextPosition3); break;
                case "c4": story.selectPosition(nextPosition4); break;
                // Tela de informações do jogador
                case "stats": story.showMenuScreen(); break;
            }

            // Verifica se morreu após a ação (e não está já na tela de game over)
            if (isGameOver() && !"gameOverScreen".equals(playerPosition)) {
                story.showGameOverScreen();
            }
        }
    }
}
