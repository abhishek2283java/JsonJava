package model;

import java.time.LocalDate;

/**
 * Created by sca820 on 03 aug., 2022
 */
public class BookPOJO {
    private String title;
    private boolean inPrint;
    private LocalDate publishDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isInPrint() {
        return inPrint;
    }

    public void setInPrint(boolean inPrint) {
        this.inPrint = inPrint;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
