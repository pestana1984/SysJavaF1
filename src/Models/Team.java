package Models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String citizenship;
    private TeamBoss boss;
    private int pointsOnSeason;

    public ArrayList<Car> cars;
    public ArrayList<TeamMember> members;

    public Team(String name, String citizenship, TeamBoss boss) {
        this.name = name;
        this.citizenship = citizenship;
        this.boss = boss;
        this.cars = new ArrayList<Car>();
        this.members = new ArrayList<TeamMember>();
        this.members.add(boss);
        this.pointsOnSeason = 0;
    }

    public Team(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getBossName() {
        return this.boss.getName();
    }

    public int getPointsOnSeason() {
        return this.pointsOnSeason;
    }

    public void addMember(TeamMember member){
        this.members.add(member);
    }

    public ArrayList<TeamMember> getMembers(){
        return this.members;
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

    public void calculatePointsOnSeason(){
        int totalPoints = 0;

        for(Car car : cars){
            totalPoints += car.getAccountableDriver().getPointsOnSeason();
        }

        this.pointsOnSeason = totalPoints;
    }
}
