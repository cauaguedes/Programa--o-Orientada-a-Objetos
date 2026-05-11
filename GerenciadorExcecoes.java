import java.util.Scanner;

// 1. A classe da exceção DEVE estender Exception para ter o método getMessage()
// Coloque-a fora da classe principal ou como 'static' dentro dela.
class IdadeInvalidaException extends Exception {
    public IdadeInvalidaException(String mensagem) {
        super(mensagem); // Passa a mensagem para a classe pai (Exception)
    }
}

public class GerenciadorExcecoes {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        boolean dadosValidos = false;

        while (!dadosValidos) {
            try {
                System.out.println("\n--- Validação de Dados ---");
                
                System.out.print("Digite seu nome: ");
                System.out.print("Digite sua idade: ");
                // Lendo como String e convertendo para evitar erros chatos do Scanner
                int idade = Integer.parseInt(leitor.nextLine());
                
                validarIdade(idade); 

                System.out.print("Número para dividir 100: ");
                int divisor = Integer.parseInt(leitor.nextLine());
                
                int resultado = 100 / divisor;
                
                System.out.println("Sucesso! Resultado: " + resultado);
                dadosValidos = true; 

            } catch (IdadeInvalidaException e) {
                // Agora o getMessage() vai funcionar porque herdamos de Exception
                System.err.println("Erro: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.err.println("Erro: Divisão por zero não permitida!");
            } catch (NumberFormatException e) {
                System.err.println("Erro: Digite um número válido!");
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getClass().getSimpleName());
            } finally {
                System.out.println("Tentativa finalizada.");
            }
        }
        leitor.close();
    }

    // O uso do 'throws' avisa o Java que este método é perigoso
    public static void validarIdade(int idade) throws IdadeInvalidaException {
        if (idade < 0 || idade > 150) {
            throw new IdadeInvalidaException("A idade " + idade + " é inválida!");
        }
    }
}