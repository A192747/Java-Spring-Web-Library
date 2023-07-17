package org.example.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    @NotEmpty(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов!")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 100, message = "Имя автора книги должно быть от 2 до 100 символов!")
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+\\s?([А-Я][а-я]+\\s?)?", message = "Введите ФИО в формате: Фамилия Имя Отчество(при наличии)")
    private String author;
    @Min(value = 0, message = "Book year should be positive!")
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

}
