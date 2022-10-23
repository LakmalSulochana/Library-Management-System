package view.tm;

public class UserTM {
    private String userId;
    private String userName;
    private String address;
    private int telePhoneNumber;
    private String role;
    private String password;
    private String nic;

    public UserTM() {
    }

    public UserTM(String userId, String userName, String address, int telePhoneNumber, String role, String password, String nic) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setAddress(address);
        this.setTelePhoneNumber(telePhoneNumber);
        this.setRole(role);
        this.setPassword(password);
        this.setNic(nic);
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

    public int getTelePhoneNumber() {
        return telePhoneNumber;
    }

    public void setTelePhoneNumber(int telePhoneNumber) {
        this.telePhoneNumber = telePhoneNumber;
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
        return "UserTM{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", telePhoneNumber=" + telePhoneNumber +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
