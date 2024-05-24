Promo Code Service Application.
The application allows you to manage promo codes, products, calculate discounts, create purchases and produce reports.

Technology Stack:
	Java 17
	Mockito
	Lombok
	Hibernate
	Spring Data Jpa
	Maven
	SpringBoot
	H2

Installation and running 
1.	https://github.com/SzymonKL99/PromoCodeManager.git
2.	The application uses the H2 database.
3.	In intellij add Data source : H2, connection type : remote
•	URL: jdbc:h2:tcp://localhost/mem:testdb
•	Port: 8080
•	Host: localhost
•	User: sa
•	Password: password
4.	After creating the database and starting the application, in order to pre-populate the database with data - you can use the file: resources/input. Add it to the Query console and approve. 
5.	After start application, using swagger you can test endpoint performance at this link: http://localhost:8080/swagger-ui/index.html





