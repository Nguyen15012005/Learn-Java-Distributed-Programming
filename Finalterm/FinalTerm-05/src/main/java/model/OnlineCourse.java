package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class OnlineCourse extends Course{

    @Column(name = "URL")
    private String url;
}
