import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        Preparedstatement interface, birden cok kez calistirilabilen onceden derlenmis bir sql kodunu
        temsil eder. parametrelendirilmis sql sorgulari (query) ile calisir.bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz .

         */

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Asena1984");
        Statement st = conn.createStatement();
        System.out.println("Bağlantı Başarılı");


        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1.adim; prepared statement querysini olustur.
       String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company= ?";

       //2. Adim prepared statement objesini olustur
        PreparedStatement pst1=  conn.prepareStatement(sql1);

        //3.Adim ; setInt() , setString(), ... methodlarini kullanarak soru isaretleri yerlerine deger atiyoruz
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");


        //4.Adim execute query query yi calistir


        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 =" SELECT * FROM companies";
        ResultSet rs1= st.executeQuery(sql2);

        while(rs1.next()){
            System.out.println(rs1.getInt(1) +"--"+ rs1.getString(2)+ "--"+rs1.getInt(3));
        }


        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");


        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi2);

        ResultSet rs2 = st.executeQuery(sql2);

        while (rs2.next()){
            System.out.println(rs2.getInt(1)+ " -- "+ rs2.getString(2)+ " -- "+ rs2.getInt(3));
        }

        conn.close();
        st.close();
        rs1.close();
        rs2.close();
        pst1.close();




    }
}
