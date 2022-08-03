package model;

import java.time.LocalDate;

/**
 * Created by sca820 on 03 aug., 2022
 */
public class DatePojoJava8 {
    private String name;
    private LocalDate dateOfBirth;

    public DatePojoJava8() {
    }

    public DatePojoJava8(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
