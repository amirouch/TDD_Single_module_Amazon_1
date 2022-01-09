package configuration.databases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 */

public class ConnectToSqlDB {

    public static Connection connect = null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet resultSet = null;

    public static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        //InputStream ism = new FileInputStream("/secret.properties");
       // InputStream ism = new FileInputStream("../Generic/src/main/secret.properties");
       // InputStream ism = new FileInputStream("../afterOOP/configPropertyDataBase/secret.properties");
       //InputStream ism = new FileInputStream("C:\\Users\\USER\\IdeaProjects\\TDD_Single_module_Amazon_1\\src\\main\\java\\configuration\\databases\\secret.properties");//**********
       InputStream ism = new FileInputStream("../TDD_Single_module_Amazon_1/src/main/java/configuration/databases/secret.properties");//**********
        prop.load(ism);
        ism.close();
        return prop;
    }

    public static Connection connectToSqlDatabase() throws IOException, SQLException, ClassNotFoundException {
        Properties prop = loadProperties();
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String userName = prop.getProperty("MYSQLJDBC.userName");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url,userName,password);
        System.out.println("Database is connected");
        return connect;
    }

    public List<String> readDataBase(String tableName, String columnName)throws Exception {
        List<String> data = new ArrayList<String>();

        try {
            connectToSqlDatabase();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from " + tableName);
            data = getResultSetData(resultSet, columnName);
        } catch (ClassNotFoundException e) {
            throw e;
        }finally{
            close();
        }
        return data;
    }

    private void close() {
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connect != null){
                connect.close();
            }
        }catch(Exception e){

        }
    }

    private List<String> getResultSetData(ResultSet resultSet2, String columnName) throws SQLException {
        List<String> dataList = new ArrayList<String>();
        while(resultSet.next()){
            String itemName = resultSet.getString(columnName);
            dataList.add(itemName);
        }
        return dataList;
    }

    public void insertDataFromArrayToSqlTable(int [] ArrayData, String tableName, String columnName)
    {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `"+tableName+"`;");
            ps.executeUpdate();
            //ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` (`ID` int(11) NOT NULL AUTO_INCREMENT,`SortingNumbers` bigint(20) DEFAULT NULL,  PRIMARY KEY (`ID`),"+columnName+" int(50) );");
            ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` ("+columnName+" int(50) );");
            ps.executeUpdate();
            for(int n=0; n<ArrayData.length; n++){
                ps = connect.prepareStatement("INSERT INTO "+tableName+" ( "+columnName+" ) VALUES(?)");
                ps.setInt(1,ArrayData[n]);
                ps.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertDataFromStringToSqlTable(String ArrayData, String tableName, String columnName)
    {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("INSERT INTO "+tableName+" ( "+columnName+" ) VALUES(?)");
            ps.setString(1,ArrayData);
            ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> directDatabaseQueryExecute(String passQuery, String dataColumn)throws Exception {
        List<String> data = new ArrayList<String>();

        try {
            connectToSqlDatabase();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(passQuery);
            data = getResultSetData(resultSet, dataColumn);
        } catch (ClassNotFoundException e) {
            throw e;
        }finally{
            close();
        }
        return data;
    }

    /*public void insertDataFromArrayListToSqlTable(List<Student> list, String tableName, String columnName)
    {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `"+tableName+"`;");
            ps.executeUpdate();
            //ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` (`ID` int(11) NOT NULL AUTO_INCREMENT,`SortingNumbers` bigint(20) DEFAULT NULL,  PRIMARY KEY (`ID`) );");
            ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` ("+columnName+" int(50) );");
            ps.executeUpdate();
            for(Student st:list){
                ps = connect.prepareStatement("INSERT INTO "+tableName+" ( "+columnName+" ) VALUES(?)");
                ps.setObject(1,st);
                ps.executeUpdate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    public void insertDataFromArrayListToSqlTable(List<String> list, String tableName, String columnName)
    {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `"+tableName+"`;");
            ps.executeUpdate();
            //ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` (`ID` int(11) NOT NULL AUTO_INCREMENT,`SortingNumbers` bigint(20) DEFAULT NULL,  PRIMARY KEY (`ID`) );");
            ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` ("+columnName+" varchar (100) );");
            ps.executeUpdate();
            for(Object st:list){
                ps = connect.prepareStatement("INSERT INTO "+tableName+" ( "+columnName+" ) VALUES(?)");
                ps.setObject(1,st);
                ps.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void insertProfileToSqlTable(String tableName, String columnName1, String columnName2)
    {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("INSERT INTO "+tableName+" ( " + columnName1 + "," + columnName2 + " ) VALUES(?,?)");
            ps.setString(1,"Ankita Sing");
            ps.setInt(2,3590);
            ps.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<User> readUserProfileFromSqlTable()throws IOException, SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        User user = null;
        try{
            Connection conn = connectToSqlDatabase();
            String query = "SELECT * FROM Students";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            // iterate through the java resultset
            while (rs.next())
            {
                String name = rs.getString("stName");
                String id = rs.getString("stID");
                String dob = rs.getString("stDOB");
                //System.out.format("%s, %s\n", name, id);
                user = new User(name,id, dob);
                list.add(user);

            }
            st.close();
        }catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args)throws IOException, SQLException, ClassNotFoundException {
        List<User> list = readUserProfileFromSqlTable();
        for(User user:list){
            System.out.println(user.getStName() + " " + user.getStID()+ " " + user.getStDOB());
        }
    }


    //    public static Connection connection = null;
//    public static Statement statement = null;
//    public static PreparedStatement preparedStatement = null;
//    public static ResultSet resultSet = null;

//    // Load properties file   ********   1   **********
//    public static Properties loadProperties(String filePath) throws IOException {
//        Properties properties = new Properties();
//        InputStream inputStream = new FileInputStream(filePath);
//        properties.load(inputStream);
//        inputStream.close();
//        return properties;
//    }
//
//    // we used loadProperties(filePath) method in the following method ********   2   **********
//    public static Connection getDatabaseConnection() throws IOException, ClassNotFoundException, SQLException {
//        // String filePath = "../LearnJava_QE_Summer2021/configProperty/Secret.properties";
//       /////// String filePath = "../afterOOP/configPropertyDataBase/secret.properties"; // copie absolut path of secret.properties
//        String filePath = "C:\\Users\\USER\\IdeaProjects\\TDD_Single_module_Amazon_1\\src\\main\\java\\configuration\\databases\\secret.properties"; // copie absolut path of secret.properties
//        Properties properties = loadProperties(filePath);
//        String driverClass = properties.getProperty("MYSQLJDBC.driver");
//        String url = properties.getProperty("MYSQLJDBC.url");
//        String userName = properties.getProperty("MYSQLJDBC.userName");
//        String password = properties.getProperty("MYSQLJDBC.password");
//
//        Class.forName(driverClass);
//        connection = DriverManager.getConnection(url, userName, password);
//        System.out.println("Database is connected");
//        return connection;
//    }
//
//    // ********   3   **********
//    public static List<String> getResultSetData(ResultSet resultSet, String columnName) throws SQLException {
//        List<String> dataList = new ArrayList<>();
//        while (resultSet.next()) {
//            String itemName = resultSet.getString(columnName);
//            dataList.add(itemName);
//        }
//        return dataList;
//    }
//
//    public static void closeDatabaseConnection() {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException throwables) {
//            System.out.println("Connection not Closed");
//        }
//    }
//
//    // ********   4   ********** ===== READ DATA ==== we basecly needed 1, 2, 3 for 4:
//    // we used getDatabaseConnection() and getResultSetData(resultSet, columnName) method in the following method
//    public static List<String> readDataBase(String tableName, String columnName) throws Exception {
//        List<String> data = new ArrayList<>();
//        try {
//            ConnectToSqlDB.getDatabaseConnection(); // will create connection to DB
//            statement = connection.createStatement();
//            String query = "select * from " + tableName;
//            resultSet = statement.executeQuery(query);
//            data = getResultSetData(resultSet, columnName);
//            System.out.println("Data value " + data);
//            for (String dt : data) {
//                System.out.println(dt);
//            }
//        } catch (ClassNotFoundException exc) {
//            System.out.println("Class not Found Exception");
//            throw exc;
//        } finally {
//            closeDatabaseConnection();
//        }
//        return data;
//
//    }


}