import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String BASE_CONNECTION = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASS = "5tr1ke00";
    private static final String ACTUAL_DATABASE = "jdbc:mysql://localhost:3306/mybnb2";

    public static void main(String[] args) {
        deleteDatabase();
        //initializeDatabase();
        System.out.println("Ok Boss");

    }

    public static void initializeDatabase() {
        Connection conn;
        Statement stmt;

        try {
            Class.forName(dbClassName);
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(BASE_CONNECTION, USER, PASS);
            System.out.println("Successfully connected to base database");

            System.out.println("Creating MyBnB database");
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS MyBnB2";
            stmt.executeUpdate(sql);
            System.out.println("MyBnB database has been created");
            stmt.close();
            conn.close();

            System.out.println("Connecting to MyBnB database");
            conn = DriverManager.getConnection(ACTUAL_DATABASE, USER, PASS);
            System.out.println("Connected");

            System.out.println("Initializing all tables");
            stmt = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "name VARCHAR(255) NOT NULL," +
                    "address VARCHAR(255) NULL," +
                    "dob DATE NOT NULL," +
                    "occupation VARCHAR(255) NULL," +
                    "sin INT NOT NULL," +
                    "cc_num INT NOT NULL," +
                    "username VARCHAR(255) NOT NULL," +
                    "pass VARCHAR(255) NOT NULL," +
                    "UNIQUE(username)," +
                    "PRIMARY KEY(sin)," +
                    "CHECK(DATEDIFF(DATE(SYSDATE()), dob) >= 6574));";
            stmt.executeUpdate(sql);
            System.out.println("Created users table");

            sql = "CREATE TABLE IF NOT EXISTS hosts(SELECT * FROM users);";
            stmt.executeUpdate(sql);
            System.out.println("Created hosts table");

            sql = "CREATE TABLE IF NOT EXISTS renters(SELECT * FROM users);";
            stmt.executeUpdate(sql);
            System.out.println("Created renters table");

            sql = "CREATE TABLE IF NOT EXISTS listings(" +
                    "user_id INT NOT NULL," +
                    "list_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "type ENUM('Apartment', 'House', 'Townhouse', 'Villa', 'Tent'," +
                    "'Condominium', 'Bungalow', 'Cottage', 'Loft', 'Lighthouse'," +
                    "'Dormitory', 'Castle', 'Boat', 'RV', 'Other') DEFAULT 'Other'," +
                    "latitude DECIMAL(13, 10) NOT NULL," +
                    "longitude DECIMAL(13, 10) NOT NULL," +
                    "listing_address VARCHAR(255) NOT NULL," +
                    "listing_city VARCHAR(255) NOT NULL," +
                    "listing_country VARCHAR(255) NOT NULL," +
                    "postal_code VARCHAR(255) NOT NULL," +
                    "FOREIGN KEY(user_id) REFERENCES users(sin) ON DELETE CASCADE," +
                    "UNIQUE(latitude, longitude)," +
                    "CHECK(latitude >= -90 AND latitude <= 90)," +
                    "CHECK(longitude >= -180 AND longitude <= 180));";
            stmt.executeUpdate(sql);
            System.out.println("Created listings table");

            sql = "CREATE TABLE IF NOT EXISTS amenities(" +
                    "listing_id INT NOT NULL," +
                    "amenity VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY(listing_id, amenity)," +
                    "FOREIGN KEY(listing_id) REFERENCES listings(list_id) ON DELETE CASCADE," +
                    "CHECK(amenity IN ('Kitchen', 'Internet', 'TV', 'Essentials', 'Heating'," +
                    "'Air Conditioning', 'Washer', 'Dryer', 'Free Parking', 'Wireless'," +
                    "'Breakfast', 'Pets', 'Family Friendly', 'Suitable for Events'," +
                    "'Smoking', 'Wheelchair Accessible', 'Elevator', 'Fireplace', 'Buzzer'," +
                    "'Doorman', 'Pool', 'Hot Tub', 'Gym', '24 Hours Check-In', 'Hangers'," +
                    "'Iron', 'Hair Dryer', 'Laptop-friendly Workspace'," +
                    "'Carbon Monoxide Detector', 'First Aid Kit', 'Smoke Detector')));";
            stmt.executeUpdate(sql);
            System.out.println("Created amenities table");

            sql = "CREATE TABLE IF NOT EXISTS availability(" +
                    "av_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "listing_id INT NOT NULL," +
                    "av_start DATE NOT NULL," +
                    "av_end DATE NOT NULL," +
                    "listing_price DECIMAL(10, 2) NOT NULL," +
                    "FOREIGN KEY(listing_id) REFERENCES listings(list_id) ON DELETE CASCADE," +
                    "CHECK(listing_price >= 0), CHECK(av_start <= av_end));";
            stmt.executeUpdate(sql);
            System.out.println("Created availability table");

            sql = "CREATE TABLE IF NOT EXISTS bookings(" +
                    "booking_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "availability_id INT NOT NULL," +
                    "renter_id INT NOT NULL," +
                    "start_date DATE NOT NULL," +
                    "end_date DATE NOT NULL," +
                    "status ENUM('Available', 'Renter Cancelled', 'Host Cancelled')," +
                    "FOREIGN KEY(availability_id) REFERENCES availability(av_id) ON DELETE CASCADE," +
                    "FOREIGN KEY(renter_id) REFERENCES users(sin) ON DELETE CASCADE," +
                    "CHECK(start_date <= end_date));";
            stmt.executeUpdate(sql);
            System.out.println("Created bookings table");

            sql = "CREATE TABLE IF NOT EXISTS profile_comments(" +
                    "user_id INT NOT NULL," +
                    "commenter_id INT NOT NULL," +
                    "content TEXT NOT NULL," +
                    "PRIMARY KEY(user_id, commenter_id)," +
                    "FOREIGN KEY(user_id) REFERENCES users(sin) ON DELETE CASCADE," +
                    "FOREIGN KEY(commenter_id) REFERENCES users(sin) ON DELETE CASCADE);";
            stmt.executeUpdate(sql);
            System.out.println("Created profile_comments table");

            sql = "CREATE TABLE IF NOT EXISTS profile_ratings(" +
                    "user_id INT NOT NULL," +
                    "rater_id INT NOT NULL," +
                    "rating INT NOT NULL," +
                    "PRIMARY KEY(user_id, rater_id)," +
                    "FOREIGN KEY(user_id) REFERENCES users(sin) ON DELETE CASCADE," +
                    "FOREIGN KEY(rater_id) REFERENCES users(sin) ON DELETE CASCADE);";
            stmt.executeUpdate(sql);
            System.out.println("Created profile_ratings table");

            sql = "CREATE TABLE IF NOT EXISTS listing_comments(" +
                    "listing_id INT NOT NULL," +
                    "commenter_id INT NOT NULL," +
                    "content TEXT NOT NULL," +
                    "PRIMARY KEY(listing_id, commenter_id)," +
                    "FOREIGN KEY(listing_id) REFERENCES listings(list_id) ON DELETE CASCADE," +
                    "FOREIGN KEY(commenter_id) REFERENCES users(sin) ON DELETE CASCADE);";
            stmt.executeUpdate(sql);
            System.out.println("Created listing_comments table");

            sql = "CREATE TABLE IF NOT EXISTS listing_ratings(" +
                    "listing_id INT NOT NULL," +
                    "rater_id INT NOT NULL," +
                    "rating INT NOT NULL," +
                    "PRIMARY KEY(listing_id, rater_id)," +
                    "FOREIGN KEY(listing_id) REFERENCES listings(list_id) ON DELETE CASCADE," +
                    "FOREIGN KEY(rater_id) REFERENCES users(sin) ON DELETE CASCADE);";
            stmt.executeUpdate(sql);
            System.out.println("Created listing_ratings table");

            System.out.println("All tables have been created");
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Something went wrong while initializing database.");
        }
    }

    public static void deleteDatabase() {
        Connection conn;
        Statement stmt;

        try {
            Class.forName(dbClassName);
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(BASE_CONNECTION, USER, PASS);
            System.out.println("Successfully connected to base database");

            stmt = conn.createStatement();
            String sql = "DROP DATABASE IF EXISTS mybnb2";
            stmt.executeUpdate(sql);

            System.out.println("Database mybnb2 has been deleted");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while deleting the database.");
        }
    }
}
