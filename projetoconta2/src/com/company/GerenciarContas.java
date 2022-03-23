package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class GerenciarContas {

    private List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GerenciarContas gc = new GerenciarContas();
        int opcao = 0;
        do{
            System.out.println("Menu Principal");
            System.out.println("1. Nova Conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Emitir Saldo");
            System.out.println("5. Listar Contas");
            System.out.println("5. Emitir Extrato");
            System.out.println("9. Sair");
            System.out.println("Digite sua opção: ");
            opcao = Integer.parseInt(input.nextLine());
            switch (opcao){
                case 1:
                    gc.execCadastrarNovaConta();
                    break;
                case 2:
                    gc.execDepositar();
                    break;
                case 3:
                    gc.execSacar();
                    break;
                case 4:
                    gc.execEmitirSaldo();
                    break;
                case 5:
                    gc.execListarContas();
                    break;
                case 6:
                    gc.execEmitirExtrato();
                    break;
                case 9:
                    System.out.println("Fim");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while(opcao!=9);
    }

    public void execCadastrarNovaConta(){
        Scanner input = new Scanner(System.in);
        Conta conta = new Conta();
        System.out.println("Cadastrando nova conta");
        System.out.println("Digite o id da Conta: ");
        conta.setIdConta(Integer.parseInt(input.nextLine()));
        System.out.println("Digite o nome do cliente: ");
        conta.setNomeCliente(input.nextLine());
        System.out.println("Digite o nome da agência: ");
        conta.setAgencia(input.nextLine());
        System.out.println("Conta cadatrada com sucesso");
        //muito importante, colocar a conta na lista criada
        contas.add(conta);
    }
    public void execDepositar(){
        //Importante: saber qual conta recebera o deposito
        Scanner input = new Scanner(System.in);
        int contaDeposito;
        System.out.println("Digite o id da conta a depositar: ");
        contaDeposito = Integer.parseInt(input.nextLine());
        for(Conta conta : contas){
            if(conta.getIdConta()==contaDeposito){
                System.out.println("Digite o valor do depósito: ");
                double valor = Double.parseDouble(input.nextLine());
                if(conta.depositar(valor)){
                    System.out.println("Depósito efetuado com sucesso");
                    Movimentacao movimentacao = new Movimentacao();
                    LocalDate dataAtual = LocalDate.now();
                    movimentacao.setData(dataAtual.toString());
                    movimentacao.setTipoMovimento('D');
                    movimentacao.setValor(valor);
                    conta.registrarMovimentacao(movimentacao);
                }else{
                    System.out.println("Valor inválido para depósito");
                }
                return;
            }
        }
        System.out.println("Conta inexistente");
    }
    public void execSacar(){
        //Importante: saber qual conta recebera o sque
        Scanner input = new Scanner(System.in);
        int contaSaque;
        System.out.println("Digite o id da conta a sacar: ");
        contaSaque = Integer.parseInt(input.nextLine());
        for(Conta conta : contas){
            if(conta.getIdConta()==contaSaque){
                System.out.println("Digite o valor do saque: ");
                double valor = Double.parseDouble(input.nextLine());
                if(conta.sacar(valor)){
                    System.out.println("Saque efetuado com sucesso");
                    Movimentacao movimentacao = new Movimentacao();
                    LocalDate dataAtual = LocalDate.now();
                    movimentacao.setData(dataAtual.toString());
                    movimentacao.setTipoMovimento('S');
                    movimentacao.setValor(valor);
                    conta.registrarMovimentacao(movimentacao);
                }else{
                    System.out.println("Sem saldo para o saque");
                }
                return;
            }
        }
        System.out.println("Conta inexistente");
    }
    public void execEmitirSaldo(){
        Scanner input = new Scanner(System.in);
        int contaSaldo;
        System.out.println("Digite o id da conta a ver o saldo: ");
        contaSaldo = Integer.parseInt(input.nextLine());
        for(Conta conta : contas){
            if(conta.getIdConta()==contaSaldo){
                System.out.println("Saldo atual: " + conta.getSaldo());
                return;
            }
        }
        System.out.println("Conta inexistente");
    }

    public void execListarContas(){
        System.out.println("Lista de Todas as contas");
        System.out.println("------------------------");
        for(Conta conta : contas){
            System.out.println(conta);
        }
    }

    public void execEmitirExtrato(){
        System.out.println("Extrato de Movimentações");
        System.out.println("------------------------");
        for(Conta conta : contas){
            System.out.println(conta.getMovimentacao());
            System.out.println("---------------------");
        }
    }
}