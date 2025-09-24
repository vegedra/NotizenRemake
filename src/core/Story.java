// Notizen - Story Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package core;

// O jogo em si - textos, falas, etc
public class Story {

    // Importa as classes
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;  // ⭐ Recebe do Game
    Inventario inventario;  // ⭐ Recebe do Game

    // Constructor
    public Story(Game g, UI userInterface, VisibilityManager vManager, Player p, Inventario inv) {

        game = g;
        ui = userInterface;
        vm = vManager;
        player = p;  // ⭐ Usa a mesma instância do Game
        inventario = inv;  // ⭐ Usa a mesma instância do Game
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

    // Função para mostrar a tela de stats/inventário
    public void showStatusScreen() {

        // Mostra as informações do jogador, substituindo o texto em tela
        // Usar append ou setText?
        ui.mainTextArea.append("\n\n=== STATUS ===\n" +
                "\nJoseph\n" +
                "Pontos de Vida: " + player.hp + "\n" +
                "Dinheiro: " + player.money + " ℛℳ\n" +
                "Karma: " + player.karma + "\n" +
                "Reputação: " + player.rep + "\n" +
                "Nível de suspeito: " + player.suspicion + "\n" +
                "Dia " + player.day + "\n" +
                "\n=== INVENTÁRIO ===");
        inventario.mostrarInventario(ui);
        ui.mainTextArea.setCaretPosition(0);
    }

    // Lida com as escolhas do jogador
    public void selectPosition(String nextPosition) {
        switch (nextPosition) {

            // Intro do jogo
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

    // Introdução do jogo
    public void gameIntro() {

        // Mostra o texto com scroll caso seja muito grande
        printf("29 de Agosto, 1939" +
                "\nEu sou Joseph. Sou um diplomata à caminho da Alemanha." +
                "\nMeus motivos oficiais são para negociação e observação, entretanto, " +
                "o que realmente busco é informação a respeito do meu avô," +
                " aquele que me criou e que, agora, se encontra desaparecido após enviar uma carta para mim...\n" +
                "\nMe hospedei no mesmo hotel em que ele estava. Preciso chegar lá até o fim do dia.", false);

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
        game.playerPosition = "gameIntro";

        System.out.println(game.playerPosition);
    }

    // Começo do jogo
    public void trainStation() {

        //player.position = "trainStation";
        printf("Após uma longa viagem de navio, você finalmente chega ao porto." +
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

        ui.playerPanel.setVisible(true);

        printf("Você decide sair do navio." +
                "\nAntes de sair, você decide verificar sua maleta:", false);

        // Mostra o inventário
        inventario.mostrarInventario(ui);

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
                "\nAlgo parece errado...", false);

        if (player.rep >= 1) {
            printf("\nUma pessoa senta ao seu lado..." +
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
