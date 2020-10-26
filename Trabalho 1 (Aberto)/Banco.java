import java.util.*;
import static java.lang.Math.abs;


class Pessoa{
    String nome;
    String cpf;
    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
}

public class Banco{
    Pessoa pessoa;
    int conta = 0;
    float saldo = 0;
    float divida = 0;

    void abrirConta(Pessoa pessoa){
        this.pessoa = pessoa;
        Random random = new Random();
        this.conta = abs(random.nextInt() % 100000);
        System.out.println("Conta aberta!");
    }
    
    void deposito(float valor){
        if(valor > 0){
            this.saldo += valor;
        }else{
            System.out.println("Impossivel depositar este valor");
        }
    }

    void saque(float valor){
        if(valor <= this.saldo && valor > 0){
            this.saldo -= valor;
        }else if(valor > this.saldo){
            System.out.println("Não possui esse saldo em conta!");
        }else{
            System.out.println("Valor inválido!");
        }
    }

    void emprestimo(float valor){
        if(valor > 0){
            this.divida += valor;
            this.saldo += valor;
        }else{
            System.out.println("Valor inválido!");
        }
    }

    void pagar(float valor){
        if(valor <= this.saldo && valor > 0){
            this.saldo -= this.divida;
            if(valor > this.divida){
                this.divida = 0;
            }else{
                this.divida -= valor;
            }
        }else if(valor > this.saldo){
            System.out.println("Não possui esse saldo em conta!");
        }else{
            System.out.println("Valor inválido");
        }
    }

    void extrato(){
        System.out.println("Dono da conta: " + this.pessoa.nome);
        System.out.println("CPF: " + this.pessoa.cpf);
        System.out.println("O número de sua conta é: " + this.conta);
        System.out.println("O seu saldo é: " + this.saldo);
        if(this.divida > 0){
            System.out.println("Possui uma dívida de: " + this.divida);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner input = new Scanner(System.in);
        while(true){
            String line = input.nextLine();
            String bLine[] = line.split(" ");
            if(bLine[0].equals("abrir") && banco.pessoa == null){
                System.out.println("Digite o seu nome:");
                String nome = input.nextLine();
                System.out.println("Digite o seu CPF:");
                String cpf = input.nextLine();
                Pessoa cliente = new Pessoa(nome, cpf);
                banco.abrirConta(cliente);
            }else if(bLine[0].equals("deposito") && banco.pessoa != null){
                banco.deposito(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("saque") && banco.pessoa != null){
                banco.saque(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("emprestimo") && banco.pessoa != null){
                banco.emprestimo(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("pagar") && banco.pessoa != null){
                banco.pagar(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("extrato") && banco.pessoa != null){
                banco.extrato();
            }else if(bLine[0].equals("sair")){
                break;
            }else if(banco.pessoa == null){
                System.out.println("A conta não foi aberta");
            }else{
                System.out.println("Operação inválida!");
            }
        }
        input.close();
    }
}