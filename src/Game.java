// Notizen
// @Pedro Ivo Rocha - Digital Cake Studio, 2025

import java.util.Scanner;

public class Game {

    // Cria um objeto Player para ter o inventário - static para poder acessar
    // de qualquer método
    static Player player = new Player();

    // Variaveis de instancia/atributos da classe Game,
    // permitindo acesso para todas as funções da classe
    int playerHP;
    int playerMoney;
    int choice;
    int playerKarma;    // Se o jogador é bom ou mau - afeta o final
    int playerRep;      // Reputação do jogador - afeta interações
    String playerSuit;
    String playerName;

    // Cria os scanners - recebe input do jogador
    Scanner myScanner = new Scanner(System.in);

    // Função principal dentro da classe Game - é o ponto de entrada do programa
    public static void main(String[] args) {

        // Cria uma variavel chamada 'game' que guarda objetos do tipo Game
        Game game;
        // 'game' guarda a nova instancia (novo objeto da classe Game)
        game = new Game();

        // Chama a função que está dentro da classe Game:
        // Quando se está no main, deve-se chamar o objeto também
        game.playerSetup();
        game.gameIntro();
    }

    // Função que inicializa o jogador
    public void playerSetup() {

        // Restante das variaveis
        playerHP = 6;
        playerMoney = 33;
        playerKarma = 100;
        playerSuit = "Terno";
        playerName = "Joseph";

        // Itens no inventário - adiciona uma classe Itens no futuro
        player.addItem("Roupas");
        player.addItem("Documentos");
        player.addItem("Livros");
    }

    // Introdução a história
    public void gameIntro() {
        System.out.println("29 de Agosto, 1939");
        System.out.println("\nEu sou " + playerName + ".");
        System.out.println("Sou um diplomata à caminho da Alemanha.");
        System.out.println("Meus motivos oficiais são para negociação e observação.");
        System.out.println("Entretanto...");
        System.out.println("O que realmente busco é informação a respeito do meu avô:");
        System.out.println("Aquele que me criou e que, agora, se encontra desaparecido...");

        // Pausa o jogo e espera por input para prosseguir
        pressEnter();
        trainStation();
    }

    // Pausa o jogo e espera por input para prosseguir
    public void pressEnter() {
        System.out.println("\nPressione Enter para continuar.\n");
        myScanner.nextLine();
    }

    // Começo do jogo
    public void trainStation() {
        System.out.println("Após uma longa viagem de navio, você finalmente chega ao porto.");
        System.out.println("O capitão anuncia a chegada e pede para os passageiros desembarcarem.");
        System.out.println("O que você faz?\n");

        System.out.println("1) Sair do navio");
        System.out.println("2) Ficar");

        choice = myScanner.nextInt();

        // Sair
        if (choice == 1) {
            System.out.println("Você decide sair do navio.");
            sairNavio();
        }
        // Ficar - else if quando apenas uma opção pode ser verdadeira*
        else if (choice == 2) {
            System.out.println("Você decide ficar mais um pouco no navio...");
            ficarNavio();
        }
        // Comando desconhecido
        else {
            trainStation();
        }
    }

    public void sairNavio() {
        System.out.println("Antes de sair, você decide verificar sua maleta.\n");

        // Código do inventário:
        player.mostrarInventario();

        pressEnter();
        inTrain();
    }

    public void ficarNavio() {
        System.out.println("\nCapitão: Herr " + playerName + ", já chegamos. Por favor, desembarque.");
        System.out.println("Como você irá responder?\n");

        System.out.println("1) De forma educada");
        System.out.println("2) De forma grosseira");

        choice = myScanner.nextInt();

        if (choice == 1) {
            System.out.println("\nVocê acena com a cabeça e se prepara para sair.");
            // Aumenta a reputação por ter sido educado
            playerRep += 1;
            playerKarma += 10;
            pressEnter();
            sairNavio();
        }
        else if (choice == 2){
            System.out.println("\nVocê olha com um olhar zangado para o capitão e estala a língua.");
            System.out.println("Capitão: ...");
            playerRep -= 1;
            playerKarma -= 10;
            pressEnter();
            sairNavio();
        }
        else {
            ficarNavio();
        }
    }

    // Do trem até a cidade
    public void inTrain() {

        System.out.println("Você está dentro do trem.");
        System.out.println("A vista lá fora é bela, porém...");
        System.out.println("Algo parece errado...");

        pressEnter();

        if (playerRep >= 1) {
            System.out.println("Uma pessoa senta ao seu lado...");
            System.out.println("Desconhecido: Guten morgen!");
            System.out.println("Desconhecido: Vi que o senhor parece ser um senhor bastante educado!");
            System.out.println("Desconhecido: Continue assim! Ser educado com as pessoas tem seus beneficios.");
            System.out.println("Desconhecido: Aqui, tome isto:");
            playerMoney += 2;
            System.out.println("Você recebeu 2 ℛℳ!");
            System.out.println("Desconhecido: O suficiente para uma boa xícara de café.. Ha, ha!");
            pressEnter();
        }
        else if (playerRep <= -1) {
            System.out.println("Uma pessoa senta ao seu lado...");
            System.out.println("Desconhecido: Hmf...");
            System.out.println("Parece que você ficou com uma má fama...");
            pressEnter();
        }
        // Continua das condições anteriores ou vem aqui direto caso playerRep == 0
        cityDayOne();
    }

