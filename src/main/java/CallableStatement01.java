import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Asena1984");
        Statement st = conn.createStatement();
        System.out.println("Bağlantı Başarılı");

        /*
/*
Java'da method'lar return type sahibi olsa da olmasa da method olarak adlandırılır.
SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandırilir
 */
        //callablestatement ile function cagirmayi

        //1.Adim FUNCTION kodunu giriniz
        String sql1= "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC )\n" +
                "returns NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y; \n" +
                "\n" +
                "END\n" +
                "\n" +
                "$$";

        //2.adim FUNCTION i calistir
        st.execute(sql1);

        //3.adim function i cagir
        CallableStatement cst1 = conn.prepareCall("{? = call toplamaF(?, ?)}");

//4. Adım: Return için registerOurParameter() methodunu, parametreler için ise set() ... methodlarını uygula.
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        //5.Adim execute() methodu ile callablestatement i calistir.
        cst1.execute();

        //6.adim sonucu cagiriyoruz ve bunun icin return data type tipine göre burda numeric oldugundan javadakine cevirip cagiriyoruz
        System.out.println(cst1.getBigDecimal(1));



        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
//1.Adım: Function kodunu yaz:
        String sql2 = "CREATE OR REPLACE FUNCTION  konininHacmiF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

//2. Adım: Function'ı çalıştır.
        st.execute(sql2);

//3. Adım: Fonction'ı çağır.
        CallableStatement cst2 = conn.prepareCall("{? = call konininHacmiF(?, ?)}");

//4. Adım: Return için registerOurParameter() methodunu, parametreler için ise set() ... methodlarını uygula.
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);

//5. Adım: execute() methodu ile CallableStatement'ı çalıştır.
        cst2.execute();


       /* System.out.println("koninin hacmi: " + String.format("%.2f",cst2.getBigDecimal(1)));
        eğer printfsiz yapmak isterseniz de böyle

        */
        //6. Adım: Sonucu çağırmak için return data type tipine göre
        System.out.printf("%.2f",cst2.getBigDecimal(1));
        conn.close();
        st.close();
        cst1.close();
        cst2.close();



    }
}