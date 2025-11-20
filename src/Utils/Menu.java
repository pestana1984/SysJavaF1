package Utils;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<String> options;
    private String title;

    public Menu(List<String> options, String title) {
        this.options = options;
        this.title = title;
    }

    public int showMenu(){

        Scanner sc = new Scanner(System.in);

        System.out.println(this.title);
        for(int i = 0; i < this.options.size(); i++){
            System.out.println(this.options.get(i));
        }

        System.out.print("Informe a opção desejada: ");
        return sc.nextInt();
    }
}