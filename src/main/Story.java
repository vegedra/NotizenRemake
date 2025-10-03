// Notizen - Story Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

// O jogo em si - textos, falas, etc
public class Story {    // erro: duplicate class 'Story'

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
        switch (nextPosition) {
            // Voltar ao jogo da tela de menu
            case "backToGame":
                backToGame();
                break;
            case "reset":
                // Reseta o jogo
                game.resetGame();
                break;

            // Intro do jogo
            case "continuarIntro1":
                continuarIntro1();
                break;
            case "trainStation":
                trainStation();
                break;
            case "sairNavio":
                sairNavio();
                break;
            case "ficarNavio":
                ficarNavio();
                break;
            case "educado0":
                respostaNavio(true);
                break;
            case "arrogante0":
                respostaNavio(false);
                break;
            case "continuar1":
                inTrain();
                break;
            case "cityDayOne":
                cityDayOne(true);
                break;

            // Chega na cidade
            // Se evento ocorreu, não chama areaRestrita
            case "areaRestrita1":
                if (eventos("carteiraPerdida")) {
                } else {
                    areaRestrita(1);
                }
                break;
            case "cityDayOne1":
                if (eventos("carteiraPerdida")) {
                } else {
                    cityDayOne(false);
                }
                break;
            case "lookAround1":
                lookAround(1);
                break;
            case "nortePraca":
                if (eventos("carteiraPerdida")) {
                } else {
                    nortePraca();
                }
                break;
            case "lookAround2":
                lookAround(2);
                break;
            case "PegouCarteiraPerdida":
                eventos("PegouCarteiraPerdida");
                break;
            case "NãoPegouCarteiraPerdida":
                eventos("NãoPegouCarteiraPerdida");
                break;

            //case "entrevista1": entrevista(1); break;
            //case "desfileMilitar": desfileMilitar(); break;
        }
    }


    // Introdução do jogo
    public void gameIntro() {

        ui.showImage("");

        // Mostra o texto com scroll caso seja muito grande
        printf("Este jogo não possui salvamento de progresso. \n" +
                "\nVocê tem 3 dias para desvendar o mistério... " +
                "Porém, não se sinta desmotivado. " +
                " \nTente quantas vezes forem necessárias.", false);

        // Escolhas
        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "continuarIntro1";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        // Posição atual do jogador
        game.playerPosition = "gameIntro";
    }

    public void continuarIntro1() {

        // Troca a imagem na tela
        ui.showImage(".//res//img_port1.jpg");

        // Mostra o texto com scroll caso seja muito grande
        printf("29 de Agosto, 1939" +
                "\nEu sou Joseph. Sou um jornalista à caminho da Alemanha. " +
                "Procuro pelo meu avô. Ele é alguém muito especial para mim.\n" +
                " \nApós muito tempo sem contato, recebi uma carta estranha dele...\n" +

                "\nNa carta, ele dizia para nos encontrarmos em Berlin. " +
                "Por conta do meu trabalho, aproveitei a oportunidade.\n" +
                "\nTenho até o fim do dia para chegar no hotel. Preciso coletar mais informações " +
                "e... preciso ter cuidado...\n", false);

        // Escolhas
        ui.choice1.setText("Continuar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "trainStation";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        // Posição atual do jogador
        game.playerPosition = "continuarIntro1";
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

        /* player.hp -= 5; // Causa dano
        ui.hpLabelNum.setText("" + player.hp); // Atualiza UI
        */

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
                    "\nDesconhecido: Vi que o senhor parece ser uma pessoa bastante educada!" +
                    "\nDesconhecido: Continue assim! Ser educado com as pessoas tem seus beneficios." +
                    "\nDesconhecido: Aqui, tome isto:\n", true);
            player.money += 2;
            ui.playerMoneyLabelNum.setText("" + player.money); // Atualiza UI
            printf("\nVocê recebeu 2 ℛℳ!\n" +
                    "\nDesconhecido: O suficiente para uma boa xícara de café.. Ha, ha!\n", true);
        } else if (player.rep <= -1) {
            printf("\nUma pessoa senta ao seu lado...\n" +
                    "\nDesconhecido: Hmf...\n" +
                    "\nParece que você ficou com uma má fama...", true);
        }

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

    // ---- Centro da cidade ----
    public void cityDayOne(boolean firstTime) {

        if (firstTime == true) {
            printf("Após algumas horas no trem, você finalmente chega na cidade." +
                    "\nAinda há tempo suficiente para encontrar o hotel e, talvez, conseguir informações " +
                    "sobre seu avô.\n\nPara onde você irá?", false);
        } else {
            printf("Você retorna ao centro.\nPara onde você irá?", false);
        }

        ui.choice1.setText("Praça");
        ui.choice2.setText("Rua sem saída");
        ui.choice3.setText("Lugar enfaixado");
        ui.choice4.setText("Olhar ao seu redor");

        game.nextPosition1 = "nortePraca";
        game.nextPosition2 = "beco";
        game.nextPosition3 = "areaRestrita1";
        game.nextPosition4 = "lookAround1";

        game.playerPosition = "cityDayOne";
    }

    // Beco - nada ocorre no primeiro dia
    public void beco() {

        printf("Você começa a subir uma rua ingreme.\nVocê começa a se sentir cansado.\n" +
                "Não há nada aqui...", false);

        ui.choice1.setText("Voltar");
        ui.choice2.setText("-");
        ui.choice3.setText("-");
        ui.choice4.setText("-");

        game.nextPosition1 = "cityDayOne1";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        game.playerPosition = "beco";
    }

    // Areas restritas
    public void areaRestrita(int saida) {

        switch (saida) {
            case 1:
                printf("Esta área está restrita...", false);

                ui.choice1.setText("Voltar");
                ui.choice2.setText("-");
                ui.choice3.setText("-");
                ui.choice4.setText("-");

                game.nextPosition1 = "cityDayOne1";
                game.nextPosition2 = "";
                game.nextPosition3 = "";
                game.nextPosition4 = "";

                game.playerPosition = "areaRestrita1";
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

    // Praça - npc para interrogar
    public void nortePraca() {

        printf("Você caminha até chegar em uma praça." +
                "\nHá uma bela fonte em seu centro, com pessoas conversando ao seu redor." +
                "\nTudo parece tão calmo...", false);

        ui.choice1.setText("Olhar ao redor");
        ui.choice2.setText("Entrevistar alguém");
        if (player.descobriuDesfile) { ui.choice3.setText("Investigar agitação"); } else { ui.choice3.setText("-"); }
        ui.choice4.setText("Voltar");

        game.nextPosition1 = "lookAround2";
        game.nextPosition2 = "entrevista1";
        if (player.descobriuDesfile) { game.nextPosition3 = "desfileMilitar"; } else { game.nextPosition3 = ""; }
        game.nextPosition4 = "cityDayOne1";

        game.playerPosition = "nortePraca";
    }
}