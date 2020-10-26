import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

class Crianca{
    String nome;
    int idade;

    Crianca(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString(){
        return nome + ":" + idade;
    }
}
public class Parque {
    ArrayList <Crianca> esperando;
    ArrayList <Crianca> brincando;

    Parque(){
        esperando = new ArrayList<>();
        brincando = new ArrayList<>();
    }

    public void chegou(String nome, int idade){
        esperando.add(new Crianca(nome, idade));
    }

    public void brincar(){
        brincando.add(esperando.get(0));
        esperando.remove(0);
    }

    public void sair(){
        esperando.add(brincando.get(0));
        brincando.remove(0);
    }

    @Override
    public String toString(){
        String saida = "=> ";
        for(int i = esperando.size() - 1; i >= 0; i--){
            saida += esperando.get(i) + " ";
        }
        saida += " => [ ";
        for(int i = brincando.size() - 1; i >= 0; i--){
            saida += brincando.get(i) + " ";
        }
        saida += "]";
        return saida;
    }
    public static void main(String[] args) throws Exception {
        Parque parque = new Parque();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String line = scanner.nextLine();
            String cmd[] = line.split(" ");

            if(cmd[0].equals("exit")){
                break;
            }else if(cmd[0].equals("chegou")){
                parque.chegou(cmd[1], Integer.parseInt(cmd[2]));
            }else if(cmd[0].equals("entrar")){
                parque.brincar();
            }else if(cmd[0].equals("sair")){
                parque.sair();
            }else if(cmd[0].equals("show")){
                System.out.println(parque);
            }
        }
        scanner.close();
    }
}
