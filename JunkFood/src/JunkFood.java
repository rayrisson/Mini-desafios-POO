import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
    private int id;
    private String nome;
    private int quantidade;
    private double preco;
    Espiral(int id){
        this.id = id;
        nome = "empty";
        quantidade = 0;
        preco = 0;
    };

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getQtd(){
        return quantidade;
    }
    public void setQtd(int qtd){
        this.quantidade = qtd;
    }

    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
}

public class JunkFood {
    private double saldo;
    private double lucro;
    private int maxProdutos;
    private ArrayList<Espiral> espirais;

    JunkFood(){
        saldo = 0;
        lucro = 0;
        maxProdutos = 0;
        espirais = new ArrayList<>();
    }

    JunkFood(int espirais, int maxProdutos){
        saldo = 0;
        lucro = 0;
        this.maxProdutos = maxProdutos;
        this.espirais = new ArrayList<>(espirais);
        for(int i = 0; i < espirais; i++){
            Espiral espiral = new Espiral(i);
            this.espirais.add(i, espiral); 
        }
    }

    public void set(int indice, String nome, int qtd, double valor){
        if(qtd > maxProdutos || qtd < 0){
            System.out.println("FAIL: limite excedido");
        }else if(indice >= 0 && indice < espirais.size()){
            espirais.get(indice).setNome(nome);
            espirais.get(indice).setQtd(qtd);
            espirais.get(indice).setPreco(valor);
        }else{
            System.out.println("FAIL: índice não existe");
        }
    }

    public void buy(int indice){
        if(espirais.get(indice).getQtd() == 0){
            System.out.println("FAIL: espiral sem produtos");
        }else if(espirais.get(indice).getPreco() > saldo){
            System.out.println("FAIL: saldo insuficiente");
        }else if(indice < 0 || indice >= espirais.size()){
            System.out.println("FAIL: índice não existe");
        }else{
            int newQtd = espirais.get(indice).getQtd() - 1;
            espirais.get(indice).setQtd(newQtd);
            saldo -= espirais.get(indice).getPreco();
            lucro += espirais.get(indice).getPreco();
            System.out.println("Você comprou " + espirais.get(indice).getNome());
        }
    }

    public void delete(int indice){
        Espiral espiral = new Espiral(espirais.get(indice).getId());
        espirais.set(indice, espiral);
    }

    public void addMoney(double money){
        if(money > 0){
            saldo += money;
        }else{
            System.out.println("FAIL: valor inválido");
        }
    }

    public void show(){
        System.out.println("Saldo: " + saldo);
        for(int i = 0; i < espirais.size(); i++){
            System.out.println(espirais.get(i).getId() + " [ " + espirais.get(i).getNome() + " : " 
            + espirais.get(i).getQtd() + " U : " + espirais.get(i).getPreco() + " RS ]");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        JunkFood maquina = null;
        while(true){
            String line = scanner.nextLine();
            String comands[] = line.split(" ");

            if(comands[0].equals("init")){
                maquina = new JunkFood(Integer.parseInt(comands[1]), Integer.parseInt(comands[2]));
            }else if(comands[0].equals("end")){
                break;
            }else if(maquina == null){
                System.out.println("Máquina não inicializada");
            }else if(comands[0].equals("show")){
                maquina.show();
            }else if(comands[0].equals("set")){
                maquina.set(Integer.parseInt(comands[1]), comands[2], Integer.parseInt(comands[3]), Double.parseDouble(comands[4]));
            }else if(comands[0].equals("dinheiro")){
                maquina.addMoney(Integer.parseInt(comands[1]));
            }else if(comands[0].equals("troco")){
                System.out.println("Você recebeu " + maquina.saldo);
                maquina.saldo = 0;
            }else if(comands[0].equals("comprar")){
                maquina.buy(Integer.parseInt(comands[1]));
            }else if(comands[0].equals("limpar")){
                maquina.delete(Integer.parseInt(comands[1]));
            }else{
                System.out.println("Comando inválido");
            }
        }
        scanner.close();
    }
}
