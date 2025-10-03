// Notizen - Player Class
// @Pedro Ivo Rocha - Digital Cake Studio, 2025

package main;

// Representa o jogador no jogo
public class Player {

    UI ui = new UI();

    // Variaveis de instância/atributos da classe Game,
    // permitindo acesso para todas as funções da classe
    public int hp;
    public int money;
    public int day;
    public int karma;    // Se o jogador é bom ou mau - afeta o final
    public int rep;      // Reputação do jogador - afeta interações
    public int suspicion;      // Desconfiança das autoridades pelo jogador

    // Flags
    public boolean eventAlleyFlag;     // Se já foi roubado ou não no beco
    public boolean scarFlag;           // Cicatriz no queixo caso tenha reagido ao assalto
    public boolean devolveuCarteira;   // Se devolveu a carteira e foi para a cafeteria junto com a mulher
    public boolean visitouLoja1;
    public boolean descobriuDesfile;
    public boolean ignorouCarteira;

    // Setup inicial do jogador
    public void setup() {
        this.hp = 5;
        this.money = 33;
        this.karma = 100;
        this.suspicion = 0;
        this.rep = 0;
        this.day = 1;

        // Flags
        this.eventAlleyFlag = false;
        this.scarFlag = false;
        this.devolveuCarteira = false;
        this.visitouLoja1 = false;
        this.descobriuDesfile = false;
        this.ignorouCarteira = false;
    }

    // Pega stats formatado
    public String getStatus() {
        return "PV: " + hp + "\nDinheiro: ℛℳ" + money + "\nKarma: " + karma;
    }
}