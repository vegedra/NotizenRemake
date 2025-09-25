// Notizen - item Class
// Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

// Qualquer objeto de inventário
public class Item {

    private String nome;    // Nome do item
    private String desc;    // Descrição do item
    private int valor;      // Preço de venda do item
    private int peso;       // Peso do item

    // Construtor - cria o item com nome, desc e valor
    public Item(String nome, String desc, int valor, int peso) {
        this.nome = nome;
        this.desc = desc;
        this.valor = valor;
        this.peso = peso;
    }

    // Getters - metodos para acessar as info do item
    public String getNome() { return nome; }
    public String getDesc() { return desc; }
    public int getValor() { return valor; }
    public int getPeso() { return peso; }

    @Override
    // Para imprimir o item
    public String toString() {
        return nome + " (" + desc + ") - Valor: " + valor + " ℛℳ | " + peso + " kg.";
    }
}
