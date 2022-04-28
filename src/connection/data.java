package connection;

import java.sql.*;
import java.util.ArrayList;

public class data {

    public static ArrayList<Person> persons = new ArrayList<>();

    //Verbindet sich mit db und fuegt alle personen in persons ein
    public void selectAll(){

        String sql = "SELECT * FROM Sportarten";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/Umfrage.db");
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set

            while (rs.next()) {

                Person person = new Person();

                person.ID = rs.getInt("ID");
                person.Latitude = rs.getDouble("Latitude");
                person.Longitude = rs.getDouble("Longitude");
                person.Geschlecht = rs.getString("Geschlecht");
                person.Basketball = rs.getInt("Basketball");
                person.Radfahren = rs.getInt("Radfahren");
                person.Schwimmen = rs.getInt("Schwimmen");
                person.Fussball = rs.getInt("Fussball");
                person.Laufen = rs.getInt("Laufen");

                persons.add(person);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Person> getPersons() {
        return persons;
    }
}
