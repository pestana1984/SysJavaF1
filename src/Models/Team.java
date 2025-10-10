package Models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String citizenship;
    private TeamBoss boss;

    //TODO Adicionar um chefe de equipe no construtor

    public ArrayList<Car> cars;
    public ArrayList<TeamMember> members;

    public Team(String name, String citizenship, TeamBoss boss) {
        this.name = name;
        this.citizenship = citizenship;
        this.boss = boss;
        this.cars = new ArrayList<Car>();
        this.members = new ArrayList<TeamMember>();
        this.members.add(boss);
    }

    public void addMember(TeamMember member){
        this.members.add(member);
    }

    public void addCar(Car car){
        this.cars.add(car);
    }

    public void showTeam(){
        System.out.println(this.name);
        System.out.println(this.citizenship);

        for(int i = 0; i < cars.size(); i++){
            cars.get(i).showInfo();
        }

        for(int i = 0; i < members.size(); i++){
            members.get(i).showInfo();
        }
    }

    public int calculatePointsOnSeason(){
        int totalPoints = 0;

        for(int i = 0; i < members.size(); i++){
            if(members.get(i) instanceof Driver)
                totalPoints += ((Driver)members.get(i)).getPointsOnSeason();
        }

        return totalPoints;
    }
}
