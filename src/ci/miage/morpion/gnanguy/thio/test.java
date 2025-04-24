package ci.miage.morpion.gnanguy.thio;
import java.sql.Connection;
import  java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {
    public static void main(String arg[]) throws SQLException {
        String sql = "select * from rdv";
        Connection connect;
        ResultSet resultSet;
        PreparedStatement prepare;
        connect =Database.connectDB();
        prepare = connect.prepareStatement(sql);
        resultSet = prepare.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("nom"));
        }

    }
}
