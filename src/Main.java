import App.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Filehandler handler = new Filehandler();
        try {
            handler.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        menu();
        handler.finalise();
    }
    static void menu() {
        boolean cont = true;
           while(cont) {
               System.out.println("Please select an option " +
                       "\n 1)Create User \n 2)Deposit \n 3)Withdraw \n 4)FundTransfer \n 5)TranscationHistory");
               Scanner sc = new Scanner(System.in);
               int option = sc.nextInt();
               Customerhandler handler1 = new Customerhandler();
               //Accountaction action = new Accountaction();
               switch (option) {
                   case 1:
                       handler1.addcustomer();
                       break;
                   case 2:
                       deposit();
                       break;
                   case 3:
                       withdraw();
                       break;
                   case 4:
                       transfer();
                       break;

                   case 5:
                       transactionHsitory();
                       break;
                   default:
                       cont=false;
                       System.out.println("Invalid Option");
               }
           }
    }
    private static void transactionHsitory() {
        Customerhandler handler1 = new Customerhandler();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = sc.nextInt();
        System.out.println("Enter the Password");
        String pw = sc.next();
        if (handler1.authenticate(id, pw)){
            System.out.println("***** TRANSACTION HISTORY *****");
            TransactionHandler handler=new TransactionHandler();
            handler.printhistory(id);
        }
    }
    private static void withdraw() {
        Customerhandler handler1 = new Customerhandler();
        Accountaction action = new Accountaction();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = sc.nextInt();
        System.out.println("Enter the Password");
        String pw = sc.next();
        if (handler1.authenticate(id, pw)) {
            System.out.println("Enter the amount to deposit");
            int amount = sc.nextInt();
            action.withdraw(id, amount,true);
        }
    }
    private static void transfer() {
        Customerhandler handler1 = new Customerhandler();
        Accountaction action = new Accountaction();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = sc.nextInt();
        System.out.println("Enter the Password");
        String pw = sc.next();
        if (handler1.authenticate(id, pw)) {
            System.out.println("Enter the customer id to which you want to transfer the amount");
            int toid = sc.nextInt();
            System.out.println("Enter the amount to deposit");
            int amount = sc.nextInt();
            action.transfer(id, toid, amount);
        }
    }
    private static void deposit() {
        Customerhandler handler1 = new Customerhandler();
        Accountaction action = new Accountaction();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = sc.nextInt();
        System.out.println("Enter the Password");
        String pw = sc.next();
        if (handler1.authenticate(id, pw)) {
            System.out.println("Enter the amount to deposit");
            int amount = sc.nextInt();
            action.deposit(id, amount,true);
        }
    }
}