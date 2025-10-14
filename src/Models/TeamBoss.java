package Models;

public class TeamBoss extends TeamMember {

    public TeamBoss(String name, int age, double wage) {
        super(name, age, wage);
    }

    public void setBonus(double bonus){
        setWage(getWage() + bonus);
    }
}
