package src.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import src.model.*;
import src.utils.Estilo;

public class controllerLocacoes {
    public static void menuLocacoes(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<----------- MENU LOCAÇÕES ---------->>>\n" + Estilo.reset);
            System.out.println(Estilo.roxo + "\t 1. Locar Veículo");
            System.out.println("\t 2. Devolver Veículo");
            System.out.println("\t 3. Listar Locações Ativas");
            System.out.println("\t 0. <- Voltar" + Estilo.reset);
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<------------------------------------>>>\n" + Estilo.reset);
            System.out.print(Estilo.negrito + Estilo.amarelo + ">> Escolha uma das opções acima: " + Estilo.reset);

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Estilo.negrito + Estilo.vermelho + "Entrada inválida! Digite apenas números." + Estilo.reset);
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.print(Estilo.negrito + Estilo.amarelo + "\nCNH do cliente: " + Estilo.reset);
                        String cnh = scanner.nextLine();
                        System.out.println(Estilo.negrito + Estilo.azul + "\n=== Veículos Disponíveis ===" + Estilo.reset);
                        Veiculo.listarVeiculos(true);
                        System.out.print(Estilo.negrito + Estilo.amarelo + "Placa do veículo: " + Estilo.reset);
                        String placa = scanner.nextLine();
                        System.out.print(Estilo.negrito + Estilo.amarelo + "Data de retirada (AAAA-MM-DD): " + Estilo.reset);
                        LocalDate retirada = LocalDate.parse(scanner.nextLine());
                        System.out.print(Estilo.negrito + Estilo.amarelo + "Data de devolução (AAAA-MM-DD): " + Estilo.reset);
                        LocalDate devolucao = LocalDate.parse(scanner.nextLine());
                        System.out.print(Estilo.negrito + Estilo.amarelo + "Valor: " + Estilo.reset);
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        Locacao.cadastrarLocacao(cnh, placa, retirada, devolucao, valor);
                    } catch (DateTimeParseException e) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Formato de data inválido. Use o padrão AAAA-MM-DD." + Estilo.reset);
                    } catch (NumberFormatException e) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Valor inválido. Use apenas números para o valor." + Estilo.reset);
                    }
                    
                    break;
                case 2:
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nPlaca do veículo para devolução: " + Estilo.reset);
                    String placaDevolver = scanner.nextLine();
                    Locacao.devolverVeiculo(placaDevolver);
                    break;
                case 3:
                    System.out.println(Estilo.negrito + Estilo.azul + "\n=== Locações Ativas ===" + Estilo.reset);
                    Locacao.listarLocacoes(true);
                    break;
                case 0:
                    System.out.println(Estilo.negrito + Estilo.branco + "\nVoltando ao menu principal..." + Estilo.reset);
                    break;
                default:
                    System.out.println(Estilo.negrito + Estilo.vermelho + "Opção inválida!" + Estilo.reset);
            }
        } while (opcao != 0);
    }
}