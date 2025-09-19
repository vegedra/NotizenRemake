// Notizen
// @Pedro Ivo Rocha - Digital Cake Studio, 2025

import java.util.Scanner;

public class Game {

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

        playerHP = 6;
        playerMoney = 33;
        playerKarma = 100;
        playerSuit = "Terno";

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
        System.out.println("Após uma longa viagem, você finalmente chega.");
        System.out.println("O capitão anuncia a chegada e pede para os passageiros desembarcarem.");
        System.out.println("O que você faz?\n");

        System.out.println("1) Sair do navio");
        System.out.println("2) Ficar");

        choice = myScanner.nextInt();

        // Sair
        if (choice == 1) {
            System.out.println("Você decide sair do navio.");
            System.out.println("Porém, antes de sair, você decide verificar sua mala.");
            // código do inventário e resto dessa seção
            System.out.println("Pressione Enter para continuar.");
            enterScanner.nextLine();
            inTrain();
        }
        // Ficar
        if (choice == 2) {
            System.out.println("\nCapitão: Herr " + playerName + ", já chegamos. Por favor, desembarque.");
            System.out.println("Como você irá responder?\n");

            System.out.println("1) De forma educada");
            System.out.println("2) De forma grosseira");
        }
        // Comando desconhecido
        else {
            trainStation();
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
        if (choice == 2) {
            // Diminui a reputação por ter sido grosseiro
            playerRep -= 1;
            playerKarma -= 10;
        }
        // ignorar
        if (choice == 3) {
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
            System.out.println("Você olha ao seu redor:");
            // escreve o que o jogador ve
        }
        // Norte
        if (choice == 2) {
            north();
        }
        // Sul
        if (choice == 3) {
            System.out.println("Você retorna de onde venho.\nVocê está de volta na estação de trem. " +
                    "Não há nada para fazer aqui.\n");
            System.out.println("Pressione Enter para continuar.");
            enterScanner.nextLine();
            cityDayOne();
        }
        // Leste
        if (choice == 4) {
            System.out.println("Esta área está restrita...");
            enterScanner.nextLine();
            cityDayOne();
        }
        // Oeste
        if (choice == 5) {
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
            System.out.println("Você olha ao seu redor:");
            // bla bla
        }
        // Desfile militar
        if (choice == 2){
            //etc
        }
        // Inacessível
        if (choice == 3){
            System.out.println("Esta área está restrita...");
            enterScanner.nextLine();
            north();
        }
        if (choice == 4) {
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
                "Você tem um mau pressentimento.");
        enterScanner.nextLine();
        System.out.println("Enquanto caminha, você percebe algo no chão. Olhar?");

        System.out.println("1) Sim");
        System.out.println("2) Não");

        choice = myScanner.nextInt();

        // Sim
        if (choice == 1){
            System.out.println("Você se agacha e percebe que é uma carteira perdida!");
            // inventario ++ carteira
        }
        // Não
        if (choice == 2){
            System.out.println("Você escolhe ignorar o objeto e continuar andando.");
            System.out.println("1) Seguir em frente");
            System.out.println("2) Voltar");

            choice = myScanner.nextInt();

            // Sim
            if (choice == 1){
                eventAlley();
            }
            // Sim
            if (choice == 1){
                west();
            }
        }
        else {
            west();
        }
    }

    public void eventAlley() {

    }
}