    // Na cidade, dia 1
    public void cityDayOne() {
        System.out.println("Após algumas horas no trem, você finalmente chega na cidade.");
        System.out.println("Ainda há tempo até a hora do check-in no hotel.");

        System.out.println("\nO que você irá fazer?");
        System.out.println("1) Olhar ao seu redor");
        System.out.println("2) Ir para o norte");
        System.out.println("3) Ir para o sul");
        System.out.println("4) Ir para o leste");
        System.out.println("5) Ir para o oeste");

        choice = myScanner.nextInt();

        // Look around
        if (choice == 1) {
            System.out.println("Você olha ao seu redor...");
            // escreve o que o jogador vê - descrição das direções
            pressEnter();
            cityDayOne();
        }
        // Norte
        else if (choice == 2) {
            north();
        }
        // Sul
        else if (choice == 3) {
            System.out.println("Você retorna de onde veio.\nVocê está de volta na estação de trem. " +
                    "Não há nada para fazer aqui.");
            pressEnter();
            cityDayOne();
        }
        // Leste
        else if (choice == 4) {
            System.out.println("Esta área está restrita...");
            pressEnter();
            cityDayOne();
        }
        // Oeste
        else if (choice == 5) {
            west();
        }
        // Comando desconhecido
        else {
            cityDayOne();
        }
    }

    // Praça
    public void north() {
        System.out.println("Você caminha até chegar em uma praça.");
        // etc

        System.out.println("\nO que você irá fazer?");
        System.out.println("1) Olhar");
        System.out.println("2) Ir para o norte");
        System.out.println("3) Ir para o leste");
        System.out.println("3) Voltar");

        choice = myScanner.nextInt();

        // Conversar com NPC
        if (choice == 1){
            System.out.println("Você olha ao seu redor...");
            // bla bla
            pressEnter();
        }
        // Desfile militar
        else if (choice == 2){
            //etc
        }
        // Inacessível
        else if (choice == 3){
            System.out.println("Esta área está restrita...");
            pressEnter();
            north();
        }
        else if (choice == 4) {
            System.out.println("Você decide voltar para onde estava...");
            pressEnter();
            cityDayOne();
        }
        else {
            north();
        }

    }

    // Evento = Carteira perdida
    public void west() {
        System.out.println("\nVocê segue ao oeste.");
        System.out.println("Você chega até uma rua vazia. Não há quase ninguém por aqui." +
                "\nVocê tem um mau pressentimento.\n");

        if (player.haveItem("Carteira perdida")) {
            System.out.println("1) Seguir em frente");
            System.out.println("2) Voltar");

            choice = myScanner.nextInt();

            // Sim
            if (choice == 1){
                eventAlley();
            }
            // Voltar
            else if (choice == 2) {
                cityDayOne();
            }
            else {
                west();
            }
        }
        else {
            System.out.println("Enquanto caminha, você percebe algo no chão. Olhar?");

            System.out.println("1) Sim");
            System.out.println("2) Não");

            choice = myScanner.nextInt();

            // Sim
            if (choice == 1){
                eventoCarteira();
            }
            // Não
            else if (choice == 2){
                System.out.println("Você escolhe ignorar o objeto e continuar andando.");
                System.out.println("1) Seguir em frente");
                System.out.println("2) Voltar");

                choice = myScanner.nextInt();

                // Sim
                if (choice == 1){
                    eventAlley();
                }
                // Sim
                else if (choice == 2) {
                    cityDayOne();
                }
                else {
                    west();
                }
            }
        }
    }

    public void eventoCarteira() {
        System.out.println("\nVocê se agacha e percebe que é uma carteira perdida!");
        player.addItem("Carteira perdida");
        System.out.println("Você guarda a Carteira Perdida em sua maleta.");
        System.out.println("Você deveria procurar pelo dono dela agora...");

        // TODO: quando entregar a carteira, recebe uma quantidade aleatoria de dinheiro:
        // playerMoney = new java.util.Random().nextInt(10);    // De 0 a 9 $

        System.out.println("\nVocê quer continuar seguindo em frente?");
        System.out.println("1) Seguir em frente");
        System.out.println("2) Voltar");

        choice = myScanner.nextInt();

        // Sim
        if (choice == 1){
            eventAlley();
        }
        // Sim
        else {
            cityDayOne();
        }
    }

    public void eventAlley() {

    }
}