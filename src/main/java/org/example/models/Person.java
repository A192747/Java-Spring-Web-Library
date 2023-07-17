package org.example.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Поле не должно быть пустым!")
    @Size(min = 2, message = "ФИО должно быть больше 2-х символов!")
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+\\s?([А-Я][а-я]+\\s?)?", message = "Введите ФИО в формате: Фамилия Имя Отчество(при наличии)")
    private String name;
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int year;

    public Person() {}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
