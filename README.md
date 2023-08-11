# Thakgalo Molefe Phaahla Project Management System (Spring boot & Angular 9 )

[![N|Solid](https://i55.servimg.com/u/f55/13/79/70/03/main10.png)](https://i55.servimg.com/u/f55/13/79/70/03/main10.png)



### Technology Stack i use on this project
Component         | Technology
---               | ---
Frontend          | [Angular 9](https://github.com/angular/angular)
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
Security          | Token Based (Spring Security and [JWT](https://github.com/auth0/java-jwt) )
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox)
DataBase      | [PostgreSQL 9.4](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) 
Persistence       | JPA (Using Spring Data)
Client Build Tools| [angular-cli](https://github.com/angular/angular-cli), Webpack, npm
Client Test| [Postman](https://www.postman.com/)/[Insomnia](https://insomnia.rest/)
Server Build Tools| Maven(Java) or Gradle

# Folder Structure

[![N|Solid](https://i55.servimg.com/u/f55/13/79/70/03/011.png)](https://i55.servimg.com/u/f55/13/79/70/03/011.png)





### Features of the Project
* Backend
  * Token Based Security (using Spring security).
  * API documentation and Live Try-out links with Swagger .
  * PostgreSQL DataBase Configuration.
  * Using JPA to talk to relational database.
  * How to request and respond for paginated data .

* Frontend
  * Organizing Components, Modules, Services, Directives, Pages , routing etc in an Angular App.
  * How to chain RxJS Observables.
  * Techniques to Lazy load Data.
  * Techniques to load large data set in a data-table but still keeping DOM footprint less.
  * Routing and guarding pages that needs authentication.
  * Basic visulaization.
  * Pagination with jquery integred in Admin template.


  
### Build Frontend

```bash
# Navigate to PROJECT_FOLDER/webui (should contain package.json )
npm install
# build the project (this will put the files under dist folder)
ng build --prod --aot=true
# to strat
ng server 
# Or
npm start
```



```bash
# Maven Build : Navigate to the root folder where pom.xml is present 
mvn clean install

#OR

# Gradle Build : Navigate to the root folder where build.gradle is present 
gradle build
```



### Accessing Application
Cpmponent         | URL                                      | Credentials
---               | ---                                      | ---
Backtend          |  http://localhost:8080                   | `register new user`
Frontend          |  http://localhost:4200                   | ``register new user``
Swagger (API Ref) |  http://localhost:8080/swagger-ui.html  | ``email & password if Ask`` 
Swagger Spec      |  http://localhost:8080/v2/api-docs          |``Nada``

### Screenshots
#### Login
![Login](https://i55.servimg.com/u/f55/13/79/70/03/112.png)



