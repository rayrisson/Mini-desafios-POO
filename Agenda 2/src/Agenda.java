import java.util.*;

class Fone{
    private int id;
    private String operadora;
    private String numero;

    Fone(int id, String operadora, String numero){
        this.id = id;
        this.operadora = operadora;
        this.numero = numero;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "[" + id + ":" + operadora + ":" + numero + "]";
    }

}

class Contato implements Comparable<Contato>{
    private String nome;
    ArrayList <Fone> fones;

    Contato(){
        nome = null;
        fones = new ArrayList<>(0);
    }
    Contato(String name, int qtdFones, String fones[]){
        nome = name;
        this.fones = new ArrayList<>(qtdFones);
        for(int id = 0; id < qtdFones; id++){
            String elements[] = fones[id].split(":");
            this.fones.add(new Fone(id, elements[0], elements[1]));
        }
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public void addFone(int qtdFones, String fones[]){
        for(int i = 0; i < qtdFones; i++){
            String elements[] = fones[i].split(":");
            this.fones.add(new Fone(this.fones.get(this.fones.size() - 1).getId() + 1, elements[0], elements[1]));
        }
    }

    public void rmFone(int index){
        for(Fone fone : fones){
            if(fone.getId() == index){
                fones.remove(fone);
                return;
            }
        }
        System.out.println("Índice inválido!");
    }

    @Override
    public String toString(){
        String result = nome;
        for(int i = 0; i < fones.size(); i++){
            result += " " + fones.get(i);
        }
        return result;
    }

    public int compareTo(Contato other){
        return this.nome.compareTo(other.nome);
    }
}

public class Agenda{
    ArrayList <Contato> contatos;

    Agenda(){
        contatos = new ArrayList<>(0);
    }

    public void addContact(String name, int qtdNum, String fones[]){
        for(Contato contato : contatos){
            if(name.equals(contato.getNome())){
                contato.addFone(qtdNum, fones);
                return;
            } 
        }
        contatos.add(new Contato(name, qtdNum, fones));
        Collections.sort(contatos);
    }

    public void rmContact(String name){
        for(Contato contato : contatos){
            if(name.equals(contato.getNome())){
                contatos.remove(contato);
                return;
            }
        }
        System.out.println("Contato inexistente!");
    }

    public void rmFone(String name, int index){
        for(Contato contato : contatos){
            if(name.equals(contato.getNome())){
                contato.rmFone(index);
                return;
            }
        }
        System.out.println("Contato inexistente!");
    }

    public void search(String pattern){
        for(Contato ct : contatos){
            if(ct.toString().indexOf(pattern) != -1){
                System.out.println(ct);
            }
        }
    }

    public void show(){
        for(int i = 0; i < contatos.size(); i++){
            System.out.println(contatos.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        while(true){
            String line = scanner.nextLine();
            String commands[] = line.split(" ");
            if(commands[0].equals("exit")){
                break;
            }else if(commands[0].equals("add")){
                String fones[] = new String[commands.length - 2];
                for(int i = 2; i < commands.length; i++){
                    fones[i - 2] = commands[i];
                };
                agenda.addContact(commands[1], commands.length - 2, fones);
            }else if(commands[0].equals("show")){
                agenda.show();
            }else if(commands[0].equals("rmContato")){
                agenda.rmContact(commands[1]);
            }else if(commands[0].equals("rmFone")){
                agenda.rmFone(commands[1], Integer.parseInt(commands[2]));
            }else if(commands[0].equals("search")){
                agenda.search(commands[1]);
            }else{
                System.out.println("Comando invalido");
            }
        }
        scanner.close();
    }
}
