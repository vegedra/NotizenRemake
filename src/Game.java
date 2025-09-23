// Notizen - Game Class
// @Pedro Ivo Rocha - Digital Cake Studio, 2025

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    // ----------------- Variaveis da GUI -----------------
    // Cria objetos das classes do std
    JFrame window;              // A janela em si
    JButton startButton, choice1, choice2, choice3, choice4;        // Cria o botão de iniciar jogo

    // Cria os paineis:
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;

    // Cria os textos
    JTextArea mainTextArea;
    JLabel titleNameLabel, gameVersionLabel,
    hpLabel, hpLabelNum, playerMoneyLabel, playerMoneyLabelNum, gameDate;

    // Cria uma fonte (nome da fonte, estilo, tamanho
    Font titleFont =  new Font("Times New Roman", Font.PLAIN, 80);
    Font buttonFont =  new Font("Gothic", Font.PLAIN, 22);
    Font textFont =  new Font("Lucida", Font.PLAIN, 20);
    Font textHud =  new Font("Times New Roman", Font.PLAIN, 22);

    // Para lidar com os inputs do jogador
    ChoiceHandler choiceHandler = new ChoiceHandler();


    // ----------------- Variaveis do jogo -----------------

    // Cria um objeto Player para ter o inventário - static para poder acessar de qualquer método
    static Player player = new Player();

    // Variaveis de instância/atributos da classe Game,
    // permitindo acesso para todas as funções da classe
    int playerHP;
    int playerMoney;
    int gameDay;
    int choice;
    int playerKarma;    // Se o jogador é bom ou mau - afeta o final
    int playerRep;      // Reputação do jogador - afeta interações
    int playerSuspicion;      // Desconfiança das autoridades pelo jogador
    boolean eventAlleyFlag;     // Se já foi roubado ou não no beco
    boolean scarFlag;           // Cicatriz no queixo caso tenha reagido ao assalto
    boolean devolveuCarteira;   // Se devolveu a carteira e foi para a cafeteria junto com a mulher
    boolean visitouLoja1;
    String playerSuit;
    String playerName;
    String playerPosition;

    public static void main(String[] args) {
        // Nova instancia (novo objeto da classe Game)
        new Game();
    }

    // Cria a janela do jogo
    public Game() {

        // Inicializa o JFrame
        window = new JFrame();
        // Tamanho da janela (width, height)
        window.setSize(800, 600);
        // Botão de fechar a janela e finalizar programa
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Cor de fundo da janela
        window.getContentPane().setBackground(Color.black);
        // Para usar um layout customizado
        window.setLayout(null);

        // Cria o painel/espaço para o título do jogo
        titleNamePanel = new JPanel();
        // Coloca o painel nas seguintes coordenadas (startX, startY, witdh, height)
        titleNamePanel.setBounds(100, 100, 600, 150);
        // Fundo do painel de titulo
        titleNamePanel.setBackground(Color.black);

        // Cria o texto do título do jogo
        titleNameLabel = new JLabel("NOTIZEN");
        // Define a cor do texto do título
        titleNameLabel.setForeground(Color.white);
        // Define a fonte (fonte e tamanho) ao texto de título
        titleNameLabel.setFont(titleFont);

        // Cria o painel do botão de iniciar o jogo
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        // Cria o botão de iniciar o jogo
        startButton = new JButton("JOGAR");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(buttonFont);
        startButton.setFocusPainted(false); // Para não marcar o texto quando selecionado
        // Faz o botão levar para outra função usando lambda:
        startButton.addActionListener((e)-> createGameScreen());


        // Cria o texto do texto de versão - sem panel, pois é mais simples
        gameVersionLabel = new JLabel("Ver0.3.0");
        gameVersionLabel.setForeground(Color.yellow);
        gameVersionLabel.setFont(new Font("Futura", Font.PLAIN, 20));
        gameVersionLabel.setBounds(695, 470, 300, 150);


        // Coloca o texto do título e o botão em seus paíneis para serem exibidos
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        // Adiciona os paineis de título e botão na janela
        window.add(gameVersionLabel);   // Adiciona o texto de versão diretamente na janela
        window.add(titleNamePanel);
        window.add(startButtonPanel);

        // Para mostrar a janela e seus conteúdos
        window.setVisible(true);
    }

    // Janela principal do jogo
    public void createGameScreen() {

        // Quando se cria a nova janela do jogo, desabilita as do menu inicial
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        gameVersionLabel.setVisible(false);

        // Cria a área do texto principal
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);

        // Cria a area do texto em si
        mainTextArea = new JTextArea("Teste da área principal de texto. Notizen é um jogo " +
                "de aventura baseado em texto feito por Pedro Ivo. 2019-2025.");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);         // Trava o texto se ele for longo
        mainTextArea.setWrapStyleWord(true);    // Não corta as palavras ao chegar no fim da área
        mainTextArea.setEditable(false);        // Não permitir que o jogador edite o texto
        mainTextPanel.add(mainTextArea);

        // Cria o paínel dos botões de escolha do jogador
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1));   // Layout que mostra os botões embaixo um do outro
        window.add(choiceButtonPanel);

        // Cria os botões em si e suas funcionalidades
        choice1 = choiceButton("Escolha 1");
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");

        choice2 = choiceButton("Escolha 2");
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");

        choice3 = choiceButton("Escolha 3");
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");

        choice4 = choiceButton("Escolha 4");
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");

        // Adiciona os botões no panel
        choiceButtonPanel.add(choice1);
        choiceButtonPanel.add(choice2);
        choiceButtonPanel.add(choice3);
        choiceButtonPanel.add(choice4);

        // Cria um novo painel para as informações do jogador (HUD)
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 14, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        playerPanel.setVisible(false);
        window.add(playerPanel);

        // Barra de HUD com informações do jogo
        hpLabel = new JLabel("PV: ");
        hpLabel.setFont(textHud);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        // ------------------------------------
        hpLabelNum = new JLabel();
        hpLabelNum.setFont(textHud);
        hpLabelNum.setForeground(Color.white);
        playerPanel.add(hpLabelNum);
        // ------------------------------------
        playerMoneyLabel = new JLabel("ℛℳ: ");
        playerMoneyLabel.setFont(textHud);
        playerMoneyLabel.setForeground(Color.white);
        playerPanel.add(playerMoneyLabel);
        // ------------------------------------
        playerMoneyLabelNum = new JLabel();
        playerMoneyLabelNum.setFont(textHud);
        playerMoneyLabelNum.setForeground(Color.white);
        playerPanel.add(playerMoneyLabelNum);
        // ------------------------------------
        gameDate = new JLabel();
        gameDate.setFont(textHud);
        gameDate.setForeground(Color.white);
        playerPanel.add(gameDate);

        playerSetup();
    }

    // Inicializa as variaveis do jogador
    public void playerSetup() {

        // Definição dos valores iniciais
        playerHP = 5;
        playerMoney = 33;
        playerKarma = 100;
        playerSuspicion = 0;
        playerSuit = "Terno";
        playerName = "Joseph";
        gameDay = 1;
        eventAlleyFlag = false;
        scarFlag = false;
        devolveuCarteira = false;
        visitouLoja1 = false;

        // Atribuindo valores aos Label
        hpLabelNum.setText("" + playerHP);
        playerMoneyLabelNum.setText("" + playerMoney);
        gameDate.setText("Dia " + gameDay);

        // Itens no inventário - TODO: adiciona uma classe Itens no futuro
        player.addItem("Roupas");
        player.addItem("Documentos");
        player.addItem("Livros");
        player.addItem("Reserva");

        // Inicia o jogo
        gameIntro();
    }

    // Função que cria os botões
    public JButton choiceButton(String text) {
        JButton button;
        button = new JButton();
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(buttonFont);
        button.setText(text);
        button.setFocusPainted(false);
        return button;
    }

    // Introdução do jogo
    public void gameIntro() {

        // Define em que 'janela/room' o jogador está
        playerPosition = "gameIntro";

        // Mostra o texto
        mainTextArea.setText("29 de Agosto, 1939" +
                "\nEu sou Joseph. Sou um diplomata à caminho da Alemanha." +
                "\nMeus motivos oficiais são para negociação e observação, entretanto, " +
                "o que realmente busco é informação a respeito do meu avô:" +
                " aquele que me criou e que, agora, se encontra desaparecido após enviar uma carta para mim...\n" +
                "\nMe hospedei no mesmo hotel em que ele estava. Preciso chegar lá até o fim do dia.");

        // Escolhas
        choice1.setText("Continuar");
        choice2.setText("-");
        choice3.setText("-");
        choice4.setText("-");
    }

    // Começo do jogo
    public void trainStation() {

        playerPosition = "trainStation";
        mainTextArea.setText("Após uma longa viagem de navio, você finalmente chega ao porto." +
                "\nO capitão anuncia a chegada e pede para os passageiros desembarcarem.");

        choice1.setText("Sair do navio");
        choice2.setText("Ficar");
        choice3.setText("-");
        choice4.setText("-");
    }

    // Se sai de uma vez do navio
    public void sairNavio() {

        playerPosition = "sairNavio";
        playerPanel.setVisible(true);

        mainTextArea.setText("Você decide sair do navio." +
                "\nAntes de sair, você decide verificar sua maleta:");

        // Código do inventário -- corrige depois que separar as classes
        player.mostrarInventario();

        choice1.setText("Continuar");
        choice2.setText("-");
        choice3.setText("-");
        choice4.setText("-");
    }

    // Se o jogador escolhe permanecer no navio
    public void ficarNavio() {

        playerPosition = "ficarNavio";
        mainTextArea.setText("Capitão: Herr Joseph, já chegamos. Por favor, desembarque.\n" +
                "\nComo você irá responder?");

        choice1.setText("De forma educada");
        choice2.setText("De forma arrogante");
        choice3.setText("-");
        choice4.setText("-");
    }

    // Tela de continuar
    public void respostaNavio(boolean educado) {
        playerPosition = "respostaNavio";

        // Se foi arrogante
        if (!educado) {
            playerRep -= 1;
            playerKarma -= 10;

            mainTextArea.setText("Você olha com um olhar zangado para o capitão e estala a língua." +
                                    "\nCapitão: ...");
        }
        // Se foi educado
        else {
            playerRep += 1;
            playerKarma += 10;

            mainTextArea.setText("Você acena com a cabeça e se prepara para sair.");
        }
        // Debug
        System.out.println("playerRep: " + playerRep +
            "\nplayerKarma: " + playerKarma);

        choice1.setText("Continuar");
        choice2.setText("-");
        choice3.setText("-");
        choice4.setText("-");
    }




    // Lida com o input do jogador nos botões-GUI
    public class ChoiceHandler implements ActionListener {

        // Metodo chamado quando um botão é clicado
        public void actionPerformed(ActionEvent event) {

            // Recebe o botão apertado e o armazena na String
            String playerChoice = event.getActionCommand();

            // Lida com todos os movimentos possíveis do jogador
            switch (playerPosition) {
                case "gameIntro":
                    switch (playerChoice) {
                        case "c1": trainStation(); break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    } break;
                case "trainStation":
                    switch (playerChoice) {
                        case "c1": sairNavio(); break;
                        case "c2": ficarNavio(); break;
                        case "c3": break;
                        case "c4": break;
                    } break;
                case "ficarNavio":
                    switch (playerChoice) {
                        case "c1":
                            respostaNavio(false);
                            break;
                        case "c2":
                            respostaNavio(true);
                            break;
                        case "c3": break;
                        case "c4": break;
                    } break;
                case "respostaNavio":
                    switch (playerChoice) {
                        case "c1":
                            respostaNavio(false);
                            break;
                        case "c2": sairNavio(); break;
                        case "c3": break;
                        case "c4": break;
                    } break;
                }
        }
    }
