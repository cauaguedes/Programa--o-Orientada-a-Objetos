import java.util.ArrayList;
import java.util.Scanner;

// Nível 5: Classe de objeto para armazenar na lista
class Aluno {
    private String nome;

    public Aluno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

public class GestaoCompleta {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        // Listas para diferentes propósitos
        ArrayList<Aluno> listaAlunos = new ArrayList<>(); // Lista de Objetos
        ArrayList<Double> listaNotas = new ArrayList<>(); // Nível 2: Lista de Números
        
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n========== SISTEMA ACADÊMICO ==========");
            System.out.println("1. Adicionar Aluno (Objeto)");
            System.out.println("2. Listar Alunos e Remover");
            System.out.println("3. Lançar Notas e Calcular Média (Nível 2)");
            System.out.println("4. Exibir Relatório de Notas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = leitor.nextInt();
            leitor.nextLine(); // Limpeza de buffer

            switch (opcao) {
                case 1:
                    // Nível 1: Entrada dinâmica
                    System.out.print("Digite o nome do aluno: ");
                    String nomeParaAdd = leitor.nextLine();
                    listaAlunos.add(new Aluno(nomeParaAdd));
                    System.out.println("Aluno adicionado com sucesso!");
                    break;

                case 2:
                    if (listaAlunos.isEmpty()) {
                        System.out.println("A lista está vazia.");
                    } else {
                        System.out.println("\n--- Lista de Alunos ---");
                        for (int i = 0; i < listaAlunos.size(); i++) {
                            System.out.println(i + " - " + listaAlunos.get(i).getNome());
                        }
                        
                        System.out.print("\nDeseja remover algum? Digite o índice ou -1 para cancelar: ");
                        int indice = leitor.nextInt();
                        if (indice >= 0 && indice < listaAlunos.size()) {
                            Aluno removido = listaAlunos.remove(indice);
                            System.out.println("Aluno " + removido.getNome() + " removido.");
                        }
                    }
                    break;

                case 3:
                    // Nível 2: Lista de números, soma e média
                    System.out.println("\n--- Lançamento de Notas ---");
                    System.out.print("Quantas notas deseja inserir? ");
                    int qtd = leitor.nextInt();
                    
                    for (int i = 1; i <= qtd; i++) {
                        System.out.print("Digite a nota " + i + ": ");
                        double nota = leitor.nextDouble();
                        listaNotas.add(nota);
                    }
                    
                    // Cálculo da média
                    double soma = 0;
                    for (double n : listaNotas) {
                        soma += n;
                    }
                    double media = listaNotas.isEmpty() ? 0 : soma / listaNotas.size();
                    System.out.printf("Média das notas inseridas: %.2f\n", media);
                    break;

                case 4:
                    System.out.println("\n--- Relatório de Notas (Nível 2) ---");
                    if (listaNotas.isEmpty()) {
                        System.out.println("Nenhuma nota lançada.");
                    } else {
                        System.out.println("Notas: " + listaNotas);
                        System.out.println("Total de registros: " + listaNotas.size());
                    }
                    break;

                case 5:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        leitor.close();
    }
}