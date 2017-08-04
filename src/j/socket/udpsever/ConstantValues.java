package j.socket.udpsever;

/**
 * Created by EdisonCat on 17-7-18
 */
interface ConstantValues {

    int PORT = 9898;
    String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
    String DATABASE_URL="jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
    String DATABASE_USERNAME="root";
    String DATABASE_PASSWORD="1234";
    String DATABASE_UPDATE_COMMAND="INSERT INTO tb2 (City,Hospital,Department,Bed,Amount,ID,Time) VALUES(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Amount=VALUES(Amount) ,Time=VALUES(Time) ";
    String DATABASE_TABLE_NAME="tb2";
}
