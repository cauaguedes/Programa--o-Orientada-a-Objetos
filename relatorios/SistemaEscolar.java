import java.util.Scanner;

public class SistemaEscolar {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- MENU INTERATIVO ---");
            System.out.println("1. Iniciar Cadastro de Alunos (Desafio Extra)");
            System.out.println("2. Verificar Idade e Nota Individual");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitor.nextInt();
            leitor.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    processarCincoAlunos(leitor);
                    break;
                case 2:
                    processarIndividual(leitor);
                    break;
                case 3:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        leitor.close();
    }

    // Nível 5: Desafio Extra (5 Alunos)
    public static void processarCincoAlunos(Scanner leitor) {
        int aprovados = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.println("\n--- Aluno " + i + " ---");
            System.out.print("Nota: ");
            double nota = leitor.nextDouble();
            if (nota >= 7) aprovados++;
        }
        System.out.println("\nTotal de alunos aprovados: " + aprovados);
    }

    // Níveis 1, 2, 3 e 4 integrados
    public static void processarIndividual(Scanner leitor) {
        // Validação de Nome
        System.out.print("Nome: ");
        String nome = leitor.nextLine();

        // Validação de Idade (Nível 1)
        int idade;
        do {
            System.out.print("Idade (maior que 0): ");
            idade = leitor.nextInt();
        } while (idade <= 0);

        // Validação de Nota e Média (Nível 4)
        double somaNotas = 0;
        System.out.print("Quantas notas deseja inserir para calcular a média? ");
        int qtdNotas = leitor.nextInt();

        for (int i = 1; i <= qtdNotas; i++) {
            double nota;
            do {
                System.out.print("Nota " + i + " (0 a 10): ");
                nota = leitor.nextDouble();
            } while (nota < 0 || nota > 10);
            somaNotas += nota;
        }
        double media = somaNotas / qtdNotas;

        // Saída de Dados
        System.out.println("\n--- RESULTADO ---");
        System.out.println("Nome: " + nome);
        System.out.println("Maior de idade: " + (idade >= 18 ? "Sim" : "Não"));

        // Classificação da Nota
        if (media >= 7) {
            System.out.println("Situação: Aprovado (Média: " + media + ")");
        } else if (media >= 5) {
            System.out.println("Situação: Recuperação (Média: " + media + ")");
        } else {
            System.out.println("Situação: Reprovado (Média: " + media + ")");
        }

        // Contagem (Laço de Repetição)
        System.out.print("Contagem até sua idade: ");
        for (int i = 1; i <= idade; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}