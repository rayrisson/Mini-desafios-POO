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

class Contato{
    private String nome;
    private boolean isFavorited;
    ArrayList <Fone> fones;

    Contato(){
        nome = null;
        fones = new ArrayList<>(0);
        isFavorited = false;
    }
    Contato(String name, int qtdFones, String fones[]){
        nome = name;
        isFavorited = false;
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
    public boolean getIsFavorited(){
        return this.isFavorited;
    }
    public void setIsFavorited(boolean isFavorited){
        this.isFavorited = isFavorited;
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
}

public class Agenda3{
    Map <String, Contato> contatos;
    Map <String, Contato> favoritos;

    Agenda3(){
        contatos = new TreeMap<>();
        favoritos = new TreeMap<>();
    }

    public void addContact(String name, int qtdNum, String fones[]){
        for(Contato contato : contatos.values()){
            if(name.equals(contato.getNome())){
                contato.addFone(qtdNum, fones);
                return;
            } 
        }
        contatos.put(name, new Contato(name, qtdNum, fones));
    }

    public void rmContact(String name){
        for(Contato contato : contatos.values()){
            if(name.equals(contato.getNome())){
                contatos.remove(contato.getNome());
                return;
            }
        }
        System.out.println("Contato inexistente!");
    }

    public void rmFone(String name, int index){
        for(Contato contato : contatos.values()){
            if(name.equals(contato.getNome())){
                contato.rmFone(index);
                return;
            }
        }
        System.out.println("Contato inexistente!");
    }

    public void search(String pattern){
        for(Contato contato : contatos.values()){
            if(contato.toString().indexOf(pattern) != -1){
                System.out.println(contato);
            }
        }
    }

    public void favoritar(String name){
        for(Contato contato : contatos.values()){
            if(name.equals(contato.getNome())){
                if(contato.getIsFavorited()){
                    System.out.println("Contato já é um favorito");
                }else{
                    contato.setIsFavorited(true);
                    favoritos.put(name, contato);
                }
                return;
            }
        }
        System.out.println("Contato inexistente!");
    }

    public void desfavoritar(String name){
        for(Contato contato : contatos.values()){
            if(name.equals(contato.getNome())){
                if(contato.getIsFavorited()){
                    contato.setIsFavorited(false);
                    favoritos.remove(contato.getNome());
                }else{
                    System.out.println("Contato não é um favorito!");
                }
                return;
            }
        }
        System.out.println("Contato inexistente!");
    }

    public void show(){
        for(Contato contato : contatos.values()){
            if(contato.getIsFavorited()){
                System.out.println("@ " + contato);
            }else{
                System.out.println("- " + contato);
            }
        }
    }

    public void showFav(){
        for(Contato contato : favoritos.values()){
            System.out.println("@ " + contato);
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Agenda3 agenda = new Agenda3();
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
            }else if(commands[0].equals("agenda")){
                agenda.show();
            }else if(commands[0].equals("favoritos")){
                agenda.showFav();
            }else if(commands[0].equals("rmContato")){
                agenda.rmContact(commands[1]);
            }else if(commands[0].equals("rmFone")){
                agenda.rmFone(commands[1], Integer.parseInt(commands[2]));
            }else if(commands[0].equals("search")){
                agenda.search(commands[1]);
            }else if(commands[0].equals("fav")){
                agenda.favoritar(commands[1]);
            }else if(commands[0].equals("unfav")){
                agenda.desfavoritar(commands[1]);
            }else{
                System.out.println("Comando invalido");
            }
        }
        scanner.close();
    }
}

