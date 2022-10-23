package model;

public class Book {
    private String addType;
    private String donateId;
    private String bookId;
    private String bookName;
    private String categoryName;
    private String title;
    private String authorName;
    private String language;
    private String date;
    private String status;

    public Book() {
    }

    public Book(String bookId, String bookName, String categoryName, String title, String language, String status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.categoryName = categoryName;
        this.title = title;
        this.language = language;
        this.status = status;
    }

    public Book(String addType, String donateId, String bookId, String bookName, String categoryName, String title, String authorName, String language, String date, String status) {
        this.setAddType(addType);
        this.setDonateId(donateId);
        this.setBookId(bookId);
        this.setBookName(bookName);
        this.setCategoryName(categoryName);
        this.setTitle(title);
        this.setAuthorName(authorName);
        this.setLanguage(language);
        this.setDate(date);
        this.setStatus(status);
    }

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public String getDonateId() {
        return donateId;
    }

    public void setDonateId(String donateId) {
        this.donateId = donateId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "addType='" + addType + '\'' +
                ", donateId='" + donateId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", language='" + language + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
