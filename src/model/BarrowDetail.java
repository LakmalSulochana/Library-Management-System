package model;

public class BarrowDetail {
    private String barrowId;
    private String bookId;
    private String memberId;
    private int totalDay;
    private String bookName;
    private String status;

    public BarrowDetail() {
    }

    public BarrowDetail(String barrowId, String bookId, String memberId, int totalDay, String bookName, String status) {
        this.setBarrowId(barrowId);
        this.setBookId(bookId);
        this.setMemberId(memberId);
        this.setTotalDay(totalDay);
        this.setBookName(bookName);
        this.setStatus(status);
    }

    public String getBarrowId() {
        return barrowId;
    }

    public void setBarrowId(String barrowId) {
        this.barrowId = barrowId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
