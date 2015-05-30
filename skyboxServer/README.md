# Skybox Server
This is the server side code for the Skybox app.

This file will be packaged with your application, when using `activator dist`.

# File structure
- The project is created based on the Java Play framework 2.3.9. The skyboxServer is following the Model View Controller(mvc) pattern. 
- `./conf/routes` is the file manages how the user could use the API supplied by the skyboxServer.
- Java classes in the `com.skybox.controller` package handles the requests from the client side, including:  
  `/skyboxServer/app/com/skybox/controller/NFLplayer.java`  
  `/skyboxServer/app/com/skybox/controller/Userinfo.java`  
- Java classes in the `com.skybox.dbmanager` package handles the actual CRUD operation to the database:  
  `/skyboxServer/app/com/skybox/dbmanager/DBManager.java`  
  `/skyboxServer/app/com/skybox/dbmanager/NFLplayerstatsDBManager.java`  
  `/skyboxServer/app/com/skybox/dbmanager/UserDBManager.java`
- Java classes in the `com.skybox.model` package includes all the data objects we need to use: 
  `/skyboxServer/app/com/skybox/model/NFLplayerstats.java`  
  `/skyboxServer/app/com/skybox/model/User.java`  
- SQL scripts are in the parent directory `SkyboxDB`, it include database table schema, sample data and SQL script to load csv file into the database.


# How to run
- Right now the server is using the AWS RDS to serve as the database, the username and password is included in the source code and you don't have to worry about that, but this might change in the future in the way that we are going to remove the username and password and use access key and secret keys instead.
- You need to download and install the Java Play framework activator(Version 2.3.9) in order to run the server on your local machine since it's not deployed to Amazon AWS cloud service yet.
Just Follow the documentation on the Play framework website, it will be really easy to run the server:
https://www.playframework.com/documentation/2.3.x/Home
- I have already converted this project to an Eclipse project, so the best way to edit the contents of this project is Eclipse, of course any normal text editors will do, but it might not be as handy as Eclipse.

# Environment
- The development environment: `Java version 1.8.0_31 + Java Play framework 2.3.9 + MySQL Server 5.6.24`
