package DAO;

import Data.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EngineerDAO {

    public static void InsertEngineer(ConnectDB db) {
        try {
            Scanner sc = new Scanner(System.in);

            String sql = "INSERT INTO Engineers (SPECIALTY, YEAR_OF_EXPERIENCE) VALUES (?, ?)";

            System.out.println("Informe o nome: ");
            String nome = sc.nextLine();
            System.out.println("Informe a idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o salario: ");
            double salario = sc.nextDouble();
            sc.nextLine();
            System.out.println("Informe a especialidade: ");
            String especialidade = sc.nextLine();
            System.out.println("Informe os anos de experiencia: ");
            int anosExperiencia = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, especialidade);
            ps.setInt(2, anosExperiencia);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int teamId = generatedKeys.getInt(1);

                    String sqlMember = "INSERT INTO public.TEAMMEMBERS (NAME, AGE, WAGE, ID_MEMBER) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps2 = db.getConnection().prepareStatement(sqlMember);
                    ps2.setString(1, nome);
                    ps2.setInt(2, idade);
                    ps2.setDouble(3, salario);
                    ps2.setInt(4, teamId);

                    int affectedRow = ps2.executeUpdate();
                    if (affectedRow > 0) {
                        System.out.println("Engenheiro cadastrado com sucesso!");
                    }
                    ps2.close();
                }
                generatedKeys.close();
            }
            sc.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
