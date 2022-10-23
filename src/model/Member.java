package model;

import java.sql.Blob;

public class Member {
     private String date;
     private String memberId;
     private String name;
     private String address;
     private String sex;
     private String age;
     private String nic;
     private String email;
     private String contact;

    public Member() {
    }

    public Member(String date, String memberId, String name, String address, String sex, String age, String nic, String email, String contact) {
        this.setDate(date);
        this.setMemberId(memberId);
        this.setName(name);
        this.setAddress(address);
        this.setSex(sex);
        this.setAge(age);
        this.setNic(nic);
        this.setEmail(email);
        this.setContact(contact);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }



    @Override
    public String toString() {
        return "Member{" +
                "date='" + date + '\'' +
                ", memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", nic='" + nic + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
