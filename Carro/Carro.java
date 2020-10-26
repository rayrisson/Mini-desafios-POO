public class Carro{
    int nPessoas = 0;
    int maxPessoas = 2;
    float gasolinaAtual = 0;
    float gasolinaMax = 30;
    float odometro = 0;

    boolean embarcar(){
        if (this.nPessoas < this.maxPessoas){
            this.nPessoas += 1;
            System.out.println("Embarcou 1 pessoa tem " + this.nPessoas + " pessoas!");
            return true;
        }
        System.out.println("Tá lotado!");
        return false;
    }

    boolean desembarcar(){
        if(this.nPessoas > 0){
            this.nPessoas -= 1;
            System.out.println("Desembarcou 1 pessoa tem " + this.nPessoas + " pessoas!");
            return true;
        }else{
            System.out.println("Tá vazio!");
            return false;
        }
    }

    void abastecer(){
        if(this.gasolinaAtual < this.gasolinaMax){
            this.gasolinaAtual += 7;
            System.out.println("Você tem abasteces 7 litros, você tem " + this.gasolinaAtual + " litros");
        }else{
            System.out.println("Tanque cheio!");
        }
    }

    void dirigir(){
        if(this.gasolinaAtual > 0){
            System.out.println("Você andou " + this.gasolinaAtual + " quilometros");
            this.odometro += this.gasolinaAtual;
            this.gasolinaAtual = 0;
        }else{
            System.out.println("Tá sem gasolina");
        }
    }

    public static void main(String[] args) {
        Carro carro = new Carro();
        carro.embarcar();
        carro.embarcar();
        carro.desembarcar();
        carro.abastecer();
        carro.abastecer();
        carro.dirigir();
        carro.abastecer();
    }

}


