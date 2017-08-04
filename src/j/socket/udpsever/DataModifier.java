package j.socket.udpsever;

import java.sql.*;

/**
 * Created by EdisonCat on 17-7-18.
 */
public class DataModifier implements ConstantValues{

    Connection connection=null;
    Statement statement=null;
    ResultSet resultSet=null;

    /**
     * Constructs a data modifier for getting connected to the database,
     * updating the database, querying the database if necessary, and closing
     * the connection.
     * @param info the string got from the datagram packet sent by a client.
     */
    public DataModifier(String info) {

        getConnection();
        updateDatabase(info);
        queryDatabase();
        closeConnection();
    }

    /**
     * Creates connection to the database.
     */
    public void getConnection(){

        try {

            Class.forName(DRIVER_NAME);
            this.connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
            this.statement = this.connection.createStatement();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.err.println("Error:ClassNotFoundException.");
        } catch (SQLException e) {
            System.err.println("Error:SQLException");
            e.printStackTrace();
        }
    }

    /**
     * Separates the string received into pieces to process and
     * updates the database according to self-made rules.
     * @param info the string got from the datagram packet sent by a client.
     */
    public void updateDatabase(String info){

        String city;
        String hospital;
        String department;
        String bed;
        String amount;
        city=info.substring(0,4);
        hospital=info.substring(4,7);
        department=info.substring(7,9);
        bed=info.substring(9,12);
        amount=info.substring(12);
        info=info.substring(0,12);

        //Update database
        try {

            PreparedStatement preparedStatement = this.connection.prepareStatement(DATABASE_UPDATE_COMMAND);
            java.util.Date date=new java.util.Date();
            preparedStatement.setString(1,city);
            preparedStatement.setString(2,hospital);
            preparedStatement.setString(3,department);
            preparedStatement.setString(4,bed);
            preparedStatement.setString(5,amount);
            preparedStatement.setString(6,info);
            preparedStatement.setString(7,date.toString());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Error:SQLException.");
            e.printStackTrace();
        }
    }

    /**
     * Queries the database and prints all data in cmd.
     */
    public void queryDatabase(){

        try {

            String sql = "select * from "+DATABASE_TABLE_NAME;
            this.resultSet = this.statement.executeQuery(sql);
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println("City"+"\t"+"Hospital"+"\t"+"Department"+"\t"+"Bed"+"\t\t\t"+"Amount"+"\t\t"+"ID"+"\t\t\t\t\t"+"Time");

            while (resultSet.next()){

                System.out.print(resultSet.getString(1) + "     \t");
                System.out.print(resultSet.getString(2) + "     \t");
                System.out.print(resultSet.getString(3) + "     \t");
                System.out.print(resultSet.getString(4) + "     \t");
                System.out.print(resultSet.getString(5) + "     \t");
                System.out.print(resultSet.getString(6) + "     \t");
                System.out.print(resultSet.getString(7) + "     \t");
                System.out.println();

            }
        } catch (SQLException e) {
            System.err.println("Error:SQLException.");
            e.printStackTrace();
        }
    }

    /**
     * Closes connection and statement, and resultset if it exists.
     */
    public void closeConnection(){

        try {
            this.statement.close();
            this.connection.close();
            if(resultSet!=null){
                this.resultSet.close();
            }
        } catch (SQLException e) {
            System.err.println("Error:SQLException.");
            e.printStackTrace();
        }
    }

}