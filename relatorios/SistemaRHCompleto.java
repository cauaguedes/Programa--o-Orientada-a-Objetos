import java.util.ArrayList;
import java.util.Scanner;

// ==========================================
// CLASSES DE MODELO
// ==========================================

abstract class FuncionarioApp {
    private String nome;
    protected double salarioBase;

    public FuncionarioApp(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public String getNome() { return nome; }
    public abstract double calcularSalario();
}

class GerenteApp extends FuncionarioApp {
    public GerenteApp(String nome, double salarioBase) {
        super(nome, salarioBase);
    }
    @Override
    public double calcularSalario() {
        return salarioBase + 2000.0;
    }
}

class DesenvolvedorApp extends FuncionarioApp {
    public DesenvolvedorApp(String nome, double salarioBase) {
        super(nome, salarioBase);
    }
    @Override
    public double calcularSalario() {
        return salarioBase;
    }
}

class EstagiarioApp extends FuncionarioApp {
    public EstagiarioApp(String nome, double salarioBase) {
        super(nome, salarioBase);
    }
    @Override
    public double calcularSalario() {
        return salarioBase * 0.8;
    }
}

// ==========================================
// CLASSE PRINCIPAL
// ==========================================

public class SistemaRHCompleto {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        ArrayList<FuncionarioApp> funcionarios = new ArrayList<>();
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- GESTAO RH ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Relatorio (Folha de Pagamento)");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            
            if (!leitor.hasNextInt()) {
                leitor.next(); // Limpa erro
                continue;
            }
            opcao = leitor.nextInt();
            leitor.nextLine();

            if (opcao == 1) {
                System.out.println("1-Gerente, 2-Dev, 3-Estagiario");
                int tipo = leitor.nextInt();
                leitor.nextLine();

                System.out.print("Nome: ");
                String nome = leitor.nextLine();
                System.out.print("Salario Base: ");
                double base = leitor.nextDouble();

                // Criando objetos com os novos nomes de classe
                if (tipo == 1) funcionarios.add(new GerenteApp(nome, base));
                else if (tipo == 2) funcionarios.add(new DesenvolvedorApp(nome, base));
                else if (tipo == 3) funcionarios.add(new EstagiarioApp(nome, base));
                
                System.out.println("Cadastrado!");

            } else if (opcao == 2) {
                System.out.println("\n--- FOLHA DE PAGAMENTO ---");
                if (funcionarios.isEmpty()) {
                    System.out.println("Lista vazia.");
                } else {
                    for (FuncionarioApp f : funcionarios) {
                        // POLIMORFISMO: chama o calcularSalario correto
                        System.out.printf("Nome: %-10s | Total: R$ %.2f\n", 
                                          f.getNome(), f.calcularSalario());
                    }
                }
            }
        }
        leitor.close();
    }
}