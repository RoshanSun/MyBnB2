import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader readerbf = new BufferedReader(new InputStreamReader(System.in));
        Scanner reader = new Scanner(System.in);
        int input;

        // Starting section of interface
        if(call.equalsIgnoreCase("S")) {
            System.out.println("Starting Interface");
            System.out.print("Choose an option: 1.Login, 2.Create User, 3.Delete User, 4.Reports Section: ");
            input = reader.nextInt();
            if(input == 1) {
                System.out.println("Login has been chosen");
                login();
            } else if (input == 2) {
                System.out.println("Creating user....");
                createUser();
            } else if (input == 3) {
                System.out.println("Deleting user....");
                deleteUser();
            } else if (input == 4) {
                System.out.println("Entering Reports Section");
                System.out.println("Choose one of the following reports to see: ");
                System.out.println("1. Total Number of Bookings in Specific Date Range By City");
                System.out.println("2. Total Number of Bookings in Specific Date Range By Zip Code in City");
                System.out.println("3. Total Number of Listings Per Country");
                System.out.println("4. Total Number of Listings Per Country, City");
                System.out.println("5. Total Number of Listings Per Country, City, Postal Code");
                System.out.println("6. Rank Hosts by Total Number of Listings Overall Per Country");
                System.out.println("7. Rank Hosts by Total Number of Listings Overall Per City");
                System.out.println("8. Provide Hosts with >10% listings in each City, Country");
                System.out.println("9. Rank Renters by Number of Bookings in Specific Time Period");
                System.out.println("10. Rank Renters by Number of Bookings in Specific Time Period, City (>=2 in year)");
                System.out.println("11. Hosts with Most Cancellations in a Year");
                System.out.println("12. Renters with Most Cancellations in a Year");
                System.out.println("13. Most Popular Noun Phrases Associated with each Listing");
                input = reader.nextInt();
                if (input == 1) {
                    try {
                        System.out.print("Choose a start date (YYYY-MM-dd): ");
                        Date start = Date.valueOf(readerbf.readLine());
                        System.out.print("Choose an end date (YYYY-MM-dd): ");
                        Date end = Date.valueOf(readerbf.readLine());

                        System.out.println("Creating Report 1");
                        report1(start, end);
                        startingInterface("S");
                    } catch (Exception e) {
                        System.out.println("Error occurred while retrieving dates.");
                    }
                } else if (input == 2) {
                    try {
                        System.out.print("Choose a start date (YYYY-MM-dd): ");
                        Date start = Date.valueOf(readerbf.readLine());
                        System.out.print("Choose an end date (YYYY-MM-dd): ");
                        Date end = Date.valueOf(readerbf.readLine());

                        System.out.println("Creating Report 2");
                        report2(start, end);
                        startingInterface("S");
                    } catch (Exception e) {
                        System.out.println("Error occurred while retrieving dates.");
                    }
                } else if (input == 3) {
                    System.out.println("Creating Report 3");
                    report3();
                    startingInterface("S");
                } else if (input == 4) {
                    System.out.println("Creating Report 4");
                    report4();
                    startingInterface("S");
                } else if (input == 5) {
                    System.out.println("Creating Report 5");
                    report5();
                    startingInterface("S");
                } else if (input == 6) {
                    System.out.println("Creating Report 6");
                    report6();
                    startingInterface("S");
                } else if (input == 7) {
                    System.out.println("Creating Report 7");
                    report7();
                    startingInterface("S");
                } else if (input == 8) {
                    System.out.println("Creating Report 8");
                    report8();
                    startingInterface("S");
                } else if (input == 9) {
                    try {
                        System.out.print("Choose a start date (YYYY-MM-dd): ");
                        Date start = Date.valueOf(readerbf.readLine());
                        System.out.print("Choose an end date (YYYY-MM-dd): ");
                        Date end = Date.valueOf(readerbf.readLine());

                        System.out.println("Creating Report 9");
                        report9(start, end);
                        startingInterface("S");
                    } catch (Exception e) {
                        System.out.println("Error occurred while retrieving dates.");
                    }
                } else if (input == 10) {
                    try {
                        System.out.print("Choose a start date (YYYY-MM-dd): ");
                        Date start = Date.valueOf(readerbf.readLine());
                        System.out.print("Choose an end date (YYYY-MM-dd): ");
                        Date end = Date.valueOf(readerbf.readLine());

                        System.out.println("Creating Report 10");
                        report10(start, end);
                        startingInterface("S");
                    } catch (Exception e) {
                        System.out.println("Error occurred while retrieving dates.");
                    }
                } else if (input == 11) {
                    System.out.println("Creating Report 11");
                    report11();
                    startingInterface("S");
                } else if (input == 12) {
                    System.out.println("Creating Report 12");
                    report12();
                    startingInterface("S");
                } else if (input == 13) {
                    System.out.println("NOT IMPLEMENTED");
                }
            } else {
                System.out.println("Invalid choice");
                startingInterface("S");
            }
        } else if (call.equalsIgnoreCase("R")) {
            System.out.println("Renting Interface");
            System.out.print("Choose an option: 1.Book Listing, 2.Cancel Booking, 3.Leave a comment, 4.Leave a Rating, 5.Return to Home Page: ");
            input = reader.nextInt();

            if(input == 1) {
                System.out.println("Searching for listings to book...");
                searchForListings();
            } else if (input == 2) {
                System.out.println("Cancelling booking...");
                cancelBookingAsRenter();
            } else if (input == 3) {
                System.out.println("Getting ready to leave a comment...");
                leaveComment();
            } else if (input == 4) {
                System.out.println("Getting ready to leave a rating...");
                leaveRating();
            } else if (input == 5) {
                System.out.println("Returning to Home Page");
                startingInterface("S");
            } else {
                System.out.println("Invalid choice");
                startingInterface("R");
            }
        } else if (call.equalsIgnoreCase("H")) {
            System.out.println("Hosting Interface");
            System.out.print("Choose an option: 1.Create Listing, 2.Delete Listing, 3.Adjust Listing, 4.Cancel Booking, 5.Return to Home Page: ");
            input = reader.nextInt();

            if(input == 1) {
                System.out.println("Creating listing...");
                createListing();
            } else if (input == 2) {
                System.out.println("Deleting listing...");
                deleteListing();
            } else if (input == 3) {
                System.out.println("NOT IMPLEMENTED");
            } else if (input == 4) {
                System.out.println("Cancelling booking...");
                cancelBookingAsHost();
            } else if (input == 5) {
                System.out.println("Returning to Home Page");
                startingInterface("S");
            } else {
                System.out.println("Invalid Choice");
                startingInterface("H");
            }
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

                //System.out.println("Invalid user/pass");
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

                //System.out.println("Invalid user/pass");
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

    public static void deleteUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt = null;

        try {
            System.out.print("Enter your username to delete your account: ");
            String user = reader.readLine();
            System.out.print("Enter your password to confirm deletion: ");
            String pass = reader.readLine();
            String sql = "DELETE FROM users WHERE username = '" + user + "' AND pass = '" + pass + "';";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM renters WHERE username = '" + user + "' AND pass = '" + pass + "';";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM hosts WHERE username = '" + user + "' AND pass = '" + pass + "';";
            stmt.executeUpdate(sql);

            System.out.println("The user " + user + " has been deleted.");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while trying to delete a user");
        }
    }

    public static void createListing() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt = null;

        try {
            System.out.print("Please enter your SIN number as on profile: ");
            int sin = Integer.parseInt(reader.readLine());
            System.out.print("Enter the listing type (Apartment, House, Townhouse, Villa, Tent, " +
                    "Condominium, Bungalow, Cottage, Loft, Lighthouse, " +
                    "Dormitory, Castle, Boat, RV, Other): ");
            String type = reader.readLine();
            System.out.print("Enter the latitude of the location: ");
            float latitude = Float.parseFloat(reader.readLine());
            System.out.print("Enter the longitude of the location: ");
            float longitude = Float.parseFloat(reader.readLine());
            System.out.print("Enter the listing address: ");
            String address = reader.readLine();
            System.out.print("Enter the listing city: ");
            String city = reader.readLine();
            System.out.print("Enter the listing country: ");
            String country = reader.readLine();
            System.out.print("Enter the listing's postal code: ");
            String postal_code = reader.readLine();
            System.out.print("Enter all amenities the listing offers (separated by commas): ");
            String amenities = reader.readLine();

            System.out.println("Attempting to insert user record into table");
            stmt = conn.createStatement();
            String sql = "INSERT INTO listings VALUES(" +
                    "" + sin + ", " + 0 + ", '" + type + "', " + latitude + ", " + longitude + ", '" + address + "', '" + city + "', '" + country + "', '" + postal_code + "', '" + amenities + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            // Get the generated listID for this listing
            sql = "SELECT list_id FROM listings WHERE user_id = " + sin + " AND latitude = " + latitude + " AND longitude = " + longitude + ";";
            ResultSet res = stmt.executeQuery(sql);
            int listID = 0;

            while(res.next()) {
                listID = res.getInt("list_id");
            }

            /* IMPLEMENT AVAILABILITY DATES */
            System.out.print("Would you like to add an amenity (Y/N)?: ");
            String input;
            String answer;
            while((input = reader.readLine()).equals("Y")){
                System.out.print("Enter one of the following amenities (Kitchen, Internet, TV, Essentials, Heating, " +
                        "Air Conditioning, Washer, Dryer, Free Parking, Wireless, " +
                        "Breakfast, Pets, Family Friendly, Suitable for Events, " +
                        "Smoking, Wheelchair Accessible, Elevator, Fireplace, Buzzer, " +
                        "Doorman, Pool, Hot Tub, Gym, 24 Hours Check-In, Hangers, " +
                        "Iron, Hair Dryer, Laptop-friendly Workspace, " +
                        "Carbon Monoxide Detector, First Aid Kit, Smoke Detector): ");
                answer = reader.readLine();

                sql = "INSERT INTO amenities VALUES(" + listID + ", '" + answer + "');";
                stmt.executeUpdate(sql);
                System.out.println("Amenity has been added");
                System.out.print("Would you like to add another amenity (Y/N)?: ");
            }

            System.out.print("Enter the start date for availability (YYYY-MM-DD): ");
            Date av_start = Date.valueOf(reader.readLine());
            System.out.print("Enter the end date for availability (YYYY-MM-DD): ");
            Date av_end = Date.valueOf(reader.readLine());

            float recPrice = hostToolkit(listID);
            System.out.println("Here at MyBnB, we recommend you charge: " + recPrice + " for your listing.");

            System.out.print("Enter the price to book for a single day: ");
            float price = Float.parseFloat(reader.readLine());
            sql = "INSERT INTO availability VALUES (" + listID + ", '" + av_start + "', '" + av_end + "', " + price + ");";
            stmt.executeUpdate(sql);
            System.out.println("Availability created");

            stmt.close();
            conn.close();

            System.out.print("The listing has been created! Write 'S' to go to home page, or 'H' to enter Host Menu: ");
            String reply = reader.readLine();
            startingInterface(reply);
        } catch (IOException e) {
            System.out.println("Something went wrong while getting input to create listing");
        } catch (SQLException e) {
            System.out.println("Something went wrong while trying to create Listing record");
        }
    }

    public static void deleteListing() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt = null;

        try {
            System.out.print("Enter your SIN number as on profile: ");
            int sin = Integer.parseInt(reader.readLine());
            System.out.print("Enter the latitude of the listing to delete: ");
            float latitude = Float.parseFloat(reader.readLine());
            System.out.print("Enter the longitutde of the listing to delete: ");
            float longitude = Float.parseFloat(reader.readLine());

            String sql = "DELETE FROM listings WHERE user_id = " + sin + " AND latitude = " + latitude + " AND longitude = " + longitude + ";";
            stmt.executeUpdate(sql);

            System.out.println("The listing owned by " + sin + " at latitude " + latitude + " and longitude " + longitude + " has been deleted.");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while trying to delete listing");
        }

        // CREATE A QUERY TO RETURN ALL LISTINGS THAT THIS HOST HAS?
        // LET THEM CHOOSE WHICH ONE TO DELETE
    }

    public static void bookListing(int sin, float latitude, float longitude) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT list_id FROM listings WHERE latitude = " + latitude + " AND longitude = " + longitude + ";";
            ResultSet rs = stmt.executeQuery(sql);
            int listID = 0;

            while(rs.next()) {
                listID = rs.getInt("list_id");
            }

            System.out.print("Enter the start date of the booking: ");
            Date startDate = Date.valueOf(reader.readLine());
            System.out.print("Enter the end date of the booking: ");
            Date endDate = Date.valueOf(reader.readLine());

            sql = "SELECT av_id FROM availabilities WHERE listing_id = " + listID + " AND av_start <= '" + startDate + "' AND av_end >= '" + endDate + "';";
            rs = stmt.executeQuery(sql);
            int avID = 0;

            while (rs.next()) {
                avID = rs.getInt("av_id");
            }

            sql = "INSERT INTO bookings VALUES(0, " + avID + ", " + sin + ", '" + startDate + "', '" + endDate + "', 'Available');";
            stmt.executeUpdate(sql);

            System.out.println("Your booking has been finalized.");
            System.out.println("Returning to renter interface");
            startingInterface("R");
        } catch (Exception e) {
            System.out.println("Error occurred while booking listing");
        }
    }

    public static void cancelBookingAsRenter() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Please enter your SIN to confirm booking cancellation: ");
            int renterID = Integer.parseInt(reader.readLine());
            System.out.print("Enter the start date of your booking: ");
            Date start = Date.valueOf(reader.readLine());
            System.out.print("Enter the end date of your booking: ");
            Date end = Date.valueOf(reader.readLine());

            stmt = conn.createStatement();
            String sql = "UPDATE bookings SET status = 'Renter Cancelled' WHERE renterID = " + renterID + " AND start_date = '" + start + "' AND end_date = '" + end + "';";
            stmt.executeUpdate(sql);

            System.out.println("Your booking has been cancelled");
            System.out.println("Returning to renter interface");
            startingInterface("R");
        } catch (Exception e) {
            System.out.println("Error occurred while trying to cancel booking");
        }
    }

    public static void cancelBookingAsHost() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Please enter the renter's ID to confirm booking cancellation: ");
            int renterID = Integer.parseInt(reader.readLine());
            System.out.print("Enter the start date of your booking: ");
            Date start = Date.valueOf(reader.readLine());
            System.out.print("Enter the end date of your booking: ");
            Date end = Date.valueOf(reader.readLine());

            stmt = conn.createStatement();
            String sql = "UPDATE bookings SET status = 'Host Cancelled' WHERE renterID = " + renterID + " AND start_date = '" + start + "' AND end_date = '" + end + "';";
            stmt.executeUpdate(sql);

            System.out.println("This booking has been cancelled");
            System.out.println("Returning to hot interface");
            startingInterface("H");
        } catch (Exception e) {
            System.out.println("Error occurred while trying to cancel booking");
        }
    }

    public static void leaveComment() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Do you want to leave a comment on another User(U) or a Listing(L)?: ");
            String choice = reader.readLine();

            if(choice.equalsIgnoreCase("U")) {
                System.out.println("Preparing comment for a user...");
                leaveProfileComment();
            } else if(choice.equalsIgnoreCase("L")) {
                System.out.println("Preparing comment for a listing...");
                leaveListingComment();
            } else {
                System.out.println("Invalid choice, returning to renter's interface");
                startingInterface("R");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while leaving comment");
        }
    }

    public static void leaveListingComment() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Enter the id of the listing you'd like to leave a comment for: ");
            int listingID = Integer.parseInt(reader.readLine());
            System.out.print("Enter your id/SIN number: ");
            int commenterID = Integer.parseInt(reader.readLine());
            System.out.print("Now, write the comment you'd like to leave on the listing: ");
            String content = reader.readLine();

            stmt = conn.createStatement();
            String sql = "INSERT INTO listing_comments VALUES(" + listingID + ", " + commenterID + ", '" + content + "');";
            stmt.executeUpdate(sql);

            System.out.println("Thank you for your feedback. Your comment has been recorded.");
            stmt.close();
            conn.close();

            System.out.println("Returning to renter interface");
            startingInterface("R");
        } catch (Exception e) {
            System.out.println("Error occurred while trying to leave a comment");
        }
    }

    public static void leaveProfileComment() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Enter the id of the user you'd like to leave a comment for: ");
            int userID = Integer.parseInt(reader.readLine());
            System.out.print("Enter your id/SIN number: ");
            int commenterID = Integer.parseInt(reader.readLine());
            System.out.print("Now, write the comment you'd like to leave on their profile: ");
            String content = reader.readLine();

            stmt = conn.createStatement();
            String sql = "INSERT INTO profile_comments VALUES(" + userID + ", " + commenterID + ", '" + content + "');";
            stmt.executeUpdate(sql);

            System.out.println("Thank you for your feedback. Your comment has been recorded.");
            stmt.close();
            conn.close();

            System.out.println("Returning to renter interface");
            startingInterface("R");
        } catch (Exception e) {
            System.out.println("Error occurred while trying to leave a comment");
        }
    }

    public static void leaveRating() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Do you want to leave a rating on another User(U) or a Listing(L)?: ");
            String choice = reader.readLine();

            if(choice.equalsIgnoreCase("U")) {
                System.out.println("Preparing rating for a user...");
                leaveProfileRating();
            } else if(choice.equalsIgnoreCase("L")) {
                System.out.println("Preparing rating for a listing...");
                leaveListingRating();
            } else {
                System.out.println("Invalid choice, returning to renter's interface");
                startingInterface("R");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while leaving rating");
        }
    }

    public static void leaveListingRating() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Enter the id of the listing you'd like to leave a rating for: ");
            int listingID = Integer.parseInt(reader.readLine());
            System.out.print("Enter your id/SIN number: ");
            int raterID = Integer.parseInt(reader.readLine());
            System.out.print("Now, choose the rating you'd like to leave on the listing (1-5): ");
            int rating = Integer.parseInt(reader.readLine());

            stmt = conn.createStatement();
            String sql = "INSERT INTO listing_ratings VALUES(" + listingID + ", " + raterID + ", " + rating + ");";
            stmt.executeUpdate(sql);

            System.out.println("Thank you for your feedback. Your rating has been recorded.");
            stmt.close();
            conn.close();

            System.out.println("Returning to renter interface");
            startingInterface("R");
        } catch (Exception e) {
            System.out.println("Error occurred while trying to leave a rating");
        }
    }

    public static void leaveProfileRating() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            System.out.print("Enter the id of the user you'd like to leave a rating for: ");
            int userID = Integer.parseInt(reader.readLine());
            System.out.print("Enter your id/SIN number: ");
            int raterID = Integer.parseInt(reader.readLine());
            System.out.print("Now, choose the rating you'd like to leave on the user's profile (1-5): ");
            int rating = Integer.parseInt(reader.readLine());

            stmt = conn.createStatement();
            String sql = "INSERT INTO profile_ratings VALUES(" + userID + ", " + raterID + ", '" + rating + "');";
            stmt.executeUpdate(sql);

            System.out.println("Thank you for your feedback. Your rating has been recorded.");
            stmt.close();
            conn.close();

            System.out.println("Returning to renter interface");
            startingInterface("R");
        } catch (Exception e) {
            System.out.println("Error occurred while trying to leave a rating");
        }
    }

    public static float hostToolkit(int listingID) {
        Connection conn = getConnection();
        Statement stmt;

        float basePrice = 50.0f;
        float baseAmenityPrice = 25.0f;
        int numAmenities = 0;

        try {
            System.out.println("Calculating potential price for listing...");
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) as num_amenities FROM listings l JOIN amenities a ON (l.list_id = a.listing_id) WHERE l.list_id = " + listingID + ";";
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                numAmenities = res.getInt("num_amenities");
            }

            float recPrice = (basePrice + (baseAmenityPrice * numAmenities));
            return recPrice;

        } catch (Exception e) {
            System.out.println("Error while calculating price for listing");
        }
        return 0;
    }

    /***********************
     *  SEARCHING QUERIES  *
     ***********************/
    public static void searchForListings() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter your SIN number to begin booking: ");
            int sin = Integer.parseInt(reader.readLine());

            System.out.println("Let's begin booking");
            System.out.print("Choose a filter to search by: 1.Location, 2.Price, 3.Postal Code, 4.Address 5.Return to Home Menu: ");
            int ans = Integer.parseInt(reader.readLine());
            if (ans == 1) {
                System.out.print("Enter the latitude you desire (-90 to 90): ");
                float latitude = Float.parseFloat(reader.readLine());
                System.out.print("Enter the longitude you desire (-180 to 180): ");
                float longitude = Float.parseFloat(reader.readLine());
                float distance = 0.1f;
                System.out.print("Would you like to enter a distance to consider (Y/N)?: ");
                String response = reader.readLine();
                if(response.equals("Y")) {
                    System.out.print("Enter a distance to consider: ");
                    distance = Float.parseFloat(reader.readLine());
                }
                System.out.println("Attempting to filter by Latitude and Longitude (default distance: 0.1)");
                searchForListingsByLocation(sin, latitude, longitude, distance);
            } else if (ans == 2) {
                System.out.print("Enter a rental price you are considering: ");
                float price = Float.parseFloat(reader.readLine());
                float offset = 50f;
                System.out.print("Would you like to enter a price offset to consider (Y/N)?: ");
                String response = reader.readLine();
                if(response.equals("Y")) {
                    System.out.print("Enter the offset to consider: ");
                    offset = Float.parseFloat(reader.readLine());
                }
                System.out.println("Attempting to filter by Price (default price offset: $50)");
                searchForListingsByPrice(sin, price, offset);
            } else if (ans == 3) {
                System.out.print("Enter the postal code in which you are looking for: ");
                String postal_code = reader.readLine();
                System.out.println("Attempting to filter by Postal Code");
                searchForListingsByPostalCode(sin, postal_code);
            } else if (ans == 4) {
                System.out.print("Enter the address at which you are looking for: ");
                String address = reader.readLine();
                System.out.println("Attempting to filter by Address");
                searchForListingsByAddress(sin, address);
            } else if (ans == 5) {
                startingInterface("S");
            } else {
                System.out.println("Invalid choice");
                searchForListings();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while searching");
        }
    }

    /* NOT FULLY IMPLEMENTED - STILL NEED TO BOOK CHOSEN LISTING */
    public static void searchForListingsByLocation(int sin, float latitude, float longitude, float distance) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT l.*, a.listing_price FROM listings l JOIN availabilities a ON (l.list_id = a.listing_id)" +
                    "WHERE (latitude >= (" + latitude + " - " + distance + ") AND latitude <= (" + latitude + " + " + distance + "))" +
                    "AND (longitude >= (" + longitude + " - " + distance + ") AND longitude <= (" + longitude + " + " + distance + "))" +
                    "ORDER BY ABS(latitude - " + latitude + ");";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Type: " + res.getString("type"));
                System.out.println("Latitude: " + res.getFloat("latitude"));
                System.out.println("Longitude: " + res.getFloat("longitude"));
                System.out.println("Address: " + res.getString("listing_address") + ", " + res.getString("listing_city") + ", " +
                        "" + res.getString("listing_country") + " " + res.getString("postal_code"));
                System.out.println("Amenities: " + res.getString("amenities")); // change this to use amenities table when possible
                System.out.println("Daily Price: " + res.getString("listing_price"));
                System.out.println();
            }
            res.close();
            stmt.close();
            conn.close();

            System.out.println();
            System.out.print("Are you interested in any of these listings (Y/N)?: ");
            String response = reader.readLine();
            if(response.equals("Y")) {
                System.out.print("Enter the latitude of the listing: ");
                float uLat = Float.parseFloat(reader.readLine());
                System.out.print("Enter the longitude of the listing: ");
                float uLong = Float.parseFloat(reader.readLine());
                bookListing(sin, uLat, uLong);
            } else {
                System.out.println("Returning to Search Menu");
                searchForListings();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 3");
        }
    }

    /* NOT FULLY IMPLEMENTED - STILL NEED TO BOOK CHOSEN LISTING */
    public static void searchForListingsByPrice(int sin, float price, float offset) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT l.*, a.listing_price FROM listings l JOIN availabilities a ON (l.list_id = a.listing_id)" +
                    "WHERE (listing_price >= (" + price + " - " + offset + ") AND listing_price <= (" + price + " + " + offset + ")" +
                    "ORDER BY listing_price ASC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Type: " + res.getString("type"));
                System.out.println("Latitude: " + res.getFloat("latitude"));
                System.out.println("Longitude: " + res.getFloat("longitude"));
                System.out.println("Address: " + res.getString("listing_address") + ", " + res.getString("listing_city") + ", " +
                        "" + res.getString("listing_country") + " " + res.getString("postal_code"));
                System.out.println("Amenities: " + res.getString("amenities")); // change this to use amenities table when possible
                System.out.println("Daily Price: " + res.getString("listing_price"));
                System.out.println();
            }
            res.close();
            stmt.close();
            conn.close();

            System.out.println();
            System.out.print("Are you interested in any of these listings (Y/N)?: ");
            String response = reader.readLine();
            if(response.equals("Y")) {
                System.out.print("Enter the latitude of the listing: ");
                float uLat = Float.parseFloat(reader.readLine());
                System.out.print("Enter the longitude of the listing: ");
                float uLong = Float.parseFloat(reader.readLine());
                bookListing(sin, uLat, uLong);
            } else {
                System.out.println("Returning to Search Menu");
                searchForListings();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while creating searching for listings by price");
        }
    }

    /* NOT FULLY IMPLEMENTED - STILL NEED TO BOOK CHOSEN LISTING */
    public static void searchForListingsByPostalCode(int sin, String postal_code) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT l.*, a.listing_price FROM listings l JOIN availabilities a ON (l.list_id = a.listing_id)" +
                    "WHERE (postal_code = '" + postal_code + "')" +
                    "ORDER BY listing_price ASC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Type: " + res.getString("type"));
                System.out.println("Latitude: " + res.getFloat("latitude"));
                System.out.println("Longitude: " + res.getFloat("longitude"));
                System.out.println("Address: " + res.getString("listing_address") + ", " + res.getString("listing_city") + ", " +
                        "" + res.getString("listing_country") + " " + res.getString("postal_code"));
                System.out.println("Amenities: " + res.getString("amenities")); // change this to use amenities table when possible
                System.out.println("Daily Price: " + res.getString("listing_price"));
                System.out.println();
            }
            res.close();
            stmt.close();
            conn.close();

            System.out.println();
            System.out.print("Are you interested in any of these listings (Y/N)?: ");
            String response = reader.readLine();
            if(response.equals("Y")) {
                System.out.print("Enter the latitude of the listing: ");
                float uLat = Float.parseFloat(reader.readLine());
                System.out.print("Enter the longitude of the listing: ");
                float uLong = Float.parseFloat(reader.readLine());
                bookListing(sin, uLat, uLong);
            } else {
                System.out.println("Returning to Search Menu");
                searchForListings();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while creating searching for listings by price");
        }
    }

    /* NOT FULLY IMPLEMENTED - STILL NEED TO BOOK CHOSEN LISTING */
    public static void searchForListingsByAddress(int sin, String address) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT l.*, a.listing_price FROM listings l JOIN availabilities a ON (l.list_id = a.listing_id)" +
                    "WHERE (listing_address = '" + address + "')" +
                    "ORDER BY listing_price ASC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Type: " + res.getString("type"));
                System.out.println("Latitude: " + res.getFloat("latitude"));
                System.out.println("Longitude: " + res.getFloat("longitude"));
                System.out.println("Address: " + res.getString("listing_address") + ", " + res.getString("listing_city") + ", " +
                        "" + res.getString("listing_country") + " " + res.getString("postal_code"));
                System.out.println("Amenities: " + res.getString("amenities")); // change this to use amenities table when possible
                System.out.println("Daily Price: " + res.getString("listing_price"));
                System.out.println();
            }
            res.close();
            stmt.close();
            conn.close();

            System.out.println();
            System.out.print("Are you interested in any of these listings (Y/N)?: ");
            String response = reader.readLine();
            if(response.equals("Y")) {
                System.out.print("Enter the latitude of the listing: ");
                float uLat = Float.parseFloat(reader.readLine());
                System.out.print("Enter the longitude of the listing: ");
                float uLong = Float.parseFloat(reader.readLine());
                bookListing(sin, uLat, uLong);
            } else {
                System.out.println("Returning to Search Menu");
                searchForListings();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while creating searching for listings by price");
        }
    }

    /***********************
     *  REPORTING QUERIES  *
     ***********************/
    public static void report1(Date start, Date end) {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) as num_bookings, l.listing_city FROM listings l JOIN availabilities a ON l.list_id = a.listing_id JOIN bookings b ON a.av_id = b.booking_id WHERE (b.start_date >= '" + start + "' AND b.end_date <= '" + end + "') GROUP BY l.listing_city;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("There have been " + res.getInt("num_bookings") + " bookings made in " + res.getString("listing_city") + " between the given dates.");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 1");
        }
    }

    public static void report2(Date start, Date end) {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) as num_bookings, l.postal_code, l.listing_city FROM listings l JOIN availabilities a ON l.list_id = a.listing_id JOIN bookings b ON a.av_id = b.booking_id WHERE (b.start_date >= '" + start + "' AND b.end_date <= '" + end + "') GROUP BY l.postal_code, l.listing_city;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("There have been " + res.getInt("num_bookings") + " bookings made in " + res.getString("postal_code") + ", " + res.getString("listing_city") + " between the given dates.");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 2");
        }
    }

    public static void report3() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT listing_country, COUNT(*) as num_listings FROM listings GROUP BY listing_country;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("There are " + res.getInt("num_listings") + " listings in " + res.getString("listing_country"));
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 3");
        }
    }

    public static void report4() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT listing_city, listing_country, COUNT(*) as num_listings FROM listings GROUP BY listing_city, listing_country;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("There are " + res.getInt("num_listings") + " listings in " + res.getString("listing_city") + ", " + res.getString("listing_country"));
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 3");
        }
    }

    public static void report5() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT listing_city, listing_country, postal_code, COUNT(*) as num_listings FROM listings GROUP BY listing_city, listing_country, postal_code;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("There are " + res.getInt("num_listings") + " listings in " + res.getString("listing_city") + ", " + res.getString("listing_country") + " at " + res.getString("postal_code"));
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 3");
        }
    }

    public static void report6() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT user_id, listing_country, COUNT(*) as num_listings FROM listings GROUP BY user_id, listing_country ORDER BY num_listings DESC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("User " + res.getInt("user_id") + " has " + res.getInt("num_listings") + " listings in " + res.getString("listing_country"));
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 6");
        }
    }

    public static void report7() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT user_id, listing_city, listing_country, COUNT(*) as num_listings FROM listings GROUP BY user_id, listing_city, listing_country ORDER BY num_listings DESC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("User " + res.getInt("user_id") + " has " + res.getInt("num_listings") + " listings in " + res.getString("listing_city") + ", " + res.getString("listing_country"));
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 3");
        }
    }

    public static void report8() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT l2.user_id, l2.listing_city, l2.listing_country FROM ( SELECT listing_city, listing_country, COUNT(*) as total_listings FROM listings GROUP BY listing_city, listing_country) l1 JOIN ( SELECT user_id, listing_city, listing_country, COUNT(*) AS user_listings FROM listings GROUP BY user_id, listing_city, listing_country) l2 USING (listing_city, listing_country) WHERE user_listings > (total_listings * 0.1);";

            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("User " + res.getInt("l2.user_id") + " holds more than 10% of the listings in " + res.getString("listing_city") + ", " + res.getString("listing_country"));
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 8");
        }
    }

    public static void report9(Date start, Date end) {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT renter_id, COUNT(*) as num_bookings FROM listings l JOIN availabilities a ON l.list_id = a.listing_id JOIN bookings b ON a.av_id = b.booking_id WHERE (b.start_date >= '" + start + "' AND b.end_date <= '" + end + "') GROUP BY renter_id ORDER BY num_bookings ASC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Renter with id " + res.getInt("renter_id") + " has made " + res.getInt("num_bookings") + " bookings in the time period");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 9");
        }
    }

    public static void report10(Date start, Date end) {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT renter_id, COUNT(*) as num_bookings, listing_city FROM listings l JOIN availabilities a ON l.list_id = a.listing_id JOIN bookings b ON a.av_id = b.booking_id WHERE (b.start_date >= '" + start + "' AND b.end_date <= '" + end + "') GROUP BY renter_id, listing_city ORDER BY num_bookings ASC;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Renter with id " + res.getInt("renter_id") + " has made " + res.getInt("num_bookings") + " bookings in " + res.getString("listing_city") + " in the time period");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 10");
        }
    }

    public static void report11() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT user_id, MAX(num_cancellations) as cancels FROM (SELECT user_id, COUNT(*) as num_cancellations FROM listings l JOIN availabilities a ON l.list_id = a.listing_id JOIN bookings b ON a.av_id = b.booking_id WHERE (b.start_date >= DATE_SUB(DATE(sysdate()), INTERVAL 1 YEAR) AND b.status='Host Cancelled') GROUP BY user_id) cancellations GROUP BY user_id;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Host with id" + res.getInt("user_id") + " has made " + res.getInt("cancels") + " cancellations in the past year");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 11");
        }
    }

    public static void report12() {
        Connection conn = getConnection();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT renter_id, MAX(num_cancellations) as cancels FROM(SELECT renter_id, COUNT(*) as num_cancellations FROM bookings b WHERE (b.start_date >= DATE_SUB(DATE(sysdate()), INTERVAL 1 YEAR) AND b.status='Renter Cancelled') GROUP BY renter_id) cancellations GROUP BY renter_id;";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println("Renter with id" + res.getInt("renter_id") + " has made " + res.getInt("cancels") + " cancellations in the past year");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong while creating Report 11");
        }
    }

    /* NOT IMPLEMENTED */
    public static void report13() { }

    /*********************************
     *  DATABASE MANAGING FUNCTIONS  *
     *********************************/
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

            sql = "CREATE TABLE IF NOT EXISTS availabilities(" +
                    "av_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "listing_id INT NOT NULL," +
                    "av_start DATE NOT NULL," +
                    "av_end DATE NOT NULL," +
                    "listing_price DECIMAL(10, 2) NOT NULL," +
                    "FOREIGN KEY(listing_id) REFERENCES listings(list_id) ON DELETE CASCADE," +
                    "UNIQUE(listing_id, av_start, av_end)," +
                    "CHECK(listing_price >= 0), CHECK(av_start <= av_end));";
            stmt.executeUpdate(sql);
            System.out.println("Created availabilities table");

            sql = "CREATE TABLE IF NOT EXISTS bookings(" +
                    "booking_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "availability_id INT NOT NULL," +
                    "renter_id INT NOT NULL," +
                    "start_date DATE NOT NULL," +
                    "end_date DATE NOT NULL," +
                    "status ENUM('Available', 'Renter Cancelled', 'Host Cancelled')," +
                    "FOREIGN KEY(availability_id) REFERENCES availabilities(av_id) ON DELETE CASCADE," +
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
        String baseFileLocation = "C://Users/Roshan Suntharan/Desktop/C43/RealOne/MyBnB2/";
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

            /*
            sql = "LOAD DATA LOCAL INFILE '" + baseFileLocation + "availabilities.csv' into TABLE availabilities " +
                    "FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("Populated availabilities table with data");
            */

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
