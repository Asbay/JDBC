import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Odev {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Asena1984");
        Statement st = conn.createStatement();
        System.out.println("Bağlantı Başarılı");

        boolean sql1= st.execute("CREATE TABLE ogrencinotlari(notlar INT) ");
        System.out.println("sql1= "+ sql1);



        Scanner input =new Scanner(System.in);
        System.out.println("girmek istediginiz notu giriniz");
        int not = input.nextInt();





    }
}
