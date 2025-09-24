// Notizen - Game Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    // Instancia
    ChoiceHandler cHandler = new ChoiceHandler();
    UI ui = new UI();
    VisibilityManager vm = new VisibilityManager(ui);
    Player player = new Player();  // ⭐ Player criado AQUI
    Inventario inventario = new Inventario();  // ⭐ Inventário criado AQUI
    Story story = new Story(this, ui, vm, player, inventario);  // ⭐ Passa como parâmetro

    // Variaveis para os botões de escolha
    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    // Posição atual do jogador no jogo
    public String playerPosition;

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
        // Cada classe cuida da sua parte
        player.setup();
        inventario.setup();

        // Coloca os valores na GUI
        ui.hpLabelNum.setText("" + player.hp);
        ui.playerMoneyLabelNum.setText("" + player.money);
        ui.gameDate.setText("Dia " + player.day);
    }


    // Lida com o input do jogador nos botões-GUI
    public class ChoiceHandler implements ActionListener {

        // Metodo chamado quando um botão é clicado
        public void actionPerformed(ActionEvent event) {

            // Recebe o botão apertado e o armazena na String
            String playerChoice = event.getActionCommand();

            switch (playerChoice) {
                // Botão START
                case "start":
                    vm.showGameScreen();
                    story.gameIntro();
                    break;
                case "c1": story.selectPosition(nextPosition1); break;
                case "c2": story.selectPosition(nextPosition2); break;
                case "c3": story.selectPosition(nextPosition3); break;
                case "c4": story.selectPosition(nextPosition4); break;
                // Tela de informações do jogador
                case "stats": story.showStatusScreen(); break;
            }
        }
    }
}