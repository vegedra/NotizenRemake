// Notizen - UI Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class UI {

    // Cria objetos das classes swing
    JFrame window;              // A janela em si

    // Cria os paineis:
    JPanel titleNamePanel, startButtonPanel, exitButtonPanel, mainTextPanel, backgroundPanel,
            statButtonPanel,  choiceButtonPanel, playerPanel, picturePanel, inventoryPanel;

    // Cria o botão de iniciar jogo
    JButton startButton, exitButton, choice1, choice2, choice3, choice4,
            statsButton;

    // Cria os textos
    JTextArea mainTextArea;
    JLabel titleNameLabel, gameVersionLabel, pictureLabel, subtitleLabel, backgroundLabel,
            hpLabel, hpLabelNum, playerMoneyLabel, playerMoneyLabelNum, gameDate;

    ImageIcon image, backgroundImage;

    // Mostrar o inventário
    public JLabel[] inventoryLabels;

    // Cria as fontes (nome da fonte, estilo, tamanho
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font buttonFont = new Font("Gothic", Font.PLAIN, 22);
    Font buttonFont2 = new Font("Gothic", Font.PLAIN, 18);
    Font textFont = new Font("Lucida", Font.PLAIN, 20);
    Font textHud = new Font("Times New Roman", Font.PLAIN, 22);
    Font subtitleFont = new Font("Times New Roman", Font.ITALIC, 18);

    // Cria a tela de ínicio do jogo
    public void createUI(Game.ChoiceHandler cHandler) {

        // Inicializa o JFrame - WINDOW
        window = new JFrame();
        // Remove o botão de maximizar
        window.setResizable(false);
        // Tamanho da janela (width, height)
        window.setSize(800, 600);
        // Botão de fechar a janela e finalizar programa
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Cor de fundo da janela
        window.getContentPane().setBackground(Color.black);
        // Para usar um layout customizado
        window.setLayout(null);

        // ---- IMAGEM DE FUNDO ----
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 800, 600);
        backgroundPanel.setLayout(null);

        // Tenta carregar a imagem de fundo
        try {
            backgroundImage = new ImageIcon(".//res//background.jpg");
            backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, 800, 600);
            backgroundPanel.add(backgroundLabel);
        } catch (Exception e) {
            // Fallback: fundo preto se a imagem não carregar
            backgroundLabel.setBackground(Color.black);
        }

        // ---- Tela de ínicio ----
        // Cria o painel/espaço para o título do jogo
        titleNamePanel = new JPanel();
        // Coloca o painel nas seguintes coordenadas (startX, startY, witdh, height)
        titleNamePanel.setBounds(100, 50, 600, 150);
        // Fundo do painel de titulo
        titleNamePanel.setBackground(new Color(0, 0, 0, 0));

        // Cria o texto do título do jogo
        titleNameLabel = new JLabel("NOTIZEN");
        // Define a cor do texto do título
        titleNameLabel.setForeground(Color.white);
        // Define a fonte (fonte e tamanho) ao texto de título
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        // Subtítulo
        subtitleLabel = new JLabel("DIGITAL CAKE STUDIO");
        subtitleLabel.setForeground(Color.yellow);
        subtitleLabel.setFont(subtitleFont);
        subtitleLabel.setBounds(300, 5, 200, 50);
        window.add(subtitleLabel);

        // Cria o painel do botão de iniciar o jogo
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 50);
        startButtonPanel.setBackground(new Color(0, 0, 0, 0));

        // Cria o botão de iniciar o jogo
        startButton = new JButton("JOGAR");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(buttonFont);
        startButton.setFocusPainted(false); // Para não marcar o texto quando selecionado
        // Faz o botão levar para outra função usando lambda:
        startButton.addActionListener(cHandler);
        startButton.setActionCommand("start");


        // Cria o painel do botão de sair do jogo
        exitButtonPanel = new JPanel();
        exitButtonPanel.setBounds(300, 450, 200, 50);
        exitButtonPanel.setBackground(new Color(0, 0, 0, 0));

        // Cria o botão de sair do jogo
        exitButton = new JButton("SAIR");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(buttonFont);
        exitButton.setFocusPainted(false); // Para não marcar o texto quando selecionado
        // Faz o botão levar para outra função usando lambda:
        exitButton.addActionListener((e) -> System.exit(0));


        // Cria o texto do texto de versão - sem panel, pois é mais simples
        gameVersionLabel = new JLabel("Ver 0.6.5");
        gameVersionLabel.setForeground(Color.yellow);
        gameVersionLabel.setFont(new Font("Futura", Font.PLAIN, 20));
        gameVersionLabel.setBounds(695, 470, 300, 150);


        // Coloca o texto do título e o botão em seus paíneis para serem exibidos
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        exitButtonPanel.add(exitButton);

        // Adiciona os paineis de título e botão na janela
        window.add(gameVersionLabel);   // Adiciona o texto de versão diretamente na janela
        window.add(titleNamePanel);
        window.add(startButtonPanel);
        window.add(exitButtonPanel);
        window.add(backgroundPanel);

        // ---- Game Screen ----
        // Cria a área do texto principal
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 350, 445, 200);
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(new BorderLayout());
        window.add(mainTextPanel);

        // Cria a area do texto em si
        mainTextArea = new JTextArea("Teste da área principal de texto.");
        //mainTextArea.setBounds(50, 350, 430, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        // Cria JScrollPane para o mainTextArea
        JScrollPane scrollPane = new JScrollPane(mainTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.black);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 200));
        mainTextPanel.add(scrollPane);

        // Cria o paínel dos botões de escolha do jogador
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(510, 375, 250, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));   // Layout que mostra os botões embaixo um do outro
        window.add(choiceButtonPanel);

        // Cria os botões em si e suas funcionalidades
        choice1 = choiceButton("Escolha 1", buttonFont);
        choice1.addActionListener(cHandler);
        choice1.setActionCommand("c1");

        choice2 = choiceButton("Escolha 2", buttonFont);
        choice2.addActionListener(cHandler);
        choice2.setActionCommand("c2");

        choice3 = choiceButton("Escolha 3", buttonFont);
        choice3.addActionListener(cHandler);
        choice3.setActionCommand("c3");

        choice4 = choiceButton("Escolha 4", buttonFont);
        choice4.addActionListener(cHandler);
        choice4.setActionCommand("c4");

        // Painel do inventário
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(510, 65, 250, 300);
        inventoryPanel.setBackground(Color.black);
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        inventoryPanel.setLayout(new GridLayout(6,1));
        window.add(inventoryPanel);

        // Labels do inventário
        JLabel inventoryTitle = new JLabel("        - INVENTÁRIO- ");
        inventoryTitle.setForeground(Color.white);
        inventoryTitle.setFont(textHud);
        inventoryPanel.add(inventoryTitle);

        // Para imprimir os itens no inventário
        inventoryLabels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            inventoryLabels[i] = new JLabel("- Vazio");
            inventoryLabels[i].setForeground(Color.white);
            inventoryLabels[i].setFont(textHud);
            //inventoryLabels[i].setHorizontalAlignment(SwingConstants.LEFT);
            inventoryPanel.add(inventoryLabels[i]);
        }

        // ---- IMAGENS ----
        picturePanel = new JPanel();
        picturePanel.setBounds(45, 60, 450, 280);
        picturePanel.setBackground(Color.black);
        window.add(picturePanel);

        pictureLabel = new JLabel("Imagem não encontrada");  // Pode mostrar texto e imagens
        pictureLabel.setForeground(Color.white);
        pictureLabel.setFont(buttonFont);

        // Tenta carregar a imagem com try-catch
        try {
            image = new ImageIcon(".//res//imageTest.png");
            pictureLabel.setIcon(image);
            pictureLabel.setText(""); // Remove o texto se a imagem carregar
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
            // Mantém o texto "Imagem não encontrada"
        }
        picturePanel.add(pictureLabel);

        // --- PAINEL BOTÃO DE AÇÃO (Inventário/Stats) ---
        statButtonPanel = new JPanel();
        statButtonPanel.setBounds(660, 15, 100, 40);
        statButtonPanel.setBackground(Color.pink);
        statButtonPanel.setLayout(new GridLayout(1, 1));   // Layout que mostra os botões embaixo um do outro
        window.add(statButtonPanel);

        JButton statButton = choiceButton("MENU", buttonFont2);
        statButton.addActionListener(cHandler);
        statButton.setActionCommand("stats");
        statButtonPanel.add(statButton);

        // Adiciona os botões no panel
        choiceButtonPanel.add(choice1);
        choiceButtonPanel.add(choice2);
        choiceButtonPanel.add(choice3);
        choiceButtonPanel.add(choice4);

        // Cria um novo painel para as informações do jogador (HUD)
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 14, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 6));
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

        // Para mostrar a janela e seus conteúdos
        window.setVisible(true);
    }

    // Função que cria os botões
    public JButton choiceButton(String text, Font font) {
        JButton button;
        button = new JButton();
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(font);
        button.setText(text);
        button.setFocusPainted(false);
        return button;
    }

    // Mostra e muda a imagem na tela
    public void showImage(String imagePath) {
        try {
            image = new ImageIcon(imagePath);
            pictureLabel.setIcon(image);
            pictureLabel.setText(""); // Remove texto se imagem carregar
            pictureLabel.revalidate();
            pictureLabel.repaint();
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + imagePath);
            pictureLabel.setIcon(null);
            pictureLabel.setText("Imagem não encontrada: " + imagePath);
        }
    }
}
