package model;

import java.util.List;

public class Barrow {
    private String barrowId;
    private String memberId;
    private String barrowDate;
    private String returnDate;
    private String memberName;
    private String memberNic;
    private String contact;
    private String email;
    private List<BarrowDetail>barrow;

    public Barrow() {
    }

    public Barrow(String barrowId, String memberId, String barrowDate, String returnDate, String memberName, String memberNic, String contact, String email, List<BarrowDetail> barrow) {
        this.setBarrowId(barrowId);
        this.setMemberId(memberId);
        this.setBarrowDate(barrowDate);
        this.setReturnDate(returnDate);
        this.setMemberName(memberName);
        this.setMemberNic(memberNic);
        this.setContact(contact);
        this.setEmail(email);
        this.setBarrow(barrow);
    }

    public String getBarrowId() {
        return barrowId;
    }

    public void setBarrowId(String barrowId) {
        this.barrowId = barrowId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBarrowDate() {
        return barrowDate;
    }

    public void setBarrowDate(String barrowDate) {
        this.barrowDate = barrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNic() {
        return memberNic;
    }

    public void setMemberNic(String memberNic) {
        this.memberNic = memberNic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BarrowDetail> getBarrow() {
        return barrow;
    }

    public void setBarrow(List<BarrowDetail> barrow) {
        this.barrow = barrow;
    }

    @Override
    public String toString() {
        return "Barrow{" +
                "barrowId='" + barrowId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", barrowDate='" + barrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberNic='" + memberNic + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", barrow=" + barrow +
                '}';
    }
}
