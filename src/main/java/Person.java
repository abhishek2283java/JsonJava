import java.util.List;

/**
 * Created by sca820 on 03 aug., 2022
 */
public class Person {
    private String ssn;
    private String name;
    private int age;
    private List<String> hobbies;

    public Person(){
        //no-arg constructor
    }

    public Person(String ssn, String name, int age) {
        this.ssn = ssn;
        this.name = name;
        this.age = age;
    }

    public Person(String ssn, String name, int age, List<String> hobbies) {
        this.ssn = ssn;
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ssn='" + ssn + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                '}';
    }
}
