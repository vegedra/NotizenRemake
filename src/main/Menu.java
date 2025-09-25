// Notizen - Classe da Tela de menu
// Pedro Ivo Rocha - Digital Cake Studio, 2025

// IDEIAS: se não funcionar de outro jeito, usa uma abordagem de múltiplas janelas para o jogo então,
// com a principal sendo a maleta e outras janelas de jogo sendo outros objetos, por exemplo

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu {

    private JDialog statusDialog;
    private JTextArea statusTextArea;
    private JButton backButton;
    private Game game;

    public Menu(JFrame parentFrame, Game game) {
        this.game = game;
        createStatusUI(parentFrame);
    }

    private void createStatusUI(JFrame parentFrame) {
        statusDialog = new JDialog(parentFrame, "Status do Jogador", false); // false = não modal
        statusDialog.setSize(500, 400);
        statusDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        statusDialog.setLayout(new BorderLayout());
        statusDialog.setLocationRelativeTo(parentFrame); // Centraliza na janela principal

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Área de texto para mostrar status
        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);
        statusTextArea.setFont(new Font("Lucida", Font.PLAIN, 16));
        statusTextArea.setLineWrap(true);
        statusTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(statusTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Botão voltar
        backButton = new JButton("Voltar ao Jogo");
        backButton.setFont(new Font("Gothic", Font.PLAIN, 16));

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        statusDialog.add(mainPanel);
    }

    public void show() {
        updateStatusText();
        statusDialog.setVisible(true);
    }

    public void hide() {
        statusDialog.setVisible(false);
    }

    private void updateStatusText() {
        Player player = game.player;
        Inventario inventario = game.inventario;

        StringBuilder statusText = new StringBuilder();
        statusText.append("=== STATUS ===\n\n");
        statusText.append("Joseph\n");
        statusText.append("Pontos de Vida: ").append(player.hp).append("\n");
        statusText.append("Dinheiro: ").append(player.money).append(" ℛℳ\n");
        statusText.append("Karma: ").append(player.karma).append("\n");
        statusText.append("Reputação: ").append(player.rep).append("\n");
        statusText.append("Nível de suspeito: ").append(player.suspicion).append("\n");
        statusText.append("Dia ").append(player.day).append("\n");

        statusText.append("\n=== INVENTÁRIO ===\n");

        if (inventario.isEmpty()) {
            statusText.append("Você não possui itens no inventário.\n");
        } else {
            int itemNumber = 1;
            for (Item item : inventario.getItens()) {
                statusText.append(itemNumber).append(". ").append(item.toString()).append("\n");
                itemNumber++;
            }
        }

        statusTextArea.setText(statusText.toString());
        statusTextArea.setCaretPosition(0); // Rola para o topo
    }

    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}