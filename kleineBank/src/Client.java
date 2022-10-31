import java.util.LinkedList;

public class Client {
    protected String userName;
    private String userPassword;
    protected long document;
    protected float balance;
    protected LinkedList<String> extrato;

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

    protected boolean subtractBalance(float subtraction){
        
        if(balance > subtraction){
            balance -= subtraction;
            return true;
        }
        else
            System.out.println("Not enough funds...");
            return false;
    }

    protected boolean addBalance(float add){
        balance += add;
        return true;
    }
}
