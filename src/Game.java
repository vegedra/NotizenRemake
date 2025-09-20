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
    Scanner enterScanner = new Scanner(System.in);

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

        // Itens no inventário - adiciona uma classe Itens no futuro
        player.addItem("Roupas");
        player.addItem("Documentos");
        player.addItem("Livros");

        System.out.println("Qual seu nome?");
        playerName = myScanner.nextLine();

        //System.out.println("\n" + playerName + "\n" + playerHP + "\n" + playerMoney + "\n" + playerSuit);
    }

    // Introdução a história
    public void gameIntro() {
        System.out.println("\nOlá, " + playerName + ".");
        System.out.println("Você é um diplomata à caminho da Alemanha.");
        System.out.println("Seus motivos oficiais são para negociação e observação.");
        System.out.println("Entretanto...");
        System.out.println("O que você realmente busca é informações a respeito de seu avô:");
        System.out.println("Aquele que o criou e que, agora, se encontra desaparecido...");

        // Pausa o jogo e espera por input para prosseguir
        System.out.println("\nPressione Enter para continuar.");
        enterScanner.nextLine();
        trainStation();
    }

    // Começo do jogo
    public void trainStation() {
        System.out.println("Após uma longa viagem, você finalmente chega ao porto.");
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

        System.out.println("\nPressione Enter para continuar.");
        enterScanner.nextLine();
        inTrain();
    }

    public void ficarNavio() {
        System.out.println("\nCapitão: Herr " + playerName + ", já chegamos. Por favor, desembarque.");
        System.out.println("Como você irá responder?\n");

        System.out.println("1) De forma educada");
        System.out.println("2) De forma grosseira");

        choice = myScanner.nextInt();

        if (choice == 1) {
            System.out.println("\nVocê acena a cabeça e se prepara para sair.");
            // Aumenta a reputação por ter sido educado
            playerRep += 1;
            playerKarma += 10;
            sairNavio();
        }
        else if (choice == 2){
            System.out.println("\nVocê olha com um olhar zangado para o capitão e estala a língua.");
            System.out.println("Capitão: ...");
            playerRep -= 1;
            playerKarma -= 10;
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

        System.out.println("\nPressione Enter para continuar.");
        enterScanner.nextLine();

        System.out.println("\nUma pessoa senta ao seu lado.");
        System.out.println("Desconhecido: Guten morgen!");
        System.out.println("\nComo você irá responder?");

        System.out.println("1) 'Guten morgen!'");
        System.out.println("2) 'Poderia se sentar em outro lugar?'");
        System.out.println("3) Ignorar");

        choice = myScanner.nextInt();

        // Educado
        if (choice == 1) {
            // Aumenta a reputação por ter sido educado
            playerRep += 1;
            playerKarma += 10;
        }
        // Grosso
        else if (choice == 2) {
            // Diminui a reputação por ter sido grosseiro
            playerRep -= 1;
            playerKarma -= 10;
        }
        // ignorar
        else if (choice == 3) {
            // Nada ocorre - neutro
        }
        else {
            inTrain();
        }
        cityDayOne();
    }

    // Na cidade, dia 1
    public void cityDayOne() {
        System.out.println("\nApós algumas horas no trem, você finalmente chega na cidade.");
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
            lookAround();
            // escreve o que o jogador ve
        }
        // Norte
        else if (choice == 2) {
            north();
        }
        // Sul
        else if (choice == 3) {
            System.out.println("Você retorna de onde venho.\nVocê está de volta na estação de trem. " +
                    "Não há nada para fazer aqui.\n");
            System.out.println("Pressione Enter para continuar.");
            enterScanner.nextLine();
            cityDayOne();
        }
        // Leste
        else if (choice == 4) {
            areaRestrita();
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

    public void areaRestrita() {
        System.out.println("Esta área está restrita...");
        enterScanner.nextLine();
    }

    public void lookAround() {
        System.out.println("Você olha ao seu redor...");
        enterScanner.nextLine();
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
            lookAround();
            // bla bla
        }
        // Desfile militar
        else if (choice == 2){
            //etc
        }
        // Inacessível
        else if (choice == 3){
            areaRestrita();
            north();
        }
        else if (choice == 4) {
            System.out.println("Você decide voltar para onde estava...");
            enterScanner.nextLine();
            cityDayOne();
        }
        else {
            north();
        }

    }

    // Evento = Carteira perdida
    public void west() {
        System.out.println("Você segue ao oeste.");
        System.out.println("Você chega até uma rua vazia. Há quase ninguém por aqui." +
                "Você tem um mau pressentimento.\n");

        if (player.haveItem("Carteira perdida")) {
            System.out.println("1) Seguir em frente");
            System.out.println("2) Voltar");

            choice = myScanner.nextInt();

            // Sim
            if (choice == 1){
                eventAlley();
            }
            // Voltar
            else {
                cityDayOne();
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
                else {
                    cityDayOne();
                }
            }
        }
    }

    public void eventoCarteira() {
        System.out.println("Você se agacha e percebe que é uma carteira perdida!");
        player.addItem("Carteira perdida");
        System.out.println("Você guarda a Carteira Perdida em sua maleta.");
        System.out.println("Você deveria procurar pelo dono dela agora...");

        // TODO: quando entregar a carteira, recebe uma quantidade aleatoria de dinheiro:
        // playerMoney = new java.util.Random().nextInt(10);    // De 0 a 9 $

        System.out.println("Você quer continuar seguindo em frente");
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