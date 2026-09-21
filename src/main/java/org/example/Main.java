package org.example;

import model.Conta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CADASTRO DA CONTA BANCARIA ===");

        System.out.print("Digite o seu nome (Titular): ");
        String nome = sc.nextLine();

        String cpf = "";
        while (true) {
            System.out.print("Digite o seu CPF (11 digitos): ");
            cpf = sc.nextLine();
            if (cpf.length() == 11) {
                break;
            }
            System.out.println("Erro: O CPF deve obter 11 digitos. Tente novamente.");
        }

        String senha = "";
        while (true) {
            System.out.print("Digite a sua senha (4 digitos): ");
            senha = sc.nextLine();
            if (senha.length() == 4) {
                break;
            }
            System.out.println("Erro: A senha deve obter 4 digitos. Tente novamente.");
        }

        Conta minhaConta = new Conta(nome, cpf, senha);

        System.out.println("\n=== BEM VINDO AO BANCO DALAS! ===");

        List<Conta> destinatarios = new ArrayList<>();
        destinatarios.add(new Conta("luíz", "10987654321"));
        destinatarios.add(new Conta("serjao do pneu", "23456789012"));
        destinatarios.add(new Conta("João", "34567890123"));
        destinatarios.add(new Conta("Historiador", "45678901234"));

        int opcao = 0;
        int tentativasIncorretas = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Ver Dados da Conta");
            System.out.println("2 - Ver Saldo");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Ver Extrato");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = sc.nextInt();

                if (opcao >= 3 && opcao <= 6) {
                    sc.nextLine();
                    System.out.print("Confirme sua senha (4 digitos) para continuar: ");
                    String senhaDigitada = sc.nextLine();

                    if (!minhaConta.validarSenha(senhaDigitada)) {
                        tentativasIncorretas++;
                        System.out.println("Senha incorreta! Tentativas restantes: " + (3 - tentativasIncorretas));

                        if (tentativasIncorretas >= 3) {
                            System.out.println("CONTA BLOQUEADA POR SEGURANCA! Sistema encerrado.");
                            break;
                        }
                        continue;
                    } else {
                        tentativasIncorretas = 0;
                    }
                }

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- DADOS DA CONTA ---");
                        System.out.println(minhaConta);
                        break;

                    case 2:
                        minhaConta.exibirSaldo();
                        break;

                    case 3:
                        System.out.print("Qual valor voce deseja depositar? R$ ");
                        double valorDeposito = sc.nextDouble();
                        minhaConta.depositar(valorDeposito);
                        break;

                    case 4:
                        System.out.print("Qual valor deseja sacar? R$ ");
                        double valorSaque = sc.nextDouble();
                        minhaConta.sacar(valorSaque);
                        break;

                    case 5:
                        System.out.print("Qual valor deseja transferir? R$ ");
                        double valorTransf = sc.nextDouble();

                        System.out.println("\n--- ESCOLHA O DESTINATARIO ---");
                        for (int i = 0; i < destinatarios.size(); i++) {
                            System.out.println((i + 1) + " - " + destinatarios.get(i).getTitular());
                        }
                        System.out.print("Digite o numero do destinatario: ");
                        int escolha = sc.nextInt();

                        if (escolha >= 1 && escolha <= destinatarios.size()) {
                            Conta contaEscolhida = destinatarios.get(escolha - 1);
                            minhaConta.transferir(contaEscolhida, valorTransf);
                        } else {
                            System.out.println("Destinatario invalido! Transferencia cancelada.");
                        }
                        break;

                    case 6:
                        minhaConta.exibirExtrato();
                        break;

                    case 7:
                        System.out.println("\"Obrigado, " + minhaConta.getTitular() + "  por escolher o Banco Dalas! Foi um prazer atendê-lo. Volte sempre!");
                        break;

                    default:
                        System.out.println("Opcao invalida! Digite um numero de 1 a 7.");
                }

            } catch (Exception erro) {
                System.out.println("Erro: Por favor, digite APENAS NUMEROS no menu!");
                sc.nextLine();
                opcao = 0;
            }

        } while (opcao != 7);

        sc.close();
    }
}
