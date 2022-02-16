Start Progetto:

1- Fare clone del progetto con il comando:

git clone https://github.com/niccocorsa94/Advanced-Programming.git

2- Nel root del progetto eseguire il comando:

mvn install -DskipTests

3- Selezionare il file src/main/java/operations/backend/OperationBackEnd.java fare edit configuration, selezionare Spring-boot-app e mettere profilo di prova con H2:

spring.profiles.active=h2db;spring.liquibase.enabled=false;spring.jpa.hibernate.ddl-auto=update

Adesso è possibile osservare gli end-point recandosi alla pagina web: http://localhost:8080/operations-backend/swagger-ui.html

Per testare il sistema con il DB engine MySQL ripetere la stessa procedura di prima, ma utilizzando un diverso profilo:

spring.profiles.active=test2;spring.liquibase.enabled=false;spring.jpa.hibernate.ddl-auto=update

Per testare il sistema attraverso l'interfaccia grafica fare:

1- eseguire il comando di pull dal docker-hub

docker pull niccocorsa/front-end-advanced-programming:latest

2- eseguire l'immagine scaricata con map alla porta 80

docker run -d -it -p 80:80/tcp --name angular-app niccocorsa/front-end-advanced-programming:latest

Relation:[Advanced Programming.pdf](https://github.com/niccolocorsani/Advanced/files/8080562/Advanced.Programming.pdf)

