public class User {
    private int id;
    private String password;
    private int mobileNum;
    private double balance;

    User(int id, String password) {
        this.id = id;
        this.password = password;
        this.balance = 500;
    }

    public int getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }

    public int getMobileNum() {
        return this.mobileNum;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addMoney(double amount) {
        this.balance += amount;
    }

    public void substractMoney(double amount) {
        this.balance -= amount;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

}
