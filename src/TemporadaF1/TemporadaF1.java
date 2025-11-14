package TemporadaF1;

import Data.ConnectDB;
import Services.CircuitService;
import Services.MemberService;
import Services.RaceService;
import Services.TeamService;
import Utils.Menu;

import java.util.ArrayList;


public class TemporadaF1 {

    static Menu mainMenu = new Menu(new ArrayList<>() {{
        add("1 - Circuitos");
        add("2 - Equipes");
        add("3 - Membros");
        add("4 - Corridas");
        add("5 - Temporada");
        add("0 - Sair");
    }}, "Menu Principal");

    static Menu circuitsMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Principal");

    static Menu teamsMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Principal");

    static Menu membersMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Principal");

    static Menu racesMenu = new Menu(new ArrayList<>() {{
        add("1 - Adicionar");
        add("2 - Listar Todos");
        add("3 - Remover");
        add("4 - Voltar");
    }}, "Menu Principal");

    static void main(String[] args) {

        ConnectDB db = new ConnectDB("dpg-d3rcb88gjchc73cpjlug-a.oregon-postgres.render.com",
                "testjava", "Q0buWnbkqDMFmYEHZXXMtSHL54NHrNZ9", "testjava_gz5z");


        while (true) {
            switch (mainMenu.showMenu()) {
                case 1:
                    switch (circuitsMenu.showMenu()) {
                        case 1:
                            CircuitService.CreateCircuit();
                            break;
                        case 2:
                            CircuitService.GetAllCircuits();
                            break;
                        case 3:
                            CircuitService.DeleteCircuit();
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
                            TeamService.CreateTeam();
                            break;
                        case 2:
                            TeamService.GetAllTeams();
                            break;
                        case 3:
                            TeamService.DeleteTeam();
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
                            MemberService.CreateMember();
                            break;
                        case 2:
                            MemberService.GetAllMembers();
                            break;
                        case 3:
                            MemberService.DeleteMember();
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
                            RaceService.CreateRace();
                            break;
                        case 2:
                            RaceService.GetAllRaces();
                            break;
                        case 3:
                            RaceService.DeleteRace();
                            break;
                        case 4:
                            break;
                        default:
                            System.err.println("Opção Inválida");
                            break;
                    }
                    break;
                case 5:
                    //Adicionar Menu Temporada
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
