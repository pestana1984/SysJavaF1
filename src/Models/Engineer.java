package Models;

public class Engineer extends TeamMember{

    public String specialty;
    public int yearOfExperience;

    public Engineer(String name, int age, double wage, String specialty, int yearOfExperience) {
        super(name, age, wage);
        this.specialty = specialty;
        this.yearOfExperience = yearOfExperience;
    }

    public Car improveCar(Car car){

        //TODO Aumentar a eficiencia do carro
        return car;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Especialidade: " + specialty);
        System.out.println("Anos de Experiência: " + yearOfExperience);
    }
}
