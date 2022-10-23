package view.tm;

public class BarrowTM {
    private String barrowId;
    private String bookId;
    private String memberId;
    private int totalDay;
    private String bookName;
    private String status;
    private String memberName;

    public BarrowTM() {
    }

    public BarrowTM(String barrowId, String bookId, String memberId, int totalDay, String bookName, String status, String memberName) {
        this.barrowId = barrowId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.totalDay = totalDay;
        this.bookName = bookName;
        this.status = status;
        this.memberName=memberName;
        //this.setMemberName(memberName);
    }

    public BarrowTM(String barrowId, String bookId, String memberId, int totalDay, String bookName, String status) {
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "BarrowTM{" +
                "barrowId='" + barrowId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", totalDay=" + totalDay +
                ", bookName='" + bookName + '\'' +
                ", status='" + status + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
