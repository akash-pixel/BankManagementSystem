import java.util.*;

public class Bank {

    private HashMap<Integer, User> users;
    ArrayList<Transaction> list;

    Bank() {
        users = new HashMap<>();
        list = new ArrayList<>();
    }

    void addUser(int id, String password, String name) {
        User newUser = new User(id, password, name);

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

            Transaction transction1 = new Transaction(
                    "money sent to " + users.get(receiverId).getName() + " @" + receiverId,
                    senderId, "Debit", amount,
                    sender.getBalance());
            list.add(transction1);
            Transaction transction2 = new Transaction(
                    "money received from " + users.get(senderId).getName() + " @" + senderId, receiverId, "Credit",
                    amount,
                    receiver.getBalance());
            list.add(transction2);

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

        Transaction transaction = new Transaction("Deposit", id, "Credit", amount, users.get(id).getBalance());
        list.add(transaction);

        System.out.println("Money deposited successfully :)\n Your current balance is: " + users.get(id).getBalance());
    }

    public double getBalance(int id) {
        return users.get(id).getBalance();
    }

    public void withdraw(int id, double amount) {
        users.get(id).substractMoney(amount);

        Transaction transaction = new Transaction("Withdraw", id, "Debit", amount, users.get(id).getBalance());
        list.add(transaction);
    }

    public void getTransactions() {

        int size = list.size() - 1;

        System.out.println("Remarks\t\tAccount\tType\tAmount(Rs.)\tBalance ");
        while (size >= 0) {
            System.out.printf("%s \t%d \t%s \t%.2f \t%.2f \n",
                    list.get(size).remarks,
                    list.get(size).id,
                    list.get(size).type,
                    list.get(size).amount,
                    list.get(size).balance);
            size--;
        }
    }

    public void getStatementOfUser(int userId) {

        int size = list.size() - 1;

        System.out.println("Remarks\t\tAccount\tType\tAmount(Rs.)\tBalance ");
        while (size >= 0) {
            if (userId == list.get(size).id) {
                System.out.printf("%s \t%d \t%s \t%.2f \t%.2f \n",
                        list.get(size).remarks,
                        list.get(size).id,
                        list.get(size).type,
                        list.get(size).amount,
                        list.get(size).balance);
            }
            size--;
        }
    }

    public void deleteAccount(int userId) {
        users.remove(userId);
        System.out.println("Account is deleted successfully.");
    }

}

class Transaction {
    String remarks, type;
    int id;
    double amount, balance;

    Transaction(String remarks, int userId, String type, double amount, double balance) {
        this.remarks = remarks;
        this.id = userId;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }
}
