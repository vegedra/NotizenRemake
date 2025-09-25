// Notizen - Story Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

// O jogo em si - textos, falas, etc
public class Story {

    // Importa as classes
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;
    Inventario inventario;

    // Constructor
    public Story(Game g, UI userInterface, VisibilityManager vManager, Player p, Inventario inv) {
        game = g;
        ui = userInterface;
        vm = vManager;
        player = p;
        inventario = inv;
    }

    // Imprime texto e se necessário rola automaticamente o scroll para o começo
    public void printf(String texto, boolean append) {
        if (append) {
            ui.mainTextArea.append(texto);
        } else {
            ui.mainTextArea.setText(texto);
        }
        ui.mainTextArea.setCaretPosition(0);
    }

    // ---- Tela de Menu ----
    public void showMenuScreen() {
        // Salva estado atual
        saveGameState();

        // Configura UI para modo status
        vm.showMenuScreen();

        // Mostra conteúdo do status
        showStatusContent();
    }

    // Salva as telas antes de abrir a de menu
    private void saveGameState() {
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
        game.nextPosition2 = "sairJogo";
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
    }

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

    // Lida com as escolhas do jogador
    public void selectPosition(String nextPosition) {
        while (player.hp > 0) {
            switch (nextPosition) {
                // Voltar ao jogo da tela de menu
                case "backToGame": backToGame(); break;
                case "sairJogo": vm.showTitleScreen(); break;
    
                // Intro do jogo
                case "continuarIntro": continuarIntro(); break;
                case "continuar0": trainStation(); break;
                case "sairNavio": sairNavio(); break;
                case "ficarNavio": ficarNavio(); break;
                case "educado0": respostaNavio(true); break;
                case "arrogante0": respostaNavio(false); break;
                case "continuar1": inTrain(); break;
    
                // Chega na cidade
                //...
            }
        } 
        printf("Fim de jogo", true);
        vm.gameOverScreen();
    }

    // Introdução do jogo
    public void gameIntro() {

        // Troca a imagem na tela
        ui.showImage(".//res//img_port1.jpg");

        // Mostra o texto com scroll caso seja muito grande
        printf("29 de Agosto, 1939" +
                "\nEu sou Joseph. Sou um diplomata à caminho da Alemanha. " +
                "Estou procurando meu avô. Ele é uma pessoa muito especial para mim.\n" +
                " \nApós muito tempo sem contato, recebi uma carta dele...\n", false);

        // Escolhas
        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "continuarIntro";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        // Posição atual do jogador
        game.playerPosition = "gameIntro";
    }

    // Introdução do jogo
    public void continuarIntro() {

        printf("Na carta, ele dizia para nos encontrarmos em Berlin. " +
                "Por conta do meu trabalho, aproveitei a oportunidade.\n" +
                "\nTenho até o fim do dia para chegar no hotel.\n", false);

        // Escolhas
        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "continuar0";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        // Posição atual do jogador
        game.playerPosition = "continuarIntro";
    }

    // Começo do jogo
    public void trainStation() {

        printf("Após uma longa viagem de navio, você finalmente chega ao porto.\n" +
                "\nO capitão anuncia a chegada e pede para os passageiros desembarcarem.", false);

        ui.choice1.setText("Sair do navio");
        ui.choice2.setText("Ficar");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "sairNavio";
        game.nextPosition2 = "ficarNavio";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        game.playerPosition = "trainStation";
    }

    // Se sai de uma vez do navio
    public void sairNavio() {

        // Mostra os paíneis agora
        ui.playerPanel.setVisible(true);
        ui.inventoryPanel.setVisible(true);

        printf("Você decide sair do navio." +
                "\nAntes de sair, você decide verificar sua maleta:", false);

        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "continuar1";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        game.playerPosition = "sairNavio";
    }

    // Se o jogador escolhe permanecer no navio
    public void ficarNavio() {

        printf("Capitão: Herr Joseph, já chegamos. Por favor, desembarque.\n" +
                "\nComo você irá responder?", false);

        ui.choice1.setText("De forma educada");
        ui.choice2.setText("De forma arrogante");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "educado0";
        game.nextPosition2 = "arrogante0";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        game.playerPosition = "ficarNavio";
    }

    // Tela de continuar
    public void respostaNavio(boolean educado) {

        // Se foi arrogante
        if (!educado) {
            player.rep -= 1;
            player.karma -= 10;

            printf("Você olha com um olhar zangado para o capitão e estala a língua." +
                    "\nCapitão: ...", false);
        }
        // Se foi educado
        else {
            player.rep += 1;
            player.karma += 10;

            printf("Você acena com a cabeça e se prepara para sair.", false);
        }
        // Debug
        System.out.println("playerRep: " + player.rep +
                "\nplayerKarma: " + player.karma);

        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "sairNavio";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        game.playerPosition = "respostaNavio";
    }

    // Trem para a cidade
    public void inTrain() {

        printf("Você está dentro do trem." +
                "\nA vista lá fora é bela, porém..." +
                "\nAlgo parece errado...\n", false);

        if (player.rep >= 1) {
            printf("\nUma pessoa senta ao seu lado...\n" +
                    "\nDesconhecido: Guten morgen!" +
                    "\nDesconhecido: Vi que o senhor parece ser um senhor bastante educado!" +
                    "\nDesconhecido: Continue assim! Ser educado com as pessoas tem seus beneficios." +
                    "\nDesconhecido: Aqui, tome isto:\n", true);
            player.money += 2;
            printf("\nVocê recebeu 2 ℛℳ!\n" +
                    "\nDesconhecido: O suficiente para uma boa xícara de café.. Ha, ha!\n", true);
        }
        else if (player.rep <= -1) {
            printf("\nUma pessoa senta ao seu lado...\n" +
                "\nDesconhecido: Hmf...\n" +
                "\nParece que você ficou com uma má fama...", true);
        }

        // Mostrar atualização
        ui.playerMoneyLabelNum.setText("" + player.money);

        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "cityDayOne";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        game.playerPosition = "inTrain";
    }
}
