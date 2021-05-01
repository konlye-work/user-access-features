# Getting Started

### Instructions
This application is to retrieve user access of a feature or to modify access by feature name and email. Following are the supported HTTP operations and examples.

* **GET**<br/>
  Example: <br/>
  http://localhost:8080/feature?email=XXX&featureName=XXX
  <br/><br/>

* **POST**<br/>
  Note: Feature name and email must exist in the database <br/>
  Example: <br/>
  http://localhost:8080/feature <br/>
  {
  "featureName": "Feature 1",
  "email": "test1@gmail.com",
  "enable": true
  } <br/><br/>

### Database Access
Run UserAccessFeaturesApplication and enter url http://localhost:8080/h2-console/

JDBC URL: (Change JDBC URL to jdbc:h2:mem:test) <br/>
Username: sa <br/>
Password: (leave blank) <br/>

### Unit Test
Unit test is included in src/test/java/com/features/api/UserAccessFeaturesApplicationTests. You may either execute UserAccessFeaturesApplicationTests class to run all test methods or run individual @Test methods