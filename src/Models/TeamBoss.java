package Models;

public class TeamBoss extends TeamMember {

    private double bonus;

    public TeamBoss(String name, int age, double wage) {
        super(name, age, wage);
    }

    public TeamBoss(String name){
        super(name);
    }

    public void setBonus(double bonus){
        this.bonus = bonus;
        setWage(getWage() + bonus);
    }
    public double getBonus(){
        return this.bonus;
    }
}
