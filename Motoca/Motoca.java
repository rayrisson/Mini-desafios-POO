import java.util.Scanner;

class Pessoa {
    String nome;
    int idade;
    public Pessoa(String nome, int idade){ //nao eh metodo, eh invocado automaticaticamente na criacao
        this.nome = nome;
        this.idade = idade;
    }
}

public class Motoca {
	Pessoa pessoa;
	int potencia;
	float tempo;

	public Motoca(int potencia){
		this.potencia = potencia;
	}
	
	void embarcar(Pessoa pessoa) {
		if(this.pessoa == null)
			this.pessoa = pessoa;
		else
			System.out.println("Ja tem gente na motoca");
	}
	
	void desembarcar() {
		if(this.pessoa != null)
			this.pessoa = null;
		else
			System.out.println("Nao tem ninguem na moto");
	}

	void dirigir(){
		if(this.pessoa == null)
			System.out.println("Nao tem ninguem na moto");
		else if(this.pessoa.idade < 2)
			System.out.println("Muito pequeno pra andar de moto");
		else if(this.pessoa.idade > 10)
			System.out.println("Muito grande pra andar de moto");
		else if(this.tempo < 1)
			System.out.println("Sem tempo, irmão!!");
		else{
			System.out.println(this.pessoa.nome + " rodou " + this.tempo + " minutos!");
			for(int i = 0; i < this.potencia; i++){
				System.out.print("Run...");
			}
			System.out.println("");
		}
	}

	void pagar(float valor){
		if(valor < 0){
			System.out.println("Insira valor válido");
		}else{
			this.tempo = valor;
			System.out.println("Você tem " + this.tempo + " minutos");
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Motoca moto = new Motoca(1);
		while(true){
			String line = input.nextLine();
			String info[] = line.split(" ");
			if(info[0].equals("init")){
				moto.potencia = Integer.parseInt(info[1]);
			}else if(info[0].equals("pagar")){
				moto.pagar(Float.parseFloat(info[1]));
			}else if(info[0].equals("embarcar")){
				Pessoa condutor = new Pessoa(info[1], Integer.parseInt(info[2]));
				moto.embarcar(condutor);
			}else if(info[0].equals("desembarcar")){
				moto.desembarcar();
			}else if(info[0].equals("dirigir")){
				moto.dirigir();
			}else if(info[0].equals("sair")){
				break;
			}else{
				System.out.println("Comando inválido");
			}
		}
		input.close();
		/*Pessoa davi = new Pessoa("Davi", 10);
		{
			Motoca moto = new Motoca();
			moto.embarcar(davi);
			moto.dirigir();
			moto.desembarcar();
		}
		Motoca moto2 = new Motoca();
		moto2.embarcar(davi);
		moto2.dirigir();
		moto2.desembarcar();

		System.out.println(davi.nome);*/
	}
/*
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Motoca motoca  = new Motoca();
		while(true) {
			String line = scanner.nextLine();
			String ui[] = line.split(" ");
			if(ui[0].equals("end")) {
				break;
			}else if(ui[0].equals("embarcar")) { //nome  idade
				int idade = Integer.parseInt(ui[2]);
				motoca.embarcar(ui[1], idade);
			}else if(ui[0].equals("desembarcar")) {
				motoca.desembarcar();
			}else if(ui[0].equals("dirigir")) {
				motoca.dirigir();
			}else {
				System.out.println("Comando invalido");
			}
		}
		scanner.close();
	}
*/
}






