This project demonstrates the usage of Jackson library to work with JSON data in Java

Chronology for the branches:
1. Initial commit in Master
2. java8-localdate-demo
3. object-inside-object-demo

Parsing a date property using Java 8 DateTime (LocalDate):
JUnit test testDateScenarioOneUsingJava8LocalDate shows this feature:
1. Need to update the POM by adding https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310/2.13.3
2. Register the module by registering it in ObjectMapper defaultObjectMapper.registerModule(new JavaTimeModule());
3. Then when reading a date and converted to Java 8 LocalDate, it will work.