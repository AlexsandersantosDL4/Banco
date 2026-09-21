package model;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String titular;
    private String cpf;
    private String senha;
    private double saldo;
    private List<String> extrato;

    public Conta(String titular, String cpf) {
        this.titular = titular;
        this.cpf = cpf;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public Conta(String titular, String cpf, String senha) {
        this.titular = titular;
        this.cpf = cpf;
        this.senha = senha;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
        this.extrato.add("Conta criada com sucesso - Saldo inicial: R$ 0.00");
    }

    public static String dadosBancarios(String titular, String cpf) {
        return "Titular: " + titular + " | CPF: " + cpf;
    }

    public boolean validarSenha(String senhaDigitada) {
        return this.senha != null && this.senha.equals(senhaDigitada);
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            this.extrato.add(String.format("Deposito: +R$ %.2f", valor));
            System.out.printf("Deposito de R$ %.2f realizado com sucesso!\n", valor);
        } else {
            System.out.println("Erro: Valor invalido.");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            this.extrato.add(String.format("Saque: -R$ %.2f", valor));
            System.out.printf("Saque de R$ %.2f realizado com sucesso!\n", valor);
            return true;
        }
        System.out.println("Erro: Saldo insuficiente.");
        return false;
    }

    public boolean transferir(Conta contaDestino, double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            contaDestino.saldo += valor;
            this.extrato.add(String.format("Transferencia para %s: -R$ %.2f", contaDestino.getTitular(), valor));
            contaDestino.extrato.add(String.format("Transferencia recebida de %s: +R$ %.2f", this.titular, valor));
            System.out.printf("Transferencia de R$ %.2f para %s concluida!\n", valor, contaDestino.getTitular());
            return true;
        }
        System.out.println("Erro: Saldo insuficiente para transferencia.");
        return false;
    }

    public void exibirSaldo() {
        System.out.printf("Saldo atual: R$ %.2f\n", this.saldo);
    }

    public void exibirExtrato() {
        System.out.println("\n=== EXTRATO BANCARIO ===");
        for (String transacao : extrato) {
            System.out.println(transacao);
        }
        System.out.println("------------------------");
        System.out.printf("Saldo atual: R$ %.2f\n", this.saldo);
        System.out.println("========================");
    }

    @Override
    public String toString() {
        return "Conta:" +
                "\nTitular: " + titular +
                "\nCPF: " + cpf +
                "\nSaldo: R$ " + String.format("%.2f", saldo);
    }
}
