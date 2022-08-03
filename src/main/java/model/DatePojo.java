package model;

import java.util.Date;

/**
 * Created by sca820 on 03 aug., 2022
 */
public class DatePojo {
    private String name;
    private Date dateOfBirth;

    public DatePojo() {
    }

    public DatePojo(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
