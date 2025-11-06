package DAO;

import Data.ConnectDB;

import java.util.Scanner;

public class CarDAO {

    public static void InsertCar(ConnectDB db) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Informe o modelo do carro: ");
            String modelo = sc.nextLine();
            System.out.println("Informe a potencia do carro: ");
            int potencia = sc.nextInt();
            sc.nextLine();



        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
