package TrabalhoII;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe principal que gerencia a simulação do supermercado
 * @author emilly dias e @author eric moraes
 */
public class Supermercado {
    private Lista<Caixa> caixas;          // Lista de caixas do supermercado
    private int proximoNumeroCliente;     // Contador para número sequencial de clientes
    private Random random;               // Gerador de números aleatórios
    private int tempoSimulacao;          // Contador de passos de tempo
    private final int PROBABILIDADE_CHEGADA = 50; // 50% de chance de chegar cliente

    public Supermercado(int numeroCaixas) {
        this.caixas = new Lista<>(); // Implemente seu TAD Lista
        this.proximoNumeroCliente = 1;
        this.random = new Random();
        this.tempoSimulacao = 0;
        
        // Cria os caixas
        for (int i = 1; i <= numeroCaixas; i++) {
            caixas.adicionar(new Caixa(i));
        }
    }

        public void executarPassoSimulacao() {
        tempoSimulacao++;
        
        // 1. Verifica chegada de novo cliente
        if (random.nextInt(100) < PROBABILIDADE_CHEGADA) {
            adicionarNovoCliente();
        }
        
        // 2. Atualiza atendimento em todos os caixas
        for (int i = 0; i < caixas.tamanho(); i++) {
            Caixa caixa = caixas.obter(i);
            caixa.atualizarAtendimento();
        }
        
        // 3. Exibe estado atual
        exibirEstado();
    }

    // Adiciona um novo cliente ao caixa com menor fila 
    private void adicionarNovoCliente() {
        // Gera tempo de atendimento entre 5 e 15 minutos
        int tempoAtendimento = 5 + random.nextInt(11);
        Cliente novoCliente = new Cliente(proximoNumeroCliente++, tempoAtendimento);
        
        // Encontra caixa com menor fila
        Caixa caixaMenorFila = encontrarCaixaMenorFila();
        
        // Adiciona cliente ao caixa
        caixaMenorFila.adicionarCliente(novoCliente);
        
        System.out.println(
            CoresTerminal.sucesso("Novo cliente #" + novoCliente.getidCliente()) +
            CoresTerminal.dado(" (" + tempoAtendimento + " min)") +
            CoresTerminal.alerta(" - Caixa " + caixaMenorFila.getidCaixa())
        );
    }

    // Encontra o caixa com menor número de clientes na fila
    private Caixa encontrarCaixaMenorFila() {
        Caixa menor = caixas.obter(0);
        
        for (int i = 1; i < caixas.tamanho(); i++) {
            Caixa atual = caixas.obter(i);
            if (atual.getTamanhoFila() < menor.getTamanhoFila()) {
                menor = atual;
            }
        }
        
        return menor;
    }

    public void exibirEstatisticas() {
        System.out.println(CoresTerminal.titulo("\n=== ESTATÍSTICAS ==="));
        System.out.println(CoresTerminal.dado("Tempo total de simulação: ") + CoresTerminal.sucesso(tempoSimulacao + " passos"));
        System.out.println(CoresTerminal.dado("Total de clientes gerados: ") + CoresTerminal.sucesso(String.valueOf(proximoNumeroCliente - 1)));
        
        int totalAtendidos = 0;
        for (int i = 0; i < caixas.tamanho(); i++) {
            Caixa caixa = caixas.obter(i);
            totalAtendidos += caixa.getClientesAtendidos();
            System.out.println(
                CoresTerminal.dado("Caixa ") + CoresTerminal.titulo(String.valueOf(caixa.getidCaixa())) + ": " +
                CoresTerminal.sucesso(String.valueOf(caixa.getClientesAtendidos())) + CoresTerminal.dado(" clientes atendidos") + " | " +
                CoresTerminal.alerta("Fila atual: ") + CoresTerminal.dado(String.valueOf(caixa.getTamanhoFila()))
            );
        }
        
        System.out.println(CoresTerminal.dado("Total de clientes atendidos: ") + CoresTerminal.sucesso(String.valueOf(totalAtendidos)));
        System.out.println(CoresTerminal.dado("Clientes ainda na fila: ") + CoresTerminal.alerta(String.valueOf(proximoNumeroCliente - 1 - totalAtendidos)));
    }

    // Exibe o estado atual de todos os caixas
    public void exibirEstado() {
        System.out.println(CoresTerminal.titulo("\n--- Tempo: " + tempoSimulacao + " -------------------"));
        for (int i = 0; i < caixas.tamanho(); i++) {
            System.out.println(formatarCaixa(caixas.obter(i)));
        }
        System.out.println(CoresTerminal.titulo("------------------------------------------------"));
    }

    private String formatarCaixa(Caixa caixa) {
        StringBuilder sb = new StringBuilder();
        sb.append(CoresTerminal.NEGRITO).append(CoresTerminal.titulo("Caixa ")).append(CoresTerminal.titulo(String.valueOf(caixa.getidCaixa()))).append(": ");
        
        if (caixa.estaLivre()) {
            sb.append(CoresTerminal.sucesso("[LIVRE] "));
        } else {
            sb.append(CoresTerminal.alerta("[Atendendo: "))
            .append(CoresTerminal.dado(String.valueOf(caixa.getClienteAtual())))
            .append(CoresTerminal.alerta(" (" + caixa.getTempoRestanteAtendimento() + " min restantes)] "));
        }
        
        sb.append(CoresTerminal.dado("Fila: ")).append(CoresTerminal.dado(caixa.getFila().toString()));
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Tratamento para número de caixas
        int numeroCaixas = 0;
        while (numeroCaixas <= 0) {
            try {
                System.out.print(CoresTerminal.dado("Digite o número de caixas (maior que 0): "));
                numeroCaixas = scanner.nextInt();
                scanner.nextLine(); 
                
                if (numeroCaixas <= 0) {
                    System.out.println(CoresTerminal.erro("Valor inválido! Digite um número positivo."));
                }
            } catch (InputMismatchException e) {
                System.out.println(CoresTerminal.erro("Entrada inválida! Digite apenas números."));
                scanner.nextLine(); 
            }
        }
        
        Supermercado supermercado = new Supermercado(numeroCaixas);
        
        System.out.println(CoresTerminal.borda("Simulação iniciada. Comandos disponíveis:"));
        System.out.println(CoresTerminal.sucesso("- ENTER: Avança um passo de tempo"));
        System.out.println(CoresTerminal.erro("- S: Encerra a simulação"));
        System.out.println(CoresTerminal.alerta("- P: Pausa/Continua a simulação"));
        System.out.println(CoresTerminal.dado("- E: Exibe estatísticas atuais"));
        boolean executando = true;
        boolean pausado = false;
        
        while (executando) {
            if (!pausado) {
                System.out.print(CoresTerminal.dado("\nPressione ENTER para continuar..."));
            }
            
            String input = scanner.nextLine().trim().toLowerCase();
            
            switch (input) {
                case "":
                    if (!pausado) {
                        supermercado.executarPassoSimulacao();
                    }
                    break;
                    
                case "s":
                    executando = false;
                    System.out.println(CoresTerminal.erro("Encerrando simulação..."));
                    break;
                    
                case "p":
                    pausado = !pausado;
                    System.out.println(CoresTerminal.alerta("Simulação " + (pausado ? "pausada" : "retomada")));
                    break;
                    
                case "e":
                    supermercado.exibirEstatisticas();
                    break;
                    
                default:
                    if (!pausado) {
                        System.out.println(CoresTerminal.erro("Comando inválido! Use: ENTER, S, P ou E"));
                    }
            }
        }
        
        // Exibe estatísticas finais
        supermercado.exibirEstatisticas();
        scanner.close();
    }
}
