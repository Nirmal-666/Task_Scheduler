public class TestDB {
    public static void main(String[] args) {
        try {
            java.sql.Connection con = DBConnection.getConnection();
            if (con != null)
                System.out.println(" Connected successfully!");
            else
                System.out.println(" Connection failed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
