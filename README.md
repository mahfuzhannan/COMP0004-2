# COMP0004-2-Q6
The code for COMP0004 coursework part 2 - Q6

Build code using maven, pom.xml is provided.
Application will require database file, `/patients.db` at root directory, to load data

### Pre-setup
Please run database setup to create database scheme prior to running application found under `/src/main/java/uk/ac/ucl/SetupDatabase.java`
Database file will be created under project root directory `/patients.db`

### To Run application
Please execute main method in `/src/main/java/uk/ac/ucl/Main.java`
Upload CSV or JSON file to populate data, click `Save to Databse`
Restart the application
Click `Load from Database` and previous saved data will be loaded

### Tests
Tests can be found in `/src/test/java/uk/ac/ucl`