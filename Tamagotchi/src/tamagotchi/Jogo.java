package tamagotchi;

import java.util.Scanner;

public class Jogo{
    Pet pet;

    void createPet(Pet pet){
        this.pet = pet;
    }

    void showPet(){
        System.out.print("E: " + pet.getEnergy() + "/" + pet.getEnergyMax() + ", ");
        System.out.print("S: " + pet.getHungry() + "/" + pet.getHungryMax() + ", ");
        System.out.print("L: " + pet.getClean() + "/" + pet.getCleanMax() + ", ");
        System.out.print("D: " + pet.getDiamonds() + ", ");
        System.out.println("I: " + pet.getAge());
    }

    void play(){
        pet.setEnergy(pet.getEnergy() - 2);
        pet.setHungry(pet.getHungry() - 1);
        pet.setClean(pet.getClean() - 3);
        pet.setDiamonds(pet.getDiamonds() + 1);
        pet.setAge(pet.getAge() + 1);
        died();
    }
    
    void eat(){
        if(pet.getHungry() == pet.getHungryMax()){
            System.out.println("fail: o pet está cheio");
        }else{
            if(pet.getHungry() + 4 > pet.getHungryMax()){
                pet.setHungry(pet.getHungryMax());
            }else{
                pet.setHungry(pet.getHungry() + 4);
            }
            pet.setEnergy(pet.getEnergy() - 1);
            pet.setClean(pet.getClean() - 2);
            pet.setAge(pet.getAge() + 1);
            died();
        }
    }

    void sleep(){
        if(pet.getEnergy() <= (pet.getEnergyMax() - 5)){
            pet.setAge(pet.getAge() + (pet.getEnergyMax() - pet.getEnergy()));
            pet.setEnergy(pet.getEnergyMax());
            died();
        }else{
            System.out.println("fail: não está com sono");
        }
    }

    void clean(){
        if(pet.getClean() == pet.getCleanMax()){
            System.out.println("fail: o pet já está totalmente limpo");
        }else{
            pet.setEnergy(pet.getEnergy() - 3);
            pet.setHungry(pet.getHungry() - 1);
            pet.setClean(pet.getCleanMax());
            pet.setAge(pet.getAge() + 2);
            died();
        }
    }

    void died(){
        if(pet.getEnergy() <= 0){
            System.out.println("fail: pet morreu de fraqueza");
            pet.setAlive(false);
        }else if(pet.getHungry() <= 0){
            System.out.println("fail: pet morreu de fome");
            pet.setAlive(false);
        }else if(pet.getClean() <= 0){
            System.out.println("pet morreu de sujeira");
            pet.setAlive(false);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Jogo jogo = new Jogo();
        while (true) {
            String entrada = input.nextLine();
            String data[] = entrada.split(" ");
            if(data[0].equals("init")) {
                int energia = Integer.parseInt(data[1]);
                int fome = Integer.parseInt(data[2]);
                int limpeza = Integer.parseInt(data[3]);
                Pet pet = new Pet(energia, fome, limpeza);
                jogo.createPet(pet);
            }else if(data[0].equals("exit")){
                break;
            }else if(jogo.pet == null){
                System.out.println("O pet não foi inicializado");
            }else if(jogo.pet.getAlive() == false){
                System.out.println("fail: pet está morto");
            }else if(data[0].equals("show")){
                jogo.showPet();
            }else if(data[0].equals("play")){
                jogo.play();
            }else if(data[0].equals("eat")){
                jogo.eat();
            }else if(data[0].equals("sleep")){
                jogo.sleep();
            }else if(data[0].equals("clean")){
                jogo.clean();
            }else{
                System.out.println("Comando inválido!");
            }
        }
        input.close();
    }
}