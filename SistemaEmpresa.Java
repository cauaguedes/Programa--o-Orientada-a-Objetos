import java.util.ArrayList;
import java.util.Scanner;

// ==========================================
// CAMADA DE MODELO (Abstração e Regras)
// ==========================================

abstract class FuncionarioBase {
    private String nome;
    
    // Nível 1: Constantes (final e static)
    public static final double SALARIO_MINIMO = 1412.00;
    public static final double TAXA_BONUS_GERENTE = 0.20; // 20%

    // Nível 2: Atributo estático (Contador)
    private static int totalColaboradores = 0;

    public FuncionarioBase(String nome) {
        this.nome = nome;
        totalColaboradores++;
    }

    // Nível 2: Método Estático Utilitário (Cálculo de Imposto)
    public static double calcularImpostoSimples(double salario) {
        return salario * 0.11; // 11% fixo de retenção
    }

    public String getNome() { return nome; }
    public static int getTotalColaboradores() { return totalColaboradores; }

    // Nível 4: Garantir implementação com abstract
    public abstract double calcularSalarioLiquido();
}

// Nível 3: Hierarquia Abstrata (Classes Derivadas)
class GerenteSist extends FuncionarioBase {
    private double salarioBase;

    public GerenteSist(String nome, double salarioBase) {
        super(nome);
        // Validação: Não permite menor que o mínimo
        this.salarioBase = Math.max(salarioBase, SALARIO_MINIMO);
    }

    @Override
    public double calcularSalarioLiquido() {
        double bruto = salarioBase + (salarioBase * TAXA_BONUS_GERENTE);
        return bruto - calcularImpostoSimples(bruto);
    }
}

class VendedorSist extends FuncionarioBase {
    private double vendas;

    public VendedorSist(String nome, double vendas) {
        super(nome);
        this.vendas = vendas;
    }

    @Override
    public double calcularSalarioLiquido() {
        double comissao = vendas * 0.15;
        double bruto = Math.max(comissao, SALARIO_MINIMO);
        return bruto - calcularImpostoSimples(bruto);
    }
}

class EstagiarioSist extends FuncionarioBase {
    public EstagiarioSist(String nome) { super(nome); }

    @Override
    public double calcularSalarioLiquido() {
        // Estagiário recebe 80% do mínimo e não paga imposto no exemplo
        return SALARIO_MINIMO * 0.8;
    }
}

// ==========================================
// CAMADA DE EXECUÇÃO (Menu e Validações)
// ==========================================

public class SistemaRHPro {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        ArrayList<FuncionarioBase> equipe = new ArrayList<>();
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- SISTEMA DE GESTÃO PRO ---");
            System.out.println("1. Contratar Colaborador");
            System.out.println("2. Ver Painel de Pagamentos");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            
            // Validação de entrada numérica
            if (!leitor.hasNextInt()) {
                System.out.println("Erro: Digite apenas números.");
                leitor.next(); continue;
            }
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nCargos: 1-Gerente | 2-Vendedor | 3-Estagiário");
                    int cargo = leitor.nextInt();
                    leitor.nextLine();

                    System.out.print("Nome do profissional: ");
                    String nome = leitor.nextLine();

                    // Nível 4 e 5: Aplicação de Regras de Negócio
                    if (cargo == 1) {
                        System.out.print("Salário Base (Mín R$ " + FuncionarioBase.SALARIO_MINIMO + "): ");
                        double s = leitor.nextDouble();
                        equipe.add(new GerenteSist(nome, s));
                    } else if (cargo == 2) {
                        System.out.print("Volume de Vendas: ");
                        double v = leitor.nextDouble();
                        equipe.add(new VendedorSist(nome, v));
                    } else if (cargo == 3) {
                        equipe.add(new EstagiarioSist(nome));
                    }
                    System.out.println("Contratação efetuada!");
                    break;

                case 2:
                    System.out.println("\n--- FOLHA ANALÍTICA ---");
                    for (FuncionarioBase f : equipe) {
                        System.out.printf("Nome: %-12s | Líquido: R$ %.2f\n", 
                                          f.getNome(), f.calcularSalarioLiquido());
                    }
                    System.out.println("-----------------------");
                    System.out.println("Total de Ativos: " + FuncionarioBase.getTotalColaboradores());
                    break;
            }
        }
        System.out.println("Encerrando...");
        leitor.close();
    }
}