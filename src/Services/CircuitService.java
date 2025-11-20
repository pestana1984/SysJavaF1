package Services;

import DAO.CircuitDAO;
import Data.ConnectDB;
import Models.Circuit;

import java.util.Scanner;

public class CircuitService {

    public static void CreateCircuit(ConnectDB db){
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome do circuito: ");
        String nome = sc.nextLine();
        System.out.println("Informe o pais do circuito: ");
        String pais = sc.nextLine();

        Circuit circuit = new Circuit(nome, pais);

        int inserted = CircuitDAO.InsertCircuit(db, circuit);

        if(inserted == 1){
            System.out.println("Circuito cadastrado com sucesso!");
        }
    }

    public static void GetAllCircuits(ConnectDB db){
        System.out.println("---------------------------");
        CircuitDAO.GetCircuits(db).forEach(Circuit::showInfo);
        System.out.println("---------------------------");
    }

    public static void DeleteCircuit(ConnectDB db){

        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do circuito: ");
        String nome = sc.nextLine();

        Circuit circuit = CircuitDAO.GetCircuitByName(db, nome);

        if(circuit == null){
            System.err.println("Circuito não encontrado");
            return;
        }

        CircuitDAO.DeleteCircuitByName(db, circuit.getName());
        System.out.println("Circuito deletado com sucesso!");
    }

}
