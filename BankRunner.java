import java.util.*;
import java.util.regex.Pattern;

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

    public static String getPassword(Scanner sc) {

        System.out.println(
                "Password should be of minimum 6 character and contain at least 1 number");
        System.out.print("Set password: ");
        int count = 0;
        String passwd;
        boolean isValid;
        do {
            passwd = sc.next();
            isValid = passwd.length() >= 6 ? true : false;
            isValid = (Pattern.compile("[0-9]").matcher(passwd).find()) && isValid ? true : false;

            if (isValid)
                return passwd;

        } while (isValid == false && count < 3);
        return "";
    }

    public static void main(String[] args) {

        Bank bank = new Bank();

        System.out.println("Welcome to Bank");
        Scanner sc = new Scanner(System.in);
        int optionNumber;

        String password;
        Status status;

        do {
            System.out.println("\nChoose the option");
            System.out.println("1. Create an Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Add Money");
            System.out.println("4. Send Money");
            System.out.println("5. Withdraw");
            System.out.println("6. Transactions");
            System.out.println("7. User Statement");
            System.out.println("8. Delete Account");
            System.out.println("9. Exit");

            System.out.print("Enter option: ");
            optionNumber = sc.nextInt();
            System.out.println();
            switch (optionNumber) {
                case 1:

                    System.out.println("Enter your full name: ");
                    String name = sc.next();
                    password = getPassword(sc);
                    if (password == "") {
                        break;
                    }

                    Random random = new Random();
                    int id = random.nextInt(9999);
                    System.out.println("Your Account no./ Userid is: " + id);

                    bank.addUser(id, password, name);
                    break;

                case 2:
                    status = loginHandler(sc, bank);
                    if (status.loggedIn) {
                        System.out.println("Your balance is: " + bank.getBalance(status.userId));

                    } else {
                        System.out.println("Bad Credentials");
                    }
                    break;

                case 3:
                    status = loginHandler(sc, bank);
                    if (status.loggedIn) {
                        System.out.println("Enter amount: ");
                        double amount = sc.nextDouble();
                        bank.addMoney(status.userId, amount);
                    } else {
                        System.out.println("Bad Credentials :(");
                    }
                    break;

                case 4:
                    status = loginHandler(sc, bank);
                    if (status.loggedIn) {
                        System.out.println("Enter receiver id");
                        int receiverId = sc.nextInt();
                        System.out.println("Enter Amount: ");
                        double amountToTransfer = sc.nextDouble();
                        bank.sendMoney(status.userId, receiverId, amountToTransfer);
                    } else {
                        System.out.println("Bad Credentials :(");
                    }
                    break;

                case 5:
                    status = loginHandler(sc, bank);
                    if (status.loggedIn) {
                        System.out.println("Enter amount: ");
                        double amount = sc.nextDouble();
                        bank.withdraw(status.userId, amount);

                    } else {
                        System.out.println("Bad Credentials :(");
                    }
                    break;

                case 6:
                    bank.getTransactions();
                    break;

                case 7:
                    status = loginHandler(sc, bank);
                    if (status.loggedIn) {
                        bank.getStatementOfUser(status.userId);
                    }
                    break;

                case 8:
                    status = loginHandler(sc, bank);
                    if (status.loggedIn) {
                        bank.deleteAccount(status.userId);
                    } else {
                        System.out.println("Bad Credentials");
                    }
                    break;

                case 9:
                    System.out.println("Bye (:");
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (optionNumber != 9);

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
