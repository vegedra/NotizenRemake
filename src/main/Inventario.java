// Notizen - Inv Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    // Inicializa a lista de itens
    private List<Item> inventario;

    // Construtor para inicializar a lista
    public Inventario() {
        this.inventario = new ArrayList<>();
    }

    // Inicialização do inventário padrão do jogador
    public void setup() {
        addItem(new Item("Roupas", "Uma muda de roupas", 5, 1));
        addItem(new Item("Documentos", "Documentos importantes", 0, 0));
        addItem(new Item("Reserva", "Sua reserva no hotel", 0, 0));
    }

    // Adiciona um item ao inventário
    public void addItem(Item item) {
        if (item != null) {
            inventario.add(item);
        }
    }

    // Remove um item pelo nome
    public boolean removeItem(String nomeItem) {
        return inventario.removeIf(item -> item.getNome().equalsIgnoreCase(nomeItem));
    }

    // Verifica se o jogador possui um item pelo nome
    public boolean haveItem(String nomeItem) {
        return inventario.stream()
                .anyMatch(i -> i.getNome().equalsIgnoreCase(nomeItem));
    }

    // Obter quantidade de itens no inventário
    public int getQuantidadeItens() {
        return inventario.size();
    }

    // Verifica se o inventário está vazio
    public boolean isEmpty() {
        return inventario.isEmpty();
    }

    // Limpa o inventário
    public void clear() {
        inventario.clear();
    }

    // Mostra o inventário GUI
    public void mostrarInventario(UI ui) {
        if (inventario.isEmpty()) {
            // Append mostra o texto sem substituir o que está no Story (antes de chamar a função)
            ui.mainTextArea.append("\nVocê não possui itens no inventário.");
        } else {
            ui.mainTextArea.append("\n\nInventário:");
            for (int i = 0; i < inventario.size(); i++) {
                ui.mainTextArea.append("\n" + (i + 1) + ". " + inventario.get(i));
            }
        }
    }

    // Obter lista de itens
    public List<Item> getItens() {
        return new ArrayList<>(inventario);
    }
}
