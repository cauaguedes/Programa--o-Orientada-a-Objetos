import java.util.Scanner;

public class olaUsuario {
    public static void main(String[] args) {
        // Criando o objeto Scanner para ler a entrada do teclado
        Scanner leitor = new Scanner(System.in);

        // Solicitação de dados básica
        System.out.print("Digite seu nome: ");
        String nome = leitor.nextLine();

        // Exibindo a mensagem personalizada inicial
        System.out.println("Olá " + nome + ", bem-vindo ao mundo da programação em Java!");
        
        System.out.println("\n--- Ampliação da Atividade ---");

        // Solicitando informações adicionais
        System.out.print("Digite sua idade: ");
        int idade = leitor.nextInt();
        
        // Limpar o buffer do teclado
        leitor.nextLine(); 

        System.out.print("Digite seu curso: ");
        String curso = leitor.nextLine();

        // Exemplo de saída final formatada
        System.out.println("\nOlá " + nome + "!");
        System.out.println("Você tem " + idade + " anos.");
        System.out.println("Bem-vindo ao curso de " + curso + ".");

        // Fechando o leitor
        leitor.close();
    }
}