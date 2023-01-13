package ru.acorn.MyLibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "book", uniqueConstraints = {
        @UniqueConstraint(columnNames = "person_id")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 150, message = "Name should be between 2 and 150 characters")
    @Getter
    @Setter
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 50, message = "author name should be between 2 and 50 characters")
    @Getter
    @Setter
    private String author;

    @Column(name = "year")
    @Min(value = 1500, message = "Year should be no more than 1500")
    @Getter
    @Setter
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Person owner;

    @Getter
    @Setter
    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;
    public Book() {
    }

    @Getter
    @Setter
    @Transient
    private boolean expired = false;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
