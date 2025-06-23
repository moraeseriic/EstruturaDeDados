package src.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import src.model.Cliente;
import src.utils.Estilo;

public class controllerClientes {

    public static String formatarCPF(String cpf) {
        try {
            if (cpf == null) return null;
            cpf = cpf.replaceAll("\\D", ""); 
            if (cpf.length() != 11) {
                throw new IllegalArgumentException("CPF inválido! Deve conter 11 dígitos.");
            }
            return cpf.substring(0, 3) + "." +
                   cpf.substring(3, 6) + "." +
                   cpf.substring(6, 9) + "-" +
                   cpf.substring(9);
        } catch (Exception e) {
            System.out.println(Estilo.negrito + Estilo.vermelho + "Erro ao formatar CPF: " + e.getMessage() + Estilo.reset);
            return null;
        }
    }

    public static String formatarCNH(String cnh) {
        try {
            if (cnh == null) return null;
            cnh = cnh.replaceAll("\\D", "");
            if (cnh.length() < 7 || cnh.length() > 11) {
                throw new IllegalArgumentException("CNH inválida! Deve conter entre 7 e 11 dígitos.");
            }
            return cnh;
        } catch (Exception e) {
            System.out.println(Estilo.negrito + Estilo.vermelho + "Erro ao formatar CNH: " + e.getMessage() + Estilo.reset);
            return null;
        }
    }

    public static String formatarTelefone(String telefone) {
        try {
            if (telefone == null) return null;
            telefone = telefone.replaceAll("\\D", "");
            if (telefone.length() != 10 && telefone.length() != 11) {
                throw new IllegalArgumentException("Telefone inválido! Deve conter 10 ou 11 dígitos.");
            }
            return telefone;
        } catch (Exception e) {
            System.out.println(Estilo.negrito + Estilo.vermelho + "Erro ao formatar telefone: " + e.getMessage() + Estilo.reset);
            return null;
        }
    }

    public static void menuClientes(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<----------- MENU CLIENTES ---------->>>\n" + Estilo.reset);
            System.out.println(Estilo.roxo + "\t 1. Inserir Cliente");
            System.out.println("\t 2. Excluir Cliente");
            System.out.println("\t 3. Editar Cliente");
            System.out.println("\t 4. Listar Clientes (Início -> Fim)");
            System.out.println("\t 5. Listar Clientes (Fim -> Início)");
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
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nNome: " + Estilo.reset);
                    String nome = scanner.nextLine();

                    System.out.print(Estilo.negrito + Estilo.amarelo + "CNH: " + Estilo.reset);
                    String cnhEntrada = scanner.nextLine();
                    String cnh = formatarCNH(cnhEntrada);
                    if (cnh == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "CNH inválida. Cancelando cadastro." + Estilo.reset);
                        break;
                    }

                    System.out.print(Estilo.negrito + Estilo.amarelo + "Telefone: " + Estilo.reset);
                    String telefoneEntrada = scanner.nextLine();
                    String telefone = formatarTelefone(telefoneEntrada);
                    if (telefone == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Telefone inválido. Cancelando cadastro." + Estilo.reset);
                        break;
                    }

                    System.out.print(Estilo.negrito + Estilo.amarelo + "CPF (somente números): " + Estilo.reset);
                    String cpfEntrada = scanner.nextLine();
                    String cpf = formatarCPF(cpfEntrada);
                    if (cpf == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "CPF inválido. Cancelando cadastro." + Estilo.reset);
                        break;
                    }

                    Cliente.cadastrarCliente(nome, cnh, telefone, cpf);
                    System.out.println(Estilo.negrito + Estilo.verde + "Cliente cadastrado com sucesso!" + Estilo.reset);
                    break;
                case 2:
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nCPF do cliente a remover: " + Estilo.reset);
                    String cpfRemoverEntrada = scanner.nextLine();
                    String cpfRemover = formatarCPF(cpfRemoverEntrada);
                    if (cpfRemover == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "CPF inválido. Cancelando remoção." + Estilo.reset);
                        break;
                    }
                    Cliente.removerCliente(cpfRemover);
                    break;
                case 3:
                    System.out.print(Estilo.negrito + Estilo.amarelo + "\nCPF do cliente a editar: " + Estilo.reset);
                    String cpfEditarEntrada = scanner.nextLine();
                    String cpfEditar = formatarCPF(cpfEditarEntrada);
                    if (cpfEditar == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "CPF inválido. Cancelando edição." + Estilo.reset);
                        break;
                    }

                    System.out.print(Estilo.negrito + Estilo.amarelo + "Novo nome: " + Estilo.reset);
                    String nomeNovo = scanner.nextLine();

                    System.out.print(Estilo.negrito + Estilo.amarelo + "Nova CNH: " + Estilo.reset);
                    String cnhNovaEntrada = scanner.nextLine();
                    String cnhNova = formatarCNH(cnhNovaEntrada);
                    if (cnhNova == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "CNH inválida. Cancelando edição." + Estilo.reset);
                        break;
                    }

                    System.out.print(Estilo.negrito + Estilo.amarelo + "Novo telefone: " + Estilo.reset);
                    String telefoneNovoEntrada = scanner.nextLine();
                    String telefoneNovo = formatarTelefone(telefoneNovoEntrada);
                    if (telefoneNovo == null) {
                        System.out.println(Estilo.negrito + Estilo.vermelho + "Telefone inválido. Cancelando edição." + Estilo.reset);
                        break;
                    }
                    Cliente.editarCliente(cpfEditar, nomeNovo, cnhNova, telefoneNovo);
                    System.out.println(Estilo.negrito + Estilo.verde + "Cliente editado com sucesso!" + Estilo.reset);
                    break;
                case 4:
                    System.out.println(Estilo.negrito + Estilo.azul + "\n=== Listando Clientes (Início -> Fim) ===" + Estilo.reset);
                    Cliente.listarClientes(true);
                    break;
                case 5:
                    System.out.println(Estilo.negrito + Estilo.azul + "\n=== Listando Clientes (Fim -> Início) ===" + Estilo.reset);
                    Cliente.listarClientes(false);
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