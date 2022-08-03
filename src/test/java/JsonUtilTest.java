import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import model.AuthorPOJO;
import model.BookPOJO;
import model.DatePojo;
import model.DatePojoJava8;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sca820 on 03 aug., 2022
 */
class JsonUtilTest {
    Person person = new Person("20120101-8858", "Ram Shyam", 30);
    Person personWithHobby = new Person("20140202-1245", "Ram Shyam", 30, List.of("Swimming", "Painting"));

    private String dateScenarioOne = "{\n" +
            "  \"name\": \"Abhikriti\",\n" +
            "  \"dateOfBirth\": \"2012-04-04\"\n" +
            "}";

    private String authorBookScenario = "{\n" +
            "  \"authorName\": \"Rui\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"Around the Mars in 81 Days\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2022-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"What to do on Sundays\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"publishDate\": \"2021-12-25\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    void testToString() {
        System.out.println(person);
        System.out.println(personWithHobby);
    }

    @Test
    void test_Parse_String_As_JSon() {
        //String jsonSource = "{\"title\": \"Around the World in 80 days\" }";
        String jsonSource = "{\n" +
                "\t\"title\": \"Around the World in 80 days\",\n" +
                "\t\"author\": \"Abhikriti Choudhary\"\n" +
                "}";
        JsonNode node = JsonUtil.parse(jsonSource);

        //System.out.println(node.get("title").asText());
        assertThat(node.get("title").asText()).isEqualTo("Around the World in 80 days");
    }

    @Test
    void test_fromJSon_To_JavaClass() throws JsonProcessingException {
        String jsonPersonSource = "{\"ssn\": \"20120101-8858\", \"name\": \"Abhikriti Choudhary\", \"age\": 10 }";
        String jsonPersonSourceNew = "{\"ssn\": \"20120101-8858\", \"name\": \"Abhikriti Choudhary\", \"age\": \"10\" }";   //even this works
        String jsonPersonSourceWithAddress = "{\n" +
                "\t\"ssn\": \"20120101-8858\",\n" +
                "\t\"name\": \"Abhikriti Choudhary\",\n" +
                "\t\"age\": \"10\",\n" +
                "\t\"address\": {\n" +
                "\t\t\"street\": \"Claessonsgatan 5\",\n" +
                "\t\t\"zipcode\": 41656,\n" +
                "\t\t\"city\": \"Gothenburg\"\n" +
                "\t}\n" +
                "}";

        JsonNode node = JsonUtil.parse(jsonPersonSourceWithAddress);
        Person person = JsonUtil.fromJSon(node, Person.class);

        assertThat(person.getName()).isEqualTo("Abhikriti Choudhary");
        assertThat(person).isInstanceOf(Person.class);
    }

    @Test
    void testFromPojoInstancetoJson() {
        final JsonNode jsonNode = JsonUtil.toJson(person);
        final String ssn = jsonNode.get("ssn").asText();
        System.out.println("ssn = " + ssn);
        assertThat(jsonNode.get("ssn").asText()).isEqualTo("20120101-8858");
    }

    @Test
    void stringify() throws JsonProcessingException {
        final JsonNode jsonNode = JsonUtil.toJson(person);
        final String str = JsonUtil.stringify(jsonNode);
        System.out.println("str = " + str); //str = {"ssn":"20120101-8858","name":"Ram Shyam","age":30,"hobbies":null}
    }

    @Test
    void prettyPrint() throws JsonProcessingException {
        final JsonNode jsonNode = JsonUtil.toJson(person);
        final String str = JsonUtil.prettyPrint(jsonNode);
        System.out.println("Pretty String = " + str);
    }

    @Test
    void testDateScenarioOne() throws JsonProcessingException {
        final JsonNode jsonNode = JsonUtil.parse(dateScenarioOne);
        final DatePojo datePojo = JsonUtil.fromJSon(jsonNode, DatePojo.class);
        System.out.println("DATE : " + datePojo.getDateOfBirth());  //Wed Apr 04 02:00:00 CEST 2012
    }

    @Test
    void testDateScenarioOneUsingJava8LocalDate() throws JsonProcessingException {
        final JsonNode jsonNode = JsonUtil.parse(dateScenarioOne);
        final DatePojoJava8 datePojo = JsonUtil.fromJSon(jsonNode, DatePojoJava8.class);
        System.out.println("DATE Using Java 8: " + datePojo.getDateOfBirth());
        //com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling
        assertThat(datePojo.getDateOfBirth().toString()).isEqualTo("2012-04-04");
    }

    @Test
    void testObjectInsideObjectUsingAuthorBookScenario() throws JsonProcessingException {
        //https://www.youtube.com/watch?v=vi1lU57U2p8&list=PLAuGQNR28pW4dOc5uytMdzcQ4-TCJFUN4&index=3
        final JsonNode jsonNode = JsonUtil.parse(authorBookScenario);
        final AuthorPOJO authorPOJO = JsonUtil.fromJSon(jsonNode, AuthorPOJO.class);
        System.out.println("Author: " + authorPOJO.getAuthorName());
        for(BookPOJO bp : authorPOJO.getBooks()) {
            System.out.println("Book Title:         " + bp.getTitle());
            System.out.println("Book inPrint?:      " + bp.isInPrint());
            System.out.println("Book PublishDate:   " + bp.getPublishDate());
            System.out.println("--------------------");
        }
        assertThat(authorPOJO.getAuthorName()).isEqualTo("Rui");
        assertThat(authorPOJO.getBooks()).hasOnlyElementsOfType(BookPOJO.class);
        assertThat(authorPOJO.getBooks()).hasSize(2);
    }
}