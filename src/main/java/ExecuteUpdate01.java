import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Asena1984");
        Statement st = conn.createStatement();
        System.out.println("Bağlantı Başarılı");

        //--1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees
        //--değerlerini 16000 olarak UPDATE edin.

        String sql1 ="update companies \n" +
                "set number_of_employees = 16000 where number_of_employees < (select AVG(number_of_employees) from companies)";
        int updateEdilenSatirSayisi = st.executeUpdate(sql1);
        System.out.println("updateEdilenSatirSayisi "+ updateEdilenSatirSayisi );

        ResultSet resultSet1 = st.executeQuery("select * from companies");

            while(resultSet1.next()){
                System.out.println(resultSet1.getInt(1)+"--"+ resultSet1.getString(2)+"++"+resultSet1.getInt(3));
            }
        conn.close();
        st.close();
        resultSet1.close();


        }






    }

