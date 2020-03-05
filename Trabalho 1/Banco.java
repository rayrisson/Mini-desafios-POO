import java.util.*;
import static java.lang.Math.abs;

public class Banco{
    int conta = 0;
    float saldo = 0;
    float divida = 0;

    void numConta(){
        Random random = new Random();
        this.conta = abs(random.nextInt() % 100000);
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
            this.divida -= valor;
            this.saldo -= valor;
        }else if(valor > this.saldo){
            System.out.println("Não possui esse saldo em conta!");
        }else{
            System.out.println("Valor inválido");
        }
    }

    void extrato(){
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
        banco.numConta();
        while(true){
            String line = input.nextLine();
            String bLine[] = line.split(" ");
            if(bLine[0].equals("deposito")){
                banco.deposito(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("saque")){
                banco.saque(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("emprestimo")){
                banco.emprestimo(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("pagar")){
                banco.pagar(Float.parseFloat(bLine[1]));
            }else if(bLine[0].equals("extrato")){
                banco.extrato();
            }else if(bLine[0].equals("sair")){
                break;
            }else{
                System.out.println("Operação inválida!");
            }
        }
        input.close();
    }
}