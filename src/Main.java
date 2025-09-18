// Notizen - Jogo de Pedro Ivo, 2025

public class Main {

    public static void main(String[] args) {
        // Imprime texto na tela
        System.out.println("Você acaba de desembarcar do navio.\n");

        // Variaveis String
        String playerWeapon;
        String playerClothing;

        playerWeapon = "Canivete";
        playerClothing = "Terno";

        // Variaveis Int
        int playerHP = 10;

        // Texto com variavel
        System.out.println("Em suas mãos: " + playerWeapon + ".");
        System.out.println("Você está vestindo: " + playerClothing + ".");
        System.out.println("Seu HP atual: " + playerHP + ".");

        System.out.println("\nUma bala perdida te atinge!");
        playerHP = playerHP - 3;
        System.out.println("-3 de HP!");
        System.out.println("Seu HP atual: " + playerHP + ".");
    }
}