
I. Brief explanation
This Spring Boot application incorporates various software development principles, patterns, and practices, exemplifying:

1/ Model-View-Controller (MVC) Architecture: Embracing the MVC design pattern, the application segregates into Model, View, and Controller components. This division streamlines codebase maintenance, fostering adaptability and scalability.

2/ Object-Relational Mapping (ORM): Leveraging Hibernate, an ORM framework, the app facilitates the translation of Java objects into relational database tables. This abstraction mitigates the intricacies associated with melding object-oriented programming and relational databases.

3/ Dependency Injection (DI): Harnessing Spring Framework's DI capabilities, the application seamlessly injects dependencies into classes. This diminishes inter-component coupling, promoting modular and easily maintainable code.

4/ Repository Pattern: Employing a repository layer, the app abstracts data access logic from the service layer. This segregation of concerns simplifies unit testing and enhances maintainability.

II. Code Structure
Model Package:
Contains entity classes representing the core domain model.

Repository Package:
Hosts interfaces responsible for interacting with the database.

Service Package:
Encompasses business logic and acts as the bridge between controllers and repositories.

Controller Package:
Houses classes that handle user requests and responses, managing views and overall application flow.

![image](https://github.com/LTP6220/MidtermJavaTechnology/assets/116489939/2376c6a6-16da-49da-ab58-98befec89bf7)

III. Step to run on a local machine **Step 1: Set up database (In my case, I use MySQL and MySQL workbench) **
![image](https://github.com/LTP6220/MidtermJavaTechnology/assets/116489939/1cb61315-04a4-4cc1-bb20-5078ee2d78af)

Relational Schema:
![image](https://github.com/LTP6220/MidtermJavaTechnology/assets/116489939/02bbc742-8ac9-4228-a231-d08422c5af5c)

**Step 2: Clone the project from my github and run the Spring boot app (the database need to have some of the product included)**
**Demo App**
https://github.com/LTP6220/MidtermJavaTechnology/assets/116489939/eaec4b86-58d6-4e18-bec5-4a7c1b36135d

