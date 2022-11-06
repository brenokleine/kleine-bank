
import java.util.Scanner;

public class BankSystem {
    
    Scanner scan = new Scanner(System.in);

    Database database = new Database();
    
    protected void mainMenu(){
        int choice = 0;
        
        System.out.println("--------------- Menu ---------------");
        System.out.println("Enter 1 to Log In or Enter 2 to Create Account");

        choice = scan.nextInt();

        switch (choice) {
            case 1:
                logInMenu();
                mainMenu();
                break;
            case 2: 
                createAccount();
                mainMenu();
                break;
            default:
                System.out.println("Must be numbers 1 or 2...");
                mainMenu();
                break;
        }
    }

    private void logInMenu(){
        System.out.println("\n");
        System.out.println("--------------- Log in Menu ---------------");
        
        System.out.println("Enter your username: (or enter " + "0" + " to go back)");
        String userName = scan.next();
        if(userName.equals("0")){
            System.out.println("Going back...");
            return;
        }
        
        System.out.println("Enter your password: (or enter " + "0" + " to go back)");
        String userPassword = scan.next();
        if(userPassword.equals("0")){
            System.out.println("Going back...");
            return;
        }

        Client login = database.verifyLogIn(userName, userPassword);

        if(login != null){
            System.out.println("Logged in successfully! ");
            clientMenu(login);
        }
        else
            System.out.println("Error to log in...");
    }

    //todo continue client menu...
    private void clientMenu(Client client){
        System.out.println("\n");
        System.out.println("--------------- Welcome, " + client.userName + "! ---------------");
        System.out.println("Your balance: " + client.balance + "$USD");
        System.out.println("1 - Withdraw \n2 - Deposit \n3 - Transfer \n4 - Extrato\n0 - Exit");

        int choice = scan.nextInt();

        switch(choice){
            case 1:
                
        }

    }

    
    
    private void createAccount(){
        
        String username;
        String password = "";

        System.out.println("--------------- Create Account ---------------");
        System.out.println("Enter document: ");
        long document = scan.nextLong();

        if(database.verifyDocument(document) == null){
            do{
                System.out.println("Enter username: ");
                username = scan.next();
                if(database.verifyUserName(username) == null){
                    System.out.println("Enter password: ");
                    password = scan.next();
                } else {
                    System.out.println("Username already exists...");
                }
            } while(database.verifyUserName(username) != null);

            database.addClient(username, password, document);

        }
        else
            System.out.println("Document already linked to a client...");
        
    }


  
}
