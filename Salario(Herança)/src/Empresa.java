import java.util.ArrayList;
import java.util.Scanner;

abstract class Funcionario{
    String nome;
    int maxDiarias;
    int qtdDiarias;
    float salario;

    Funcionario(String nome, int maxDiarias){
        this.nome = nome;
        this.maxDiarias = maxDiarias;
    }
    abstract void calcSalario();
    public void addDiaria(){
        if(qtdDiarias < maxDiarias){
            salario += 100;
            qtdDiarias++;
        }else{
            System.out.println("Limite de diárias atingidas");
        }
    }
    public void addBonus(float bonus){
        this.salario += bonus;
    }
}

class Professor extends Funcionario{
    char classe;

    Professor(String nome, char classe){
        super(nome, 2);
        this.classe = classe;
    }
    public void calcSalario(){
        char cl[] = {'A', 'B', 'C', 'D', 'E'};
        for(int i = 0; i < 5; i++){
            if(cl[i] == classe){
                salario = 3000 + (2000 * i);
                break;
            }
        }
    }

    @Override
    public String toString(){
        return "Prof " + nome + " : classe " + classe + " : salario " + salario;
    }
}

class STA extends Funcionario{
    int nivel;

    STA(String nome, int nivel){
        super(nome, 1);
        this.nivel = nivel;
    }
    public void calcSalario(){
        salario = 3000 + (300 * nivel);
    }

    @Override
    public String toString(){
        return "Sta " + nome + " : nivel " + nivel + " : salario " + salario;
    }
}

class Tercerizado extends Funcionario{
    int horasTrabalhadas;
    boolean insalubre;

    Tercerizado(String nome, int horasTrabalhadas, boolean insalubre){
        super(nome, 0);
        this.horasTrabalhadas = horasTrabalhadas;
        this.insalubre = insalubre;
    }
    public void calcSalario(){
        salario = insalubre ? horasTrabalhadas * 4 + 500 : horasTrabalhadas * 4;
    }

    @Override
    public void addDiaria(){
        System.out.println("Tercerizado não pode receber diária");
    }
    public String toString(){
        if(insalubre){
            return "Ter " + nome + " : " + horasTrabalhadas + "h : insalubre : salario " + salario;
        }else{
            return "Ter " + nome + " : " + horasTrabalhadas + "h : salario " + salario;
        }
    }
}

public class Empresa{
    ArrayList <Funcionario> funcionarios;
    
    Empresa(){
        funcionarios = new ArrayList<>();
    }
    public void addProf(String nome, char classe){
        funcionarios.add(new Professor(nome, classe));
        funcionarios.get(funcionarios.size() - 1).calcSalario();
    }
    public void addSTA(String nome, int nivel){
        funcionarios.add(new STA(nome, nivel));
        funcionarios.get(funcionarios.size() - 1).calcSalario();
    }
    public void addTer(String nome, int horasTrabalhadas, boolean insalubre){
        funcionarios.add(new Tercerizado(nome, horasTrabalhadas, insalubre));
        funcionarios.get(funcionarios.size() - 1).calcSalario();
    }
    public Funcionario findFunc(String nome){
        for(Funcionario fc : funcionarios){
            if(fc.nome.equals(nome)){
                return fc;
            }
        }
        return null;
    }
    public void rmFun(String nome){
        Funcionario aux = findFunc(nome);
        if(aux != null){
            funcionarios.remove(aux);
        }else{
            System.out.println("Funcionário " + nome + " não existe");
        }
    }
    public void diaria(String nome){
        Funcionario aux = findFunc(nome);
        if(aux != null){
            aux.addDiaria();
        }else{
            System.out.println("Funcionário " + nome + " não existe");
        }
    }
    public void bonus(float bonus){
        float divide = bonus / (funcionarios.size());
        for(Funcionario fc : funcionarios){
            fc.addBonus(divide);
        }
    }
    public String show(String nome){
        Funcionario aux = findFunc(nome);
        if(aux != null){
            if(aux instanceof Professor){
                return ((Professor) aux).toString();
            }else if(aux instanceof STA){
                return ((STA) aux).toString();
            }else{
                return ((Tercerizado) aux).toString();
            }
        }else{
            return "Funcionário " + nome + " não existe";
        }
    }
    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String line = scanner.nextLine();
            String commands[] = line.split(" ");

            if(commands[0].equals("addProf")){
                empresa.addProf(commands[1], commands[2].charAt(0));
            }else if(commands[0].equals("addSta")){
                empresa.addSTA(commands[1], Integer.parseInt(commands[2]));
            }else if(commands[0].equals("addTer")){
                empresa.addTer(commands[1], Integer.parseInt(commands[2]), commands[3].equals("sim"));
            }else if(commands[0].equals("rm")){
                empresa.rmFun(commands[1]);
            }else if(commands[0].equals("addDiaria")){
                empresa.diaria(commands[1]);
            }else if(commands[0].equals("setBonus")){
                empresa.bonus(Float.parseFloat(commands[1]));
            }else if(commands[0].equals("show")){
                System.out.println(empresa.show(commands[1]));
            }else if(commands[0].equals("exit")){
                break;
            }else{
                System.out.println("Comando inválido!");
            }
        }
        scanner.close();
    }
}
