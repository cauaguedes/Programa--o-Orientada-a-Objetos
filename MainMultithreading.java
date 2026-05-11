import java.util.ArrayList;
import java.util.List;

/**
 * Nível 1: Implementação usando Runnable
 */
class ContadorTarefa implements Runnable {
    private final int inicio;
    private final int fim;
    private final int passo;
    private final int delay;

    public ContadorTarefa(int inicio, int fim, int passo, int delay) {
        this.inicio = inicio;
        this.fim = fim;
        this.passo = passo;
        this.delay = delay;
    }

    @Override
    public void run() {
        // Nível 2: Nomeação capturada via Thread.currentThread()
        String nomeThread = Thread.currentThread().getName();
        
        for (int i = inicio; (passo > 0 ? i <= fim : i >= fim); i += passo) {
            System.out.println("[" + nomeThread + "] Valor: " + i);
            try {
                // Nível 3: Controle de tempo (delay variável)
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println(nomeThread + " foi interrompida.");
            }
        }
        System.out.println(">>> " + nomeThread + " finalizou!");
    }
}

/**
 * Nível 5: Objeto para demonstrar Condição de Corrida
 */
class RecursoCompartilhado {
    public int contador = 0;

    // Método não sincronizado de propósito para observar o erro
    public void incrementar() {
        contador++; 
    }
}

public class MainMultithreading {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== INICIANDO EXECUÇÃO CONCORRENTE ===\n");

        // --- PARTE 1: NÍVEIS 1 A 4 (EXECUÇÃO DE TAREFAS) ---
        
        // Criando as instâncias de Runnable
        ContadorTarefa tarefaCrescente = new ContadorTarefa(1, 10, 1, 400);
        ContadorTarefa tarefaDecrescente = new ContadorTarefa(10, 1, -1, 400);
        ContadorTarefa tarefaRapida = new ContadorTarefa(100, 110, 1, 200);

        // Criando as Threads
        Thread t1 = new Thread(tarefaCrescente);
        Thread t2 = new Thread(tarefaDecrescente);
        Thread t3 = new Thread(tarefaRapida);

        // Nível 2: Nomeação
        t1.setName("Thread A (Crescente)");
        t2.setName("Thread B (Decrescente)");
        t3.setName("Thread C (Rápida)");

        // Nível 4: Start nas múltiplas threads
        t1.start();
        t2.start();
        t3.start();

        // Aguarda as threads de contagem terminarem antes de iniciar o desafio extra
        t1.join();
        t2.join();
        t3.join();

        // --- PARTE 2: NÍVEL 5 (DESAFIO DE SINCRONIZAÇÃO) ---
        
        System.out.println("\n=== DESAFIO: CONDIÇÃO DE CORRIDA ===");
        RecursoCompartilhado recurso = new RecursoCompartilhado();
        int totalThreads = 20;
        int incrementosPorThread = 1000;
        
        List<Thread> threadsCorrida = new ArrayList<>();

        Runnable tarefaSoma = () -> {
            for (int i = 0; i < incrementosPorThread; i++) {
                recurso.incrementar();
            }
        };

        // Dispara 20 threads somando ao mesmo tempo
        for (int i = 0; i < totalThreads; i++) {
            Thread t = new Thread(tarefaSoma);
            threadsCorrida.add(t);
            t.start();
        }

        // Aguarda todas as threads de soma terminarem
        for (Thread t : threadsCorrida) t.join();

        int esperado = totalThreads * incrementosPorThread;
        System.out.println("Resultado Esperado: " + esperado);
        System.out.println("Resultado Real:     " + recurso.contador);
        
        if (recurso.contador < esperado) {
            System.out.println("STATUS: Inconsistência detectada! (As threads se atropelaram)");
        } else {
            System.out.println("STATUS: Resultado consistente (Sorte do escalonador)");
        }
    }
}