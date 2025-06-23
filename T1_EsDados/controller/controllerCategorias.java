package src.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.model.Categoria;
import src.utils.Estilo;

public class controllerCategorias {

    public static void menuCategorias(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<----------- MENU CATEGORIAS ---------->>>\n" + Estilo.reset);
            System.out.println(Estilo.roxo + "\t 1. Inserir Categoria");
            System.out.println("\t 2. Excluir Categoria");
            System.out.println("\t 3. Editar Categoria");
            System.out.println("\t 4. Listar Categorias");
            System.out.println("\t 0. <- Voltar" + Estilo.reset);
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<-------------------------------------->>>\n" + Estilo.reset);
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
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nNome da categoria: " + Estilo.reset);
                    String nome = scanner.nextLine();
                    System.out.print(Estilo.negrito + Estilo.amarelo + "Identificador: " + Estilo.reset);
                    try {
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Categoria.cadastrarCategoria(nome, id);
                    } catch (InputMismatchException e) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Identificador inválido! Digite apenas números." + Estilo.reset);
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nIdentificador da categoria a remover: " + Estilo.reset);
                    try {
                        int idRemover = scanner.nextInt();
                        scanner.nextLine();
                        Categoria.removerCategoria(idRemover);
                    } catch (InputMismatchException e) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Identificador inválido! Digite apenas números." + Estilo.reset);
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nIdentificador da categoria a editar: " + Estilo.reset);
                    try {
                        int idEditar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print(Estilo.negrito + Estilo.amarelo + "Novo nome: " + Estilo.reset);
                        String novoNome = scanner.nextLine();
                        Categoria.editarCategoria(idEditar, novoNome);
                    } catch (InputMismatchException e) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Identificador inválido! Digite apenas números." + Estilo.reset);
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    System.out.println(Estilo.negrito + Estilo.azul + "\n=== Listando Categorias ===" + Estilo.reset);
                    Categoria.listarCategorias(true);
                    break;
                case 0:
                    System.out.println(Estilo.negrito + Estilo.branco + "\nVoltando ao menu principal..." + Estilo.reset);
                    break;
                default:
                    System.out.println(Estilo.negrito + Estilo.vermelho + "\nOpção inválida!" + Estilo.reset);
            }
        } while (opcao != 0);
    }
}