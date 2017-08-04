# UDP Server Implementation in Java
This is a simple implementation of UDP server in Java, implemented with fundamental Java classes, methods and interfaces. And it can be reused to build a more powerful and more functional UDP server(Usually used in back-end).

#### Attention:
This UDP server is linked to a database(a hospital database here, but you can change it to whatever you want), so MySQL is required before you can run the source code correctly.

[Download MySQL](https://www.mysql.com/downloads/)
## Installation
- ### JDK
You need to download JDK first, skip this step if you have downloaded it.

[Download JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- ### Download the project
Download zip file and unzip it or use git to clone the project.
If you choose the latter, run command line as below in Terminal:

`git clone https://github.com/EdisonCat/java-udpserver.git`

- ### Download JDBC(Java Database Connectivity) Driver
To create connection between server(written in Java) and MySQL databases, you have to download a driver called JDBC, it is a .jar file.

[Download JDBC](https://dev.mysql.com/downloads/connector/j/5.1.html)

- ### Open the project in IDEs
Open the project in IntelliJ(highly recommended) or Eclipse. You will find that the project will go wrong if you do not add JDBC as a dependency for it.
Under IntelliJ, open **Project Structure** panel and select **Module** at the side bar, add JDBC driver .jar file as one of its **dependencies**. Do the same with JDK of course.
![Adding JDBC driver in IntelliJ](https://github.com/edisoncat/java-udpserver/raw/master/screenshots/1.png)
- ### Try to run
You can try run the project in your IDE now if you have known how to link to the database.But you may probably get error messages here.

## Usage
- ### Create a MySQL database in Terminal(**this is just an example**)
**If you know how to create MySQL databases and tables and add columns, just skip this step.**

After installing MySQL successfully, configure it through creating an account.And then do as below:

`mysql -uusername -ppassword`

For example, your username is root, your password is 1234, do:

`mysql -uroot -p1234`

After logging in, do:

1. `CREATE DATABASE database_name;`

2. `USE database_name;`

3. 
```
  CREATE TABLE table_name (
  City VARCHAR(4) NOT NULL,
  Hopital VARCHAR(3) NOT NULL,
  Department VARCHAR(2) NOT NULL,
  Bed VARCHAR(3) NOT NULL,
  Amount VARCHAR(3) NOT NULL,
  ID VARCHAR(12) NOT NULL,
  Time VARCHAR(40) NOT NULL,
  UNIQUE (ID));
```
- ### Change database information
There is an **ConstantValues** interface file in **j.socket.udpserver** package,
and all the information you need to provide to create connection with database is stored in it.
So you need to modify the values if the original ones are not consistent with the ones you lately create.

For example, you create an account, username of which is Bob, you need to change the value of String **_DATABASE_USERNAME_** from "root" to "Bob".

If the name of your database is not "test" , replace "test" in String **_DATABASE_URL_** with the new name of your database.

If the name of your table is not "tb2" , replace "tb2" in String **_DATABASE_TABLE_NAME_** with the new name of your table.

Do the same with **_DATABASE_TABLE_NAME_** and **_PORT_**.

Usually, the database you created will be totally different from the original one in this project, so you have to modify **updateDatabase(String info)** and **queryDatabase()** methods in **DataModifier** class to meet your needs.

- ### Read the documentation of the project
The documentation contains all the usages of classes and methods in the project was automatically generated by JavaDoc, and was saved in **documentation** folder.Open **index.html** to read.

## Test the server with UDP test tools
You can now download some UDP test tools to test the server, search 'udp test tool' key word in app stores on your phone or on google. There are so many kind of tools you can download for free.

Set up local and target port number(they should be the same), and ip address of your server.

If your PC and the device you use to test are in the same local area network, then it would be easy to a correct result.

here is mine(the database is updated and the information is printed in cmd):

![Correct result](https://github.com/edisoncat/java-udpserver/raw/master/screenshots/2.png)

#### If you the test tool(or client) is not in the same local area network:
Try to google "port forwarding".

## ENJOY IT!
At this point, a simple UDP server implementation in Java is set up successfully. You can extend its functions and make modifications to meet your needs.ENJOY IT!
