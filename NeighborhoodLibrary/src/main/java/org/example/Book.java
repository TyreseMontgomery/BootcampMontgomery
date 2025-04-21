package org.example;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private Boolean isCheckedOut;
    private String checkedOutTo;

    public Book(String checkedOutTo, Boolean isCheckedOut, String title, String isbn, int id) {
        this.checkedOutTo = checkedOutTo;
        this.isCheckedOut = isCheckedOut;
        this.title = title;
        this.isbn = isbn;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsCheckedOut() {
        return isCheckedOut;
    }

    public void setIsCheckedOut(Boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

}












