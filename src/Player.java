// Notizen
// @Pedro Ivo Rocha - Digital Cake Studio, 2025

import java.util.ArrayList;

public class Player {

    // Cria uma lista de itens vazia - array/lista que guarda strings
    // private = somente esta classe pode acessar o inventario
    private ArrayList<String> inventario = new ArrayList<>();

    // Adicionar itens ao inventario
    public void addItem(String item) {
        inventario.add(item);
        // System.out.println(item + " foi adicionado ao seu inventário.\n"); - coloca pra mostrar só no terminal depois
    }

    // Devolve False (0) ou True (1) caso o item especificado esteja no inventário
    public boolean haveItem(String item) {
        return inventario.contains(item);
    }

    // Mostrar inventario (botão na GUI quando o fizer)
    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("Sua maleta está vazio.");
        }
        else {
            System.out.println("Em sua maleta tem:");
            for (String item : inventario) {
                System.out.println(" -" + item);
            }
        }
    }
}