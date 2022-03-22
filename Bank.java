import java.util.*;

public class Bank {

    private HashMap<Integer, User> users;

    Bank() {
        users = new HashMap<>();
    }

    void addUser(int id, String password) {
        User newUser = new User(id, password);

        if (users.containsKey(id)) {
            System.out.println("User already exists");
        } else {
            users.put(id, newUser);
            System.out.println("Account created successfully.");
        }

    }

    public void sendMoney(int senderId, int receiverId, double amount) {
        User sender = users.get(senderId);

        if (sender.getBalance() >= amount) {
            System.out.println("Transferring the money :)");

            User receiver = users.get(receiverId);
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            System.out.println("Transaction Completed Successfully.");
            System.out.println("Your new balance is: " + sender.getBalance());

        } else {
            System.out.println("Bank balance low. Can't send money :(");
        }
    }

    public boolean verifyCredentials(int id, String password) {
        User user = users.get(id);
        return user.verifyPassword(password);
    }

    public void addMoney(int id, double amount) {
        users.get(id).addMoney(amount);
        System.out.println("Money deposited successfully :)\n Your current balance is: " + users.get(id).getBalance());
    }

    public double getBalance(int id) {
        return users.get(id).getBalance();
    }

    public void withdraw(int id, double amount) {
        users.get(id).substractMoney(amount);
    }

}
