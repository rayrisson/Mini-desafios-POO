package tamagotchi;

public class Pet{
    private int energyMax, hungryMax, cleanMax;
    private int energy , hungry, clean;
    private int diamonds = 0, age = 0;
    private boolean alive = true;

    public Pet(int energy, int hungry, int clean){
        this.energy = energy;
        this.energyMax = energy;
        this.hungry = hungry;
        this.hungryMax = hungry;
        this.clean = clean;
        this.cleanMax = clean;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHungry(int hungry){
        this.hungry = hungry;
    }

    public void setClean(int clean) {
        this.clean = clean;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getEnergy() {
        return energy;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public int getHungry() {
        return hungry;
    }

    public int getHungryMax() {
        return hungryMax;
    }

    public int getClean() {
        return clean;
    }

    public int getCleanMax() {
        return cleanMax;
    }

    public int getAge() {
        return age;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public boolean getAlive(){
        return alive;
    }
}