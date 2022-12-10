import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Asena1984");
        Statement st = conn.createStatement();
        System.out.println("Bağlantı Başarılı");

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        String sql1 ="SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";
        ResultSet resultSet1= st.executeQuery(sql1);

        while( resultSet1.next()){

            System.out.println(resultSet1.getString("company")+ "--"+ resultSet1.getInt("number_of_employees"));
        }

        //2.yol
        String sql2 = "select company,  number_of_employees from companies\n" +
                "where number_of_employees =(select max(number_of_employees) from companies\n" +
                "where number_of_employees < (select max (number_of_employees) from companies))\n";
        ResultSet resultSet2= st.executeQuery(sql2);

        while(resultSet2.next()){
            System.out.println(resultSet2.getString("company")+"--"+ resultSet2.getInt(2));
        }

        conn.close();
        st.close();
        resultSet2.close();
        resultSet1.close();





    }
}
