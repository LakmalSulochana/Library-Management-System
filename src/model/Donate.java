package model;

public class Donate {
    private String donateId;
    private String personName;
    private String personAddress;
    private String mobile;
    private String nic;
    private String date;
    private String email;

    public Donate() {
    }

    public Donate(String donateId, String personName, String personAddress, String mobile, String nic, String date, String email) {
        this.setDonateId(donateId);
        this.setPersonName(personName);
        this.setPersonAddress(personAddress);
        this.setMobile(mobile);
        this.setNic(nic);
        this.setDate(date);
        this.setEmail(email);
    }

    public String getDonateId() {
        return donateId;
    }

    public void setDonateId(String donateId) {
        this.donateId = donateId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Donate{" +
                "donateId='" + donateId + '\'' +
                ", personName='" + personName + '\'' +
                ", personAddress='" + personAddress + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nic='" + nic + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
