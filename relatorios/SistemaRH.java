// --- CLASSE BASE (Nível 1: Encapsulamento) ---

import java.util.Scanner;

class Funcionario {
    private String nome;
    private double salario;

    // Nível 2: Sobrecarga de Construtores
    public Funcionario() {}

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        setSalario(salario); // Usa o setter para validar
    }

    // Getters e Setters com Validação (Nível 1)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        } else {
            System.out.println("Erro: Salário não pode ser negativo!");
        }
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome + " | Salário Base: R$" + salario);
    }
}

// --- CLASSE DERIVADA (Herança e Sobrescrita) ---
class Gerente extends Funcionario {
    private double bonus;

    public Gerente(String nome, double salario, double bonus) {
        super(nome, salario); // Nível 2: Uso do super
        this.bonus = bonus;
    }

    // Nível 5: Cálculo de salário com bônus
    @Override
    public double getSalario() {
        return super.getSalario() + bonus;
    }

    @Override
    public void exibirDados() {
        System.out.println("[GERENTE] Nome: " + getNome() + 
                           " | Salário Total (c/ bônus): R$" + getSalario());
    }
}

// --- CLASSE DERIVADA (Nível 3: Hierarquia) ---
class Estagiario extends Funcionario {
    public Estagiario(String nome, double salario) {
        super(nome, salario);
    }

    @Override
    public void exibirDados() {
        System.out.println("[ESTAGIÁRIO] Nome: " + getNome() + " | Bolsa: R$" + getSalario());
    }
}

// --- CLASSE PRINCIPAL (Nível 5: Menu Interativo) ---
public class SistemaRH {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        // Nível 4: Polimorfismo (Referência da classe base para objetos derivados)
        Funcionario func1 = new Gerente("Aline Rocha", 5000, 1500);
        Funcionario func2 = new Estagiario("Matheus Silva", 1200);

        System.out.println("--- Demonstração de Polimorfismo ---");
        func1.exibirDados();
        func2.exibirDados();

        // Menu Interativo
        int opcao = 0;
        while (opcao != 2) {
            System.out.println("\n1. Cadastrar Novo Gerente\n2. Sair");
            System.out.print("Escolha: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            if (opcao == 1) {
                System.out.print("Nome: ");
                String n = leitor.nextLine();
                System.out.print("Salário: ");
                double s = leitor.nextDouble();
                System.out.print("Bônus: ");
                double b = leitor.nextDouble();

                Gerente g = new Gerente(n, s, b);
                g.exibirDados();
            }
        }
        leitor.close();
    }
} 
    

