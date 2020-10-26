import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Operacao{
    int id;
    String nome;
    float valor;
    float saldo;

    Operacao(int id, String name, float value, float saldo){
        this.id = id;
        this.nome = name;
        this.valor = value;
        this.saldo = saldo;
    }
}
public class Conta{
    int numeroConta;
    int idOper;
    float saldo;
    ArrayList<Operacao> extrato;

    Conta(int NC){
        numeroConta = NC;
        idOper = 1;
        saldo = 0;
        extrato = new ArrayList<>();
        extrato.add(new Operacao(0, "abertura", 0, 0));
    }
    public void addExtrato(String name, float value){
        extrato.add(new Operacao(idOper, name, value, saldo));
        idOper++;
    }
    public void deposito(float value){
        if(value > 0){
            saldo += value;
            addExtrato("deposito", value);
        }else{
            System.out.println("Valor inválido");
        }
    }
    public void saque(float value){
        if(value <= saldo){
            saldo -= value;
            addExtrato("saque", -value);
        }else{
            System.out.println("Saldo indisponivel");
        }
    }
    public void tarifa(float value){
        if(value > 0){
            saldo -= value;
            addExtrato("tarifa", -value);
        }else{
            System.out.println("Tárifa inválida");
        }
    }
    public void extorno(int... args){
        for(int i : args){
            if(i < 0 || i >= extrato.size()){
                System.out.println("Índice " + i + " inválido");
            }else if("tarifa".equals(extrato.get(i).nome)){
                saldo -= extrato.get(i).valor;
                addExtrato("extorno", -extrato.get(i).valor);
                System.out.println("Extornado com exito");
            }else{
                System.out.println("Índice " + i + " não corresponde a uma tarifa");
            }
        }
    }
    public void showExtrato(int... args){
        if(args.length == 0){
            for(Operacao op : extrato){
                System.out.println(op.id + ": " + op.nome + ": " + op.valor + ": " + op.saldo);
            }
        }else{
            for(Operacao op : extrato.subList(extrato.size() - args[0] - 1, extrato.size() - 1)){
                System.out.println(op.id + ": " + op.nome + ": " + op.valor + ": " + op.saldo);
            }
        }
    }
    @Override
    public String toString(){
        return "Conta: " + numeroConta + " Saldo: " + saldo;
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Conta conta = null;
        while(true){
            String line = scanner.nextLine();
            String commands[] = line.split(" ");
            if(commands[0].equals("init")){
                conta = new Conta(Integer.parseInt(commands[1]));
            }else if(commands[0].equals("exit")){
                break;
            }else if(conta == null){
                System.out.println("Conta não aberta");
            }else if(commands[0].equals("deposito")){
                conta.deposito(Float.parseFloat(commands[1]));
            }else if(commands[0].equals("saque")){
                conta.saque(Float.parseFloat(commands[1]));
            }else if(commands[0].equals("tarifa")){
                conta.tarifa(Float.parseFloat(commands[1]));
            }else if(commands[0].equals("extorno")){
                String aux[] = Arrays.copyOfRange(commands, 1, commands.length);
                conta.extorno(Arrays.asList(aux).stream().mapToInt(Integer::parseInt).toArray());
            }else if(commands[0].equals("extrato")){
                conta.showExtrato();
            }else if(commands[0].equals("extratoN")){
                conta.showExtrato(Integer.parseInt(commands[1]));
            }else if(commands[0].equals("show")){
                System.out.println(conta);
            }else{
                System.out.println("Comando inválido");
            }
        }
        scanner.close();
    }
}
