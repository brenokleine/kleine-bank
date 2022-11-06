import java.util.LinkedList;
import java.util.Scanner;

public class Client {
    protected String userName;
    private String userPassword;
    protected long document;
    protected float balance;
    protected LinkedList<String> extrato;

    Scanner scan = new Scanner(System.in);

    public Client(String userName, String userPassword, long document){
        this.userName = userName;
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

    protected boolean withdraw(float subtraction){

        if(balance > subtraction){
            balance -= subtraction;
            extrato.add("Withdrawal of " + subtraction + " $USD | " + java.time.LocalDate.now());
            return true;
        }
        else
            System.out.println("Not enough funds...");
            return false;
    }

    protected boolean deposit(float add){
        balance += add;
        extrato.add("Amount deposited: " + add + " $USD | " + java.time.LocalDate.now());
        return true;
    }

    private void getExtrato(){
        System.out.println(extrato.toString());
    }
}
