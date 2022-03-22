import java.util.*;

public class BankRunner {

    public static Status loginHandler(Scanner sc, Bank bank) {
        System.out.println("Enter your id:");
        int userId = sc.nextInt();
        System.out.println("Enter password: ");
        String password = sc.next();

        Status st = new Status();
        st.setLoggedIn(bank.verifyCredentials(userId, password));
        st.setUserId(userId);
        return st;
    }

    public static void main(String[] args) {

        Bank bank = new Bank();

        System.out.println("Welcome to Bank");
        Scanner sc = new Scanner(System.in);
        int optionNumber;

        int senderId, receiverId;
        String password;
        boolean loggedIn;

        do {
            System.out.println("Choose the option");
            System.out.println("1. Create an Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Add Money");
            System.out.println("4. Send Money");
            System.out.println("5. Delete Account");
            System.out.println("8. Exit");

            optionNumber = sc.nextInt();
            switch (optionNumber) {
                case 1:
                    System.out.print("Enter id:");
                    int id = sc.nextInt();
                    System.out.print("Enter password: ");
                    password = sc.next();
                    bank.addUser(id, password);
                    break;

                case 2:
                    Status st = loginHandler(sc, bank);
                    if (st.loggedIn) {
                        System.out.println("Enter receiver id");
                        receiverId = sc.nextInt();
                        System.out.println("Enter Amount: ");
                        double amountToTransfer = sc.nextDouble();
                        bank.sendMoney(st.userId, receiverId, amountToTransfer);

                    } else {
                        System.out.println("Bad Credentials");
                    }

                case 3:
                    System.out.println("Enter your id: ");
                    senderId = sc.nextInt();
                    System.out.println("Enter password: ");
                    password = sc.next();
                    loggedIn = bank.verifyCredentials(senderId, password);
                    if (loggedIn) {
                        System.out.println("Enter amount: ");
                        double amount = sc.nextDouble();
                        bank.addMoney(senderId, amount);
                    } else {
                        System.out.println("Bad Credentials :(");
                    }
                    break;

                case 4:
                    System.out.println("Enter your id: ");
                    int Id = sc.nextInt();
                    System.out.println("Enter password: ");
                    password = sc.next();
                    loggedIn = bank.verifyCredentials(Id, password);
                    if (loggedIn) {
                        System.out.println("Your balance is: " + bank.getBalance(Id));
                    } else {
                        System.out.println("Bad Credentials :(");
                    }
                    break;

                case 5:
                    System.out.println("Enter your id: ");
                    senderId = sc.nextInt();
                    System.out.println("Enter password: ");
                    password = sc.next();
                    loggedIn = bank.verifyCredentials(senderId, password);
                    if (loggedIn) {
                        System.out.println("Enter amount: ");
                        double amount = sc.nextDouble();
                        bank.withdraw(senderId, amount);

                    } else {
                        System.out.println("Bad Credentials :(");
                    }
                    break;

                case 8:
                    System.out.println("Bye (:");
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (optionNumber != 8);

        sc.close();
    }
}

class Status {
    boolean loggedIn;
    int userId;

    public void setLoggedIn(boolean result) {
        loggedIn = result;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setUserId(int uid) {
        userId = uid;
    }

    public Integer getUserId() {
        return userId;
    }
}
