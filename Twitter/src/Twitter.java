import java.util.*;

class Tweet{
    int id;
    String username, tweet;
    ArrayList<String> likes;

    Tweet(int id, String username, String tweet){
        this.id = id;
        this.username = username;
        this.tweet = tweet;
        this.likes = new ArrayList<>();
    }

    public void addLike(String username){
        for(String usr : likes){
            if(usr.equals(username)){
                System.out.println("Você ja deixou o seu like!");
                return;
            }
        }
        likes.add(username);
    }

    @Override
    public String toString(){
        String saida = id + ":" + username + "( " + tweet + " )";
        if(!likes.isEmpty()){
            saida += "[ ";
            for(String lk : likes){
                saida += lk + " ";
            }
            saida += "]";
        }
        return saida;
    }
}

class User{
    String username;
    int idNaoLidas;
    ArrayList<User> seguidores, seguindo;
    ArrayList<Tweet> timeline, myTweets;

    User(String username){
        this.username = username;
        this.idNaoLidas = -1;
        this.seguidores = new ArrayList<>();
        this.seguindo = new ArrayList<>();
        this.timeline = new ArrayList<>();
        this.myTweets = new ArrayList<>();
    }

    public void twittar(Tweet tweet){
        myTweets.add(tweet);
        for(User user : seguidores){
            user.timeline.add(tweet);
            if(user.idNaoLidas == -1){
                user.idNaoLidas = user.timeline.size() - 1;
            }
        }
    }

    public void follow(User other){
        if(seguindo.contains(other)){
            System.out.println(this.username + " já segue " + other.username + "!");
        }else{
            seguindo.add(other);
            other.seguidores.add(this);
            System.out.println("Seguido com êxito!");
        }
    }

    public void like(int idTweet){
        for(Tweet tweet : timeline){
            if(idTweet == tweet.id){
                tweet.addLike(username);
                return;
            }
        }
        System.out.println("Tweet inexistente!");
    }

    public void getTimeline() {
        for(Tweet tt : timeline){
            System.out.println(tt);
        }
        idNaoLidas = -1;
    }

    public void getMyTweets(){
        for(Tweet mt : myTweets){
            System.out.println(mt);
        }
    }

    public void getNaoLidas(){
        if(idNaoLidas != -1){
            for(int i = idNaoLidas; i < timeline.size(); i++){
                System.out.println(timeline.get(i));
            }
            idNaoLidas = -1;
        }else{
            System.out.println("Nenhum tweet não lido!");
        }
    }

    @Override
    public String toString(){
        String saida = username + "\n Seguidos   [ ";
        for(User user : seguindo){
            saida += user.username + " ";
        }
        saida += "]\n Seguidores [ ";
        for(User user : seguidores){
            saida += user.username + " ";
        }
        saida += "]";
        return saida;
    }
}

class Sistema{
    TreeMap<String, User> usuarios;
    int idTweet;

    Sistema(){
        this.usuarios = new TreeMap<>();
        this.idTweet = 0;
    }

    public void addUser(String username){
        if(usuarios.containsKey(username)){
            System.out.println("Usuário já cadastrado!");
        }else{
            usuarios.put(username, new User(username));
            System.out.println("Usuário cadastrado com êxito!");
        }
    }

    public void followTo(String username1, String username2){
        if(usuarios.containsKey(username1)){
            if(usuarios.containsKey(username2)){
                usuarios.get(username1).follow(usuarios.get(username2));
            }else{
                System.out.println(username2 + " não existe!");
            }
        }else{
            System.out.println(username1 + " não existe!");
        }
    }

    public void twittar(String username, String tweet){
        if(usuarios.containsKey(username)){
            usuarios.get(username).twittar(new Tweet(idTweet, username, tweet));
            idTweet++;
        }else{
            System.out.println("Usuário inexistente!");
        }
    }

    public void like(String username, int idTweet){
        if(usuarios.containsKey(username)){
            usuarios.get(username).like(idTweet);
        }else{
            System.out.println("Usuário inexistente!");
        }
    }

    public void showUsers(){
        for(User user : usuarios.values()){
            System.out.println(user);
        }
    }

    public void showTimeline(String username){
        if(usuarios.containsKey(username)){
            usuarios.get(username).getTimeline();
        }else{
            System.out.println("Usuário inexistente!");
        }
    }

    public void showMyTweets(String username){
        if(usuarios.containsKey(username)){
            usuarios.get(username).getMyTweets();
        }else{
            System.out.println("Usuário inexistente!");
        }
    }

    public void showNaoLidas(String username){
        if(usuarios.containsKey(username)){
            usuarios.get(username).getNaoLidas();
        }else{
            System.out.println("Usuário inexistente!");
        }
    }
}

public class Twitter{
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

        while(true){
            String line = scanner.nextLine();
            String commands[] = line.split(" ");

            try{
                if(commands[0].equals("addUser")){
                    sistema.addUser(commands[1]);
                }else if(commands[0].equals("show")){
                    sistema.showUsers();
                }else if(commands[0].equals("follow")){
                    sistema.followTo(commands[1], commands[2]);
                }else if(commands[0].equals("twittar")){
                    String aux = "";
                    for(int i = 2; i < commands.length; i++){
                        if(i == commands.length - 1){
                            aux += commands[i];
                        }else{
                            aux += commands[i] + " ";
                        }
                    }
                    sistema.twittar(commands[1], aux);
                }else if(commands[0].equals("like")){
                    sistema.like(commands[1], Integer.parseInt(commands[2]));
                }else if(commands[0].equals("timeline")){
                    sistema.showTimeline(commands[1]);
                }else if(commands[0].equals("myTweets")){
                    sistema.showMyTweets(commands[1]);
                }else if(commands[0].equals("naoLidas")){
                    sistema.showNaoLidas(commands[1]);
                }else if(commands[0].equals("exit")){
                    break;
                }else{
                    System.out.println("Comando inválido!");
                }
            }catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("Insira todos os comandos!");
            }
        }
        scanner.close();
    }
}
