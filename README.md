# MyBnB2

Hello!
Welcome to my failed CSCC43 project!

The following constitutes as the user manual.
In order to run the follow code, you'll need to perform a few steps.

Apparently, MySql is weird when it comes to loading data from your local machine because of security breaches and such.
Due to this, you'll need to do the following first:

Run your CMD terminal with administrator permissions.
Go to wherever your MySql extracted folder and move into the /bin folder.

Then, perform the command "mysql -u root -p --local-infile=1" and enter your password accordingly.
(Though I'm not entirely sure if the --local-infile=1 flag is entirely needed at all for this part)

Then, do "SET GLOBAL local_infile=1;"
Also, do "SHOW VARIABLES LIKE 'local_infile';" and confirm that the value says "ON";
If done properly, now you should be able to load data locally from your machine... at least I hope.

Next, you'll have to go to the Main.java file and adjust the password as accordingly set.
I couldn't set mine to "" as the database was being weird so I created a random one as you'll see.

Assuming the database link acts accordingly with the settings that you downloaded it with (port 3306 on localhost and all), perform the following:

In the "populateDatabase" method, you'll have to update the "baseFileLocation" variable.
By this, I mean you'll have to change the path location to where you have stored all the csv files found on the repo.

Once this is done, you should be able to run the project either on the IDE by simply running the main method in Main.java....
OR
You can use your terminal, cd to the directory in which the pom.xml file is found and do the following:
mvn clean,
mvn compile,
mvn exec:java -Dexec.mainClass=Main

This cleans and newly compiles the Maven project and runs the main function in the Main.java class. Thank you!