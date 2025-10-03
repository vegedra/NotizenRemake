// Notizen - StoryManager Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

public class StoryManager {
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;
    Inventario inventario;

    // Construtor
    public StoryManager(Game g, UI userInterface, VisibilityManager vManager, Player p, Inventario inv) {
        game = g;
        ui = userInterface;
        vm = vManager;
        player = p;
        inventario = inv;
    }

    // ---- Telas ----
    // Tela de menu
    public void showMenuScreen() {
        // Salva estado atual
        saveGameState();

        // Configura UI para modo status
        vm.showMenuScreen();

        // Mostra conteúdo do status
        showStatusContent();
    }

    // Salva as telas antes de abrir a de menu
    public void saveGameState() {
        game.savedPosition = game.playerPosition;

        game.savedText = ui.mainTextArea.getText();

        game.savedChoice1 = ui.choice1.getText();
        game.savedChoice2 = ui.choice2.getText();
        game.savedChoice3 = ui.choice3.getText();
        game.savedChoice4 = ui.choice4.getText();

        game.savedNext1 = game.nextPosition1;
        game.savedNext2 = game.nextPosition2;
        game.savedNext3 = game.nextPosition3;
        game.savedNext4 = game.nextPosition4;
    }

    // Tela de menu
    private void showStatusContent() {
        // Imprime a tela de menu
        printf("Joseph\n", false);
        printf("Pontos de Vida: " + player.hp + "\n", true);
        printf("Dinheiro: " + player.money + " Reichsmark\n", true);
        printf("Reputação: " +
                (player.rep > 2 ? "boa" : player.rep < 0 ? "ruim" : "normal") + "\n", true);
        printf("Índice de suspeita: " +
                (player.suspicion > 2 ? "alto" : player.rep < 0 ? "baixo" : "normal") + "\n", true);
        printf("Dia " + player.day + "\n", true);

        // Funções dos botões
        game.nextPosition1 = "backToGame";
        game.nextPosition2 = "reset";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        // Atualiza o inventário na UI
        inventario.atualizarInventarioUI(ui);

        game.playerPosition = "statusScreen";
    }

    // Volta para a tela antes de abrir o menu
    public void backToGame() {
        vm.backToGame();
        restoreGameState();

        // Atualiza a UI após voltar
        ui.hpLabelNum.setText("" + player.hp);
        ui.playerMoneyLabelNum.setText("" + player.money);
        inventario.atualizarInventarioUI(ui);
    }

    // Volta à tela anterior à de menu
    private void restoreGameState() {
        if (game.savedPosition != null) {
            ui.mainTextArea.setText(game.savedText);
            ui.choice1.setText(game.savedChoice1);
            ui.choice2.setText(game.savedChoice2);
            ui.choice3.setText(game.savedChoice3);
            ui.choice4.setText(game.savedChoice4);
            game.nextPosition1 = game.savedNext1;
            game.nextPosition2 = game.savedNext2;
            game.nextPosition3 = game.savedNext3;
            game.nextPosition4 = game.savedNext4;
            game.playerPosition = game.savedPosition;
        }
    }

    // Tela de game over
    public void showGameOverScreen() {
        game.playerPosition = "gameOverScreen";

        ui.mainTextArea.setText("Fim de jogo.\nVocê alcançou o final: ");
        printf("\nTente novamente...", true);
        vm.gameOverScreen();

        ui.choice1.setText("Voltar ao início");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "reset";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    // ----- Utils -----
    // Imprime texto e se necessário rola automaticamente o scroll para o começo
    public void printf(String texto, boolean append) {
        if (append) {
            ui.mainTextArea.append(texto);
        } else {
            ui.mainTextArea.setText(texto);
        }
        ui.mainTextArea.setCaretPosition(0);
    }

    // Eventos que podem ocorrer no jogo
    public boolean eventos(String eventID) {
        switch (eventID) {
            // Encontrar carteira perdida
            case "carteiraPerdida":
                // 30% de chance de evento ocorrer
                if (Math.random() < 0.10) {
                    if ((!inventario.haveItem("Carteira perdida")) && !player.ignorouCarteira) {

                        // Salva estado atual
                        saveGameState();

                        printf("Enquanto caminha, você percebe algo no chão.\n" +
                                "Parece ser uma carteira perdida.\nVocê irá pegar?", false);

                        ui.choice1.setText("Sim");
                        ui.choice2.setText("Não");
                        ui.choice3.setText("-");
                        ui.choice4.setText("-");

                        game.nextPosition1 = "PegouCarteiraPerdida";
                        game.nextPosition2 = "NãoPegouCarteiraPerdida";
                        game.nextPosition3 = "";
                        game.nextPosition4 = "";

                        game.playerPosition = "eventos1";
                        return true; // Evento ocorreu
                    }
                }
                return false; // Evento não ocorreu

            case "NãoPegouCarteiraPerdida":
                player.ignorouCarteira = true;

                printf("Você ignora a carteira.", false);

                ui.choice1.setText("Voltar");
                ui.choice2.setText("-");
                ui.choice3.setText("-");
                ui.choice4.setText("-");

                game.nextPosition1 = "backToGame";
                game.nextPosition2 = "";
                game.nextPosition3 = "";
                game.nextPosition4 = "";

                game.playerPosition = "eventos1B";
                return true;

            case "PegouCarteiraPerdida":

                inventario.addItem(new Item("Carteira perdida", "Uma carteira perdida", 0, 0));
                inventario.atualizarInventarioUI(ui);

                printf("Você pega a carteira.\n" +
                        "Ao abrí-la, você vê um documento.\nA carteira parece " +
                        "pertencer à uma mulher de cabelo escuro...", false);

                ui.choice1.setText("Voltar");
                ui.choice2.setText("-");
                ui.choice3.setText("-");
                ui.choice4.setText("-");

                game.nextPosition1 = "backToGame";
                game.nextPosition2 = "";
                game.nextPosition3 = "";
                game.nextPosition4 = "";

                game.playerPosition = "eventos1A";
                return true;

            default:
                return false;
        }
    }

    // Look around
    public void lookAround(int lugar) {

        switch (lugar) {
            case 1:
                player.descobriuDesfile = true;

                printf("Você olha ao seu redor..." +
                        "\nAs pessoas caminham felizes pela rua, porém, outras nem tanto..." +
                        "\nVocê percebe uma agitação próxima à Praça.", false);

                ui.choice1.setText("Voltar");
                ui.choice2.setText("-");
                ui.choice3.setText("-");
                ui.choice4.setText("-");

                game.nextPosition1 = "cityDayOne1";
                game.nextPosition2 = "";
                game.nextPosition3 = "";
                game.nextPosition4 = "";

                game.playerPosition = "lookAround1";
                break;

            case 2:
                player.descobriuDesfile = true;

                printf("Você olha ao seu redor..." +
                        "\nVocê percebe uma agitação próxima à Praça.", false);

                ui.choice1.setText("Voltar");
                ui.choice2.setText("-");
                ui.choice3.setText("-");
                ui.choice4.setText("-");

                game.nextPosition1 = "nortePraca";
                game.nextPosition2 = "";
                game.nextPosition3 = "";
                game.nextPosition4 = "";

                game.playerPosition = "lookAround2";
                break;
        }
    }
}