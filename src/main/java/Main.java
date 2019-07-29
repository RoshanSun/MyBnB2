import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String BASE_CONNECTION = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASS = "5tr1ke00";
    private static final String ACTUAL_DATABASE = "jdbc:mysql://localhost:3306/mybnb2";

    public static void main(String[] args) {
        //deleteDatabase();
        //initializeDatabase();
        //populateDatabase();
        System.out.println("Ok Boss");

        //User Interface
        System.out.println();
        System.out.println("*****************************************************	");
        System.out.println("*---------------------------------------------------*	");
        System.out.println("*--------------- WELCOME TO MyBnB ------------------*	");
        System.out.println("*---------------------------------------------------*	");
        System.out.println("*****************************************************	");
        System.out.println();
        System.out.println("Rent unique accommodations from local hosts all over the world!");
        System.out.println("Feel at home anywhere you go in the world with MyBnB.");

        startingInterface("S");

    }

    public static void startingInterface(String call) {
        Scanner reader = new Scanner(System.in);
        int input;

        // Starting section of interface
        if(call.equalsIgnoreCase("S")) {
            System.out.println("Starting Interface");
            System.out.print("Choose an option: 1.Login, 2.Create/Update User, 3.Reports Section: ");
            input = reader.nextInt();
            if(input == 1) {
                System.out.println("Login has been chosen");
                login();
            } else if (input == 2) {
                System.out.println("Creating user....");
                createUser();
            } else if (input == 3) {

            }
        } else if (call.equalsIgnoreCase("R")) {
            System.out.println("Renting Interface");
        } else if (call.equalsIgnoreCase("H")) {
            System.out.println("Hosting Interface");
        }
    }

    public static void login() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.println("Welcome back to MyBnB. Enter the following info:");
            System.out.print("Are you a Renter (R) or a Host (H)?: ");
            String type = reader.readLine();
            System.out.print("Enter your username: ");
            String user = reader.readLine();
            System.out.print("Enter your password: ");
            String pass = reader.readLine();

            stmt = conn.createStatement();
            String sql;

            if(type.equalsIgnoreCase("R")) {
                sql = "SELECT * FROM renters WHERE username = '" + user + "' AND pass = '" + pass + "';";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println("Welcome back to MyBnB " + rs.getString("username"));
                    System.out.println("Renter successfully logged in");
                    startingInterface("R");
                }

                System.out.println("Invalid user/pass");
                System.out.println("Returning to starting interface");
                startingInterface("S");
            } else if (type.equalsIgnoreCase("H")) {
                sql = "SELECT * FROM hosts WHERE username = '" + user + "' AND pass = '" + pass + "';";
                ResultSet res = stmt.executeQuery(sql);

                while (res.next()) {
                    System.out.println("Welcome back to MyBnB " + res.getString("username"));
                    System.out.println("Host successfully logged in");
                    startingInterface("H");
                }

                System.out.println("Invalid user/pass");
                System.out.println("Returning to starting interface");
                startingInterface("S");
            } else {
                System.out.println("Invalid user type");
                System.out.println("Returning to starting interface");
                startingInterface("S");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error while logging in");
        }
    }

    public static void createUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Please enter your name: ");
            String name = reader.readLine();
            System.out.print("Enter your address (optional): ");
            String address = reader.readLine();
            System.out.print("Enter your date of birth (YYYY-MM-DD, must be over 18): ");
            Date dob = Date.valueOf(reader.readLine());
            System.out.print("Enter your occupation (optional): ");
            String occupation = reader.readLine();
            System.out.print("Enter your SIN number (9 digits, no spaces): ");
            int sin = Integer.parseInt(reader.readLine());
            System.out.print("Enter your credit card number: ");
            long cc_num = Long.parseLong(reader.readLine());
            System.out.print("Enter a username to login to the system with in the future: ");
            String username = reader.readLine();
            System.out.print("Choose a password to login with: ");
            String pass = reader.readLine();

            System.out.println("Attempting to add user to table");
            stmt = conn.createStatement();
            String sql = "INSERT INTO users VALUES(" +
                    "'" + name + "', '" + address + "', '" + dob + "', '" + occupation + "', " + sin + ", " + cc_num + ", '" + username + "', '" + pass + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            System.out.print("Do you want to be a Renter(R) or a Host(H)?: ");
            String type = reader.readLine();
            if (type.equalsIgnoreCase("R")) {
                sql = "INSERT INTO renters VALUES(" +
                        "'" + name + "', '" + address + "', '" + dob + "', '" + occupation + "', " + sin + ", " + cc_num + ", '" + username + "', '" + pass + "');";
                stmt.executeUpdate(sql);
            } else if (type.equalsIgnoreCase("H")) {
                sql = "INSERT INTO hosts VALUES(" +
                        "'" + name + "', '" + address + "', '" + dob + "', '" + occupation + "', " + sin + ", " + cc_num + ", '" + username + "', '" + pass + "');";
                stmt.executeUpdate(sql);
            }
            System.out.println("Tables have been updated accordingly. Let us proceed.");
            stmt.close();
            conn.close();

            System.out.println("Returning to starting interface");
            startingInterface("S");
        } catch (Exception e) {
            System.out.println("Error occurred while creating new user");
        }
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
            System.out.println("MyBnB2 database has been created");
            stmt.close();
            conn.close();

            System.out.println("Connecting to MyBnB2 database");
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

    public static void populateDatabase() {
        String addon = "?allowLoadLocalInfile=true";
        String baseFileLocation = "C://Users/Roshan Suntharan/Desktop/C43/MyBnB/";
        Connection conn;
        Statement stmt;

        try {
            Class.forName(dbClassName);
            System.out.println("Connecting to MyBnB2 database");
            conn = DriverManager.getConnection(ACTUAL_DATABASE + addon, USER, PASS);
            System.out.println("Connected");

            stmt = conn.createStatement();
            String sql = "LOAD DATA LOCAL INFILE '" + baseFileLocation + "users.csv' into TABLE users " +
                    "FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
            stmt.executeUpdate(sql);
            System.out.println("Populated users table with data");

            sql = "LOAD DATA LOCAL INFILE '" + baseFileLocation + "hosts.csv' into TABLE hosts " +
                    "FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
            stmt.executeUpdate(sql);
            System.out.println("Populated hosts table with data");

            sql = "LOAD DATA LOCAL INFILE '" + baseFileLocation + "renters.csv' into TABLE renters " +
                    "FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
            stmt.executeUpdate(sql);
            System.out.println("Populated renters table with data");

            sql = "LOAD DATA LOCAL INFILE '" + baseFileLocation + "listings.csv' into TABLE listings " +
                    "FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
            stmt.executeUpdate(sql);
            System.out.println("Populated listings table with data");

            /* FOR SOME REASON THIS LOADS UP EMPTY CELLS FOR THE AMENITIES */
            sql = "LOAD DATA LOCAL INFILE '" + baseFileLocation + "amenities.csv' into TABLE amenities " +
                    "FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
            stmt.executeUpdate(sql);
            System.out.println("Populated amenities table with data");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Something went wrong while trying to populate the mybnb2 database");
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(dbClassName);
            Connection conn = DriverManager.getConnection(ACTUAL_DATABASE, USER, PASS);
            return conn;
        } catch (Exception e) {
            System.out.println("Couldn't get a connection");
        }
        return null;
    }
}
