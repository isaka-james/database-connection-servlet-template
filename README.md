# NETBEANS SERVLET DATABASE CONNECTION TEMPLATE

## Recall to some basic Knowledge 🚀
JDBC stands for Java Database Connectivity, which is a standard Java API for database
independent connectivity between the Java programming language and a wide range of 
databases. 

The JDBC library includes APIs for each of the tasks mentioned below that are commonly 
associated with database usage. 

- Making a connection to a database. 
- Creating SQL or MySQL statements. 
- Executing SQL or MySQL queries in the database. 
- Viewing & Modifying the resulting records.

## Relationship between Java Code, JDBC API, and MySQL JDBC Driver
- Java code interacts with the JDBC API to communicate with databases.
- The JDBC API serves as an intermediary layer between Java code and database drivers, providing a standardized interface for database access.
- When using a MySQL database, developers include the MySQL JDBC driver in their Java project to enable JDBC operations specific to MySQL.
- Java code utilizes JDBC API methods to invoke functionalities provided by the MySQL JDBC driver, such as establishing connections, executing SQL statements, and retrieving results.
- The MySQL JDBC driver implements the JDBC API specifications and provides MySQL-specific implementations of JDBC interfaces and classes, allowing Java applications to seamlessly interact with MySQL databases.

## THE DIAGRAM SHOWING THE RELATIONSHIP BETWEEN JAVA CODE, JDBC API AND MYSQL DATABASE DRIVER.
![image](https://github.com/reprogamaco/database-connection-servlet-template/assets/76619967/334c1123-6cb3-4882-9a00-0a396479792f)


## Programs used
- [x] Apache NetBeans IDE 20: This integrated development environment provides a user-friendly platform for Java development, offering features such as code editing, debugging, and project management. Apache NetBeans simplifies the process of developing Java applications, including those that interact with databases using JDBC.
- [x] Apache Tomcat 10.1.18: Tomcat is a widely used web server and servlet container that supports Java servlets and JavaServer Pages (JSP). It's crucial for deploying Java web applications, including those that utilize JDBC for database connectivity. Tomcat serves as the runtime environment where JDBC-enabled Java applications can be executed.
- [x] MySQL Connector/J 8.3.0: This is the official JDBC driver for MySQL databases, provided by Oracle. The connector allows Java applications to communicate with MySQL databases using the JDBC API. By including the MySQL Connector/J library in your project's classpath, you enable JDBC to establish connections to MySQL databases and perform database operations.
- [x] Operating System Compatibility (Linux version 6.6.9-amd64): It's important to ensure that your operating system supports the components mentioned above. In this case, Linux version 6.6.9-amd64 is specified as the operating system running on the system where JDBC and the DBMS will be utilized. It's essential to verify that the chosen operating system is compatible with the selected versions of Apache NetBeans, Apache Tomcat, and MySQL Connector/J.

## Core Logic
```java
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Step 1: Loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establishing connection
            String url = "jdbc:mysql://localhost:3306";
            String username = "root";
            String password = "master123";
            connection = DriverManager.getConnection(url, username, password);
            out.println("Connected to MySQL server successfully!<br>");

            // Step 3: Creating database if not exists
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS StudentData";
            preparedStatement = connection.prepareStatement(createDatabaseQuery);
            preparedStatement.executeUpdate();
            out.println("StudentData database created successfully!<br>");

            // Step 4: Switch to the StudentData database
            String useDatabaseQuery = "USE StudentData";
            preparedStatement = connection.prepareStatement(useDatabaseQuery);
            preparedStatement.executeUpdate();

            // Step 5: Creating a PreparedStatement
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Student (id INT PRIMARY KEY, name VARCHAR(50))";
            preparedStatement = connection.prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
            out.println("Student table created successfully!<br>");

            // Step 6: Execute PreparedStatement
            preparedStatement = connection.prepareStatement("INSERT INTO Student (id, name) VALUES (?, ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "John");
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                out.println("Data inserted successfully!<br>");
            } else {
                out.println("Failed to insert data!<br>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(out); // Print exception details to the browser
        } finally {
            // Step 7: Closing connection and resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(out); // Print exception details to the browser
            }
        }
```

### PROJECT AS SEEN IN NETBEANS
![image](https://github.com/reprogamaco/database-connection-servlet-template/assets/76619967/309c1e41-97f6-4ff9-a066-6b2fed2e8adb)

### PROJECT RUN IN BROWSER
![image](https://github.com/reprogamaco/database-connection-servlet-template/assets/76619967/e93c5546-97ad-4483-a134-63cf01033abc)


### ON MARIADB SHOWING DATA ARE SUCCESSFULLY INSERTED
![image](https://github.com/reprogamaco/database-connection-servlet-template/assets/76619967/46372041-1952-47fe-b3a8-49f0cbfec453)

## NETBEANS SHOWING THE DATA ARE INSERTED SUCCESSFULLY
![image](https://github.com/reprogamaco/database-connection-servlet-template/assets/76619967/a2cb1cd5-3cc8-476f-b752-595e7176c711)

### LICENSE
MIT
