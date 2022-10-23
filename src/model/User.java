package model;

import javafx.collections.ObservableList;

public class User {
    private String userId;
    private String userName;
    private String address;
    private int mobile;
    private String role;
    private String password;
    private String nic;
    public User(String text, String txtUserNameText, String txtAddressText, int mobile, ObservableList<String> items, String txtPasswordText,String nic) {
    }

    public User(String userId, String userName, String address, int mobile, String role, String password,String nic) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setAddress(address);
        this.setMobile(mobile);
        this.setRole(role);
        this.setPassword(password);
        this.setNic(nic);
    }
    public User(String userId,String password ){
        this.setUserId(userId);
        this.setPassword(password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", mobile=" + mobile +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
