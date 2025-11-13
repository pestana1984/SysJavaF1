package Models;

public abstract class TeamMember {
    private String name;
    private int age;
    private double wage;


    public TeamMember(String name, int age, double wage) {
        this.name = name;
        this.age = age;
        this.wage = wage;
    }

    public TeamMember(String name){
        this.name = name;
    }

    public void showInfo(){
        System.out.println("Nome: " + getName());
        System.out.println("Idade: " + getAge());
        System.out.println("Salário: " + getWage());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() < 3) {
            System.out.println("O nome não pode conter menos de 3 caracteres");
            return;
        }
        else {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 16) {
            System.out.println("O piloto tem menos de 16 anos!");
            return;
        }
        else {
            this.age = age;
        }
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
