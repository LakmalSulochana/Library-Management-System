package view.tm;

public class CartTM {
    private String bookId;
    private String bookName;
    private String language;
    private String category;
    private String memberId;
    private String memberName;

    public CartTM() {
    }

    public CartTM(String bookId, String bookName, String language, String category, String memberId, String memberName) {
        this.setBookId(bookId);
        this.setBookName(bookName);
        this.setLanguage(language);
        this.setCategory(category);
        this.setMemberId(memberId);
        this.setMemberName(memberName);
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
