import java.util.LinkedList;
import java.util.Scanner;

public class Client {
    protected String userName;
    protected String clientName;
    private String userPassword;
    protected long document;
    protected float balance;
    protected LinkedList<String> extrato;

    Scanner scan = new Scanner(System.in);

    public Client(String userName, String clientName, String userPassword, long document){
        this.userName = userName;
        this.clientName = clientName;
        this.userPassword = userPassword;
        this.document = document;
        balance = 0;
        extrato = new LinkedList<>();
        
    }

    protected boolean verifyPassword(String password){
        if(password.equals(userPassword)){
            return true;
        }else
            return false;
    }

    protected float getBalance(){
        return balance;
    }

    protected boolean withdraw(){

        System.out.println("Amount to withdraw (To go back enter 0): ");
        float subtraction = scan.nextFloat();

        if(subtraction == 0){return true;}

        if(balance > subtraction){
            balance -= subtraction;
            extrato.add("Withdrawal of " + subtraction + " $USD | " + java.time.LocalDate.now());
            return true;
        }
        else
            System.out.println("\nNot enough funds...");
            return false;
    }

    protected boolean deposit(){
        
        System.out.println("Amount to deposit (To go back enter 0): ");
        float add = scan.nextFloat();

        if(add == 0){return true;}
        
        balance += add;
        extrato.add("Amount deposited: " + add + " $USD | " + java.time.LocalDate.now());
        return true;
    }

    protected void getExtrato(){
        for (String string : extrato) {
            System.out.println(string + "\n");
        }
    }
}
