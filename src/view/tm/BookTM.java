package view.tm;

public class BookTM {
    private String bookId;
    private String bookName;
    private String title;
    private String authorName;
    private String language;
    private String releasedDate;
    private String categoryName;
    private String donateId;
    private String addType;
    private String status;

    public BookTM() {
    }

    public BookTM(String bookId, String bookName, String title, String language, String categoryName, String status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.language = language;
        this.categoryName = categoryName;
        this.status = status;
    }

    public BookTM(String bookId, String bookName, String title, String authorName, String language, String releasedDate, String categoryName, String donateId, String addType, String status) {
        this.setBookId(bookId);
        this.setBookName(bookName);
        this.setTitle(title);
        this.setAuthorName(authorName);
        this.setLanguage(language);
        this.setReleasedDate(releasedDate);
        this.setCategoryName(categoryName);
        this.setDonateId(donateId);
        this.setAddType(addType);
        this.setStatus(status);
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

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDonateId() {
        return donateId;
    }

    public void setDonateId(String donateId) {
        this.donateId = donateId;
    }

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookTM{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", language='" + language + '\'' +
                ", releasedDate='" + releasedDate + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", donateId='" + donateId + '\'' +
                ", addType='" + addType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
