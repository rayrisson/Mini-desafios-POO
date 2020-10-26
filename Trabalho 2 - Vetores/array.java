import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class array{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList <Integer> vetor = new ArrayList<Integer>();
        while(true){
            String opc = input.nextLine();
            String bLine[] = opc.split(" ");
            if(bLine[0].equals("add")){
                for(int i = 1; i < bLine.length; i++)
                    vetor.add(Integer.parseInt(bLine[i]));
            }else if(bLine[0].equals("show")){
                System.out.println(vetor);
            }else if(bLine[0].equals("rshow")){
                Collections.reverse(vetor);
                System.out.println(vetor);
                Collections.reverse(vetor);
            }else if(bLine[0].equals("ins")){
                vetor.add(Integer.parseInt(bLine[1]), Integer.parseInt(bLine[2]));
            }else if(bLine[0].equals("rmi")){
                if(Integer.parseInt(bLine[1]) < vetor.size() && Integer.parseInt(bLine[1]) > 0)
                    vetor.remove(Integer.parseInt(bLine[1]));
                else
                    System.out.println("Fail");
            }else if(bLine[0].equals("rma")){
                vetor.removeAll(Collections.singleton(Integer.parseInt(bLine[1])));
            }else if(bLine[0].equals("get")){
                System.out.print("[ ");
                for(int i = 1; i < bLine.length; i++)
                    System.out.print(vetor.get(Integer.parseInt(bLine[1])));
                System.out.println("]");
            }else if(bLine[0].equals("set")){
                System.out.print("[ ");
                for(int i = 1; i < bLine.length; i++)
                    System.out.print(vetor.set(Integer.parseInt(bLine[1]), Integer.parseInt(bLine[2])));
                System.out.println("]");
            }else if(bLine[0].equals("find")){
                System.out.print("[ ");
                for(int i = 1; i < bLine.length; i++)
                    System.out.print(vetor.indexOf(Integer.parseInt(bLine[i])) + " ");
                System.out.println("]");
            }else if(bLine[0].equals("end")){
                break;
            }else{
                System.out.println("Operação inválida!");
            }
        }
        input.close();
    }
}