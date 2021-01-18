import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Funktioner {
    static Connection connection = SQLConnector.getConnection();
    static PreparedStatement prep = null;
    Statement statement = null;

    public Funktioner() {

    }


    public static void listAftaler() {
        String ptId = "040769-0787";
        String sqlListAftaler = "SELECT * FROM Aftaler WHERE ptId ='" +ptId+ "';";
        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlListAftaler);


            while(rs.next()) {
                 int idAftaler = rs.getInt("idAftaler");
                 String dato = rs.getString("dato");
                 String varighed = rs.getString("varighed");
                 String aarsag = rs.getString("aarsag");
                 String hospital = rs.getString("hospital");
                 int lokale = rs.getInt("lokale");
                System.out.println("<tr>");

                System.out.println("<td>");
                System.out.println(dato);

                System.out.println("<td>");
                System.out.println(varighed);

                System.out.println("<td>");
                aarsag = aarsag.replaceAll("ø", "&oslash;");
                aarsag = aarsag.replaceAll("æ", "&aelig;");
                aarsag = aarsag.replaceAll("å", "&aring;");
                aarsag = aarsag.replaceAll("Ø", "&Oslash;");
                aarsag = aarsag.replaceAll("Æ", "&AElig;");
                aarsag = aarsag.replaceAll("Å", "&Aring;");
                System.out.println(aarsag);


                System.out.println("<td>");
                hospital = hospital.replaceAll("ø", "&oslash;");
                hospital = hospital.replaceAll("æ", "&aelig;");
                hospital = hospital.replaceAll("å", "&aring;");
                hospital = hospital.replaceAll("Ø", "&Oslash;");
                hospital = hospital.replaceAll("Æ", "&AElig;");
                hospital = hospital.replaceAll("Å", "&Aring;");
                System.out.println(hospital);


                System.out.println("<td>");
                System.out.println(lokale);

                System.out.println("<td> <button name=sletAftale value=" +idAftaler+" type=submit>X</button>");

                System.out.println("</tr>");
             }


         } catch (SQLException throwables){
             throwables.printStackTrace();

        }

    }


    public static void opretAftale(String dato, String varighed, String aarsag, String hospital, int lokale, String ptId) {

                try{
            String SQL = "INSERT INTO Aftaler (dato, varighed, aarsag, hospital,lokale, ptId) VALUES(?,?,?,?,?,?);";
            prep = connection.prepareStatement(SQL);
            prep.setString(1, dato);
            prep.setString(2, varighed);
            prep.setString(3, aarsag);
            prep.setString(4, hospital);
            prep.setInt(5,lokale);
            prep.setString(6, ptId);
            prep.execute();

        } catch (SQLException throwables) {
            System.out.println("FEJL");
            System.out.println("Message:" + throwables.getMessage());
        }


    }
    public static void sletAftale(int aftaleId) {

        String ptId = "040769-0787";
        try {

            Statement statement = connection.createStatement();
            String sqlSletAftaler = "DELETE FROM Aftaler WHERE ptId ='" +ptId+ "' AND idAftaler="+aftaleId+";";
            ResultSet rs = statement.executeQuery(sqlSletAftaler);

    } catch(SQLException throwables) {
            System.out.println("Fejl besked: " + throwables.getMessage());
        }
    }


    public static void main(String[] args) {

    }
}
