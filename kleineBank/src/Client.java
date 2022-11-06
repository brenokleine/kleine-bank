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

    protected boolean subtractBalance(float subtraction, String type){
        
        if(balance > subtraction){
            balance -= subtraction;
            switch(type){
                case "withdrawal":
                    extrato.add("Withdrawal of " + subtraction + " $USD | " + java.time.LocalDate.now());
                    break;
                case "transference":
                    extrato.add("Transference of " + subtraction + " $USD | " + java.time.LocalDate.now());
                    break;
            }
            return true;
        }
        else
            System.out.println("Not enough funds...");
            return false;
    }

    protected boolean addBalance(float add){
        balance += add;
        extrato.add("Amount added: " + add + " $USD | " + java.time.LocalDate.now());
        return true;
    }

    private void getExtrato(){
        System.out.println(extrato.toString());
    }
}
