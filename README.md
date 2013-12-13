pujcovna-stroju
===============

To run the Application:
-------------------------
1) Clean and build the whole project: (cd pujcovna-stroju\pujcovnaStroju)
     mvn clean install

2) Make sure your derby db is available at jdbc:derby://localhost:1527/memory:sample;create=true
           "username: pa165"
            "password: pa165"

3) Run the server part using tomcat container:
     (cd pujcovna-stroju\pujcovnaStroju)
     mvn tomcat:run-war

4) Web app is now available at http://localhost:8080/pa165/


To run the REST client:
-------------------------
1) Go to the directory pujcovnaStroju/pujcovnaStroju_RESTclient  
2) run command mvn exec:java  
3) type 'help' to see available commands  

http://localhost:8080/pa165/rest/
Examples
-------------------
all commands are described in the "help"

to create a user: USER ADD -F mane -l lastname -T ADMINISTRATOR 

to list user user with ID 1: user detail -i 1

dalsie sample prikazy
curl http://localhost:8080/pa165/rest/user/add?firstName=Tomas\&lastName="Jedno"\&type=REVISIONER
curl http://localhost:8080/pa165/rest/user/list
curl http://localhost:8080/pa165/rest/user/detail?id=1
curl http://localhost:8080/pa165/rest/user/update?id=1\&type=CUSTOMERLEGAL\&firstName=Tomas2\&lastName=Jedno2
curl http://localhost:8080/pa165/rest/user/delete?id=1
