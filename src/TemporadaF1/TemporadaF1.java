package TemporadaF1;

import Data.ConnectDB;
import Services.*;
import Utils.Menu;

import java.util.ArrayList;

public class TemporadaF1 {

    static Menu mainMenu = new Menu(new ArrayList<>() {{
        add("1 - Circuitos");
        add("2 - Equipes");
        add("3 - Membros");
        add("4 - Corridas");
        add("5 - Carros");
        add("6 - Temporada");
        add("0 - Sair");
    }}, "Menu Principal");

    static Menu circuitsMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Circuitos");

    static Menu teamsMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Equipes");

    static Menu membersMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Membros");

    static Menu carsMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Carros Por Equipe");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Carros");

    static Menu racesMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Corridas");

    static Menu seasonMenu = new Menu(new ArrayList<>() {{
        add("1 - Campeonato de Pilots");
        add("2 - Campeonato de Construtores");
        add("3 - Voltar");
    }}, "Menu Temporada");

    static void main(String[] args) {

        ConnectDB db = new ConnectDB("localhost",
                "postgres", "pg@2025!", "Formula1");

        RaceService _raceService = new RaceService();

        while (true) {
            switch (mainMenu.showMenu()) {
                case 1:
                    switch (circuitsMenu.showMenu()) {
                        case 1:
                            CircuitService.CreateCircuit(db);
                            break;
                        case 2:
                            CircuitService.GetAllCircuits(db);
                            break;
                        case 3:
                            CircuitService.DeleteCircuit(db);
                            break;
                        case 4:
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 2:
                    switch (teamsMenu.showMenu()) {
                        case 1:
                            TeamService.CreateTeam(db);
                            break;
                        case 2:
                            TeamService.GetAllTeams(db);
                            break;
                        case 3:
                            TeamService.DeleteTeam(db);
                            break;
                        case 4:
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 3:
                    switch (membersMenu.showMenu()) {
                        case 1:
                            MemberService.CreateMember(db);
                            break;
                        case 2:
                            MemberService.GetAllMembers(db);
                            break;
                        case 3:
                            MemberService.DeleteMember(db);
                            break;
                        case 4:
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 4:
                    switch (racesMenu.showMenu()) {
                        case 1:
                            _raceService.CreateRace(db);
                            break;
                        case 2:
                            _raceService.GetAllRaces(db);
                            break;
                        case 3:
                            //_raceService.DeleteRace(db);
                            break;
                        case 4:
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 5:
                    switch (carsMenu.showMenu()) {
                        case 1:
                            CarService.CreateCar(db);
                            break;
                        case 2:
                            CarService.GetAllCars(db);
                            break;
                        case 3:
                            CarService.DeleteCar(db);
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 6:
                    switch (seasonMenu.showMenu()) {
                        case 1:
                            SeasonService.DriversChampionship(db);
                            break;
                        case 2:
                            SeasonService.TeamsChampionship(db);
                            break;
                        case 3:
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opção Inválida");
            }
        }
    }
}
