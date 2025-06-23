package src.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.model.Categoria;
import src.model.Veiculo;
import src.utils.Estilo;

public class controllerVeiculos {

    public static void menuVeiculos(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<----------- MENU VEÍCULOS ---------->>>\n" + Estilo.reset);
            System.out.println(Estilo.roxo + "\t 1. Inserir Veículo");
            System.out.println("\t 2. Excluir Veículo");
            System.out.println("\t 3. Editar Veículo");
            System.out.println("\t 4. Listar Veículos (Início -> Fim)");
            System.out.println("\t 5. Listar Veículos (Fim -> Início)");
            System.out.println("\t 6. Filtrar Veículos");
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
                    // Inserir veículo
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nPlaca: " + Estilo.reset);
                    String placa = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Modelo: " + Estilo.reset);
                    String modelo = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Marca: " + Estilo.reset);
                    String marca = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Ano: " + Estilo.reset);
                    int ano = scanner.nextInt();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Potência: " + Estilo.reset);
                    int potencia = scanner.nextInt();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Número de lugares: " + Estilo.reset);
                    int lugares = scanner.nextInt();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "ID da Categoria: " + Estilo.reset);
                    int idCategoria = scanner.nextInt();
                    scanner.nextLine();
                    Categoria categoria = Categoria.buscarCategoria(idCategoria);

                    if (categoria != null) {
                        Veiculo.cadastrarVeiculo(placa, modelo, marca, ano, potencia, lugares, categoria);
                        System.out.println(Estilo.negrito + Estilo.verde + "Veículo cadastrado com sucesso!" + Estilo.reset);
                    } else {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Categoria não encontrada!" + Estilo.reset);
                    }
                    break;
                case 2:
                    // Excluir veículo
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nPlaca do veículo a remover: " + Estilo.reset);
                    String placaRemover = scanner.nextLine();
                    Veiculo.removerVeiculo(placaRemover);
                    break;
                case 3:
                    // Editar veículo
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nPlaca do veículo a editar: " + Estilo.reset);
                    String placaEditar = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Novo modelo: " + Estilo.reset);
                    String novoModelo = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Nova marca: " + Estilo.reset);
                    String novaMarca = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Novo ano: " + Estilo.reset);
                    int novoAno = scanner.nextInt();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Nova potência: " + Estilo.reset);
                    int novaPotencia = scanner.nextInt();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Novo número de lugares: " + Estilo.reset);
                    int novosLugares = scanner.nextInt();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Nova categoria (ID): " + Estilo.reset);
                    int novaCategoriaId = scanner.nextInt();
                    scanner.nextLine();
                    Categoria novaCategoria = Categoria.buscarCategoria(novaCategoriaId);

                    if (novaCategoria != null) {
                        Veiculo.editarVeiculo(placaEditar, novoModelo, novaMarca, novoAno, novaPotencia, novosLugares, novaCategoria);
                        System.out.println(Estilo.negrito + Estilo.verde + "Veículo editado com sucesso!" + Estilo.reset);
                    } else {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Categoria não encontrada!" + Estilo.reset);
                    }
                    break;
                case 4:
                    // Listar veículos (Início -> Fim)
                    System.out.println(Estilo.negrito + Estilo.azul + "\n=== Listando Veículos (Início -> Fim) ===" + Estilo.reset);
                    Veiculo.listarVeiculos(true);
                    break;
                case 5:
                    // Listar veículos (Fim -> Início)
                    System.out.println(Estilo.negrito + Estilo.azul + "\n=== Listando Veículos (Fim -> Início) ===" + Estilo.reset);
                    Veiculo.listarVeiculos(false);
                    break;
                case 6:
                    // Filtrar veículos
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nPotência mínima (ou pressione Enter para ignorar): " + Estilo.reset);
                    String potenciaInput = scanner.nextLine();
                    Integer potenciaMinima = potenciaInput.isEmpty() ? null : Integer.parseInt(potenciaInput);

                    System.out.print(Estilo.negrito + Estilo.amarelo + "Número de lugares (ou pressione Enter para ignorar): " + Estilo.reset);
                    String lugaresInput = scanner.nextLine();
                    Integer lugares1 = lugaresInput.isEmpty() ? null : Integer.parseInt(lugaresInput);

                    System.out.print(Estilo.negrito + Estilo.amarelo + "Nome da categoria (ou pressione Enter para ignorar): " + Estilo.reset);
                    String nomeCategoria = scanner.nextLine();
                    nomeCategoria = nomeCategoria.isEmpty() ? null : nomeCategoria;

                    Veiculo.filtrarVeiculos(potenciaMinima, lugares1, nomeCategoria);
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