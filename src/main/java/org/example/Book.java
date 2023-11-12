package org.example;

import java.io.Serializable;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------
public class Book  implements Serializable {

    private String title;
    private String authors;
    private double price;
    private String isbn;
    private String genre;
    private int year;

    public Book() {

    }

    public Book(String title, String authors, double price, String isbn, String genre, int year) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

    public Book(Book book) {
        this.title = book.title;
        this.authors = book.authors;
        this.price = book.price;
        this.isbn = book.isbn;
        this.genre = book.genre;
        this.year = book.year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }

    public boolean isEmpty() {
        boolean empty = false;
        if (this.title.isEmpty() &&
                this.authors.isEmpty() &&
                this.price == 0 &&
                this.isbn.isEmpty() &&
                this.genre.isEmpty() &&
                this.year == 0) {
            empty = true;
        }
        return empty;
    }
}
