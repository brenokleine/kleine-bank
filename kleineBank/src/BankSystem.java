
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
                System.out.println("\nMust be numbers 1 or 2...");
                mainMenu();
                break;
        }
    }

    private void logInMenu(){
        System.out.println("\n--------------- Log in Menu ---------------");
        
        System.out.println("\nEnter your username: (or enter " + "0" + " to go back)");
        String userName = scan.next();
        if(userName.equals("0")){
            System.out.println("\nGoing back...");
            return;
        }
        
        System.out.println("\nEnter your password: (or enter " + "0" + " to go back)");
        String userPassword = scan.next();
        if(userPassword.equals("0")){
            System.out.println("\nGoing back...");
            return;
        }

        Client login = database.verifyLogIn(userName, userPassword);

        if(login != null){
            System.out.println("\nLogged in successfully!");
            clientMenu(login);
        }
        else
            System.out.println("\nError to log in...");
    }

    private void clientMenu(Client client){
        
        while(true){
            System.out.println("\n--------------- Welcome, " + client.clientName + "! ---------------");
            System.out.println("\nYour balance: " + client.balance + " $USD");
            System.out.println("\n1 - Withdraw \n2 - Deposit \n3 - Transaction \n4 - Extract\n0 - Exit");
    
            int choice = scan.nextInt();
    
            switch(choice){
                case 1:
                    client.withdraw();
                    break;
                case 2:
                    client.deposit();
                    break;
                case 3:
                    transaction(client);
                    break;
                case 4:
                    System.out.println("---------- Extract ----------");
                    client.getExtrato();
                    System.out.println("Enter 0 to continue...");
                    scan.next();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid option, try again...");
            }
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

    private void transaction(Client client){
        System.out.println("---------- Transaction Menu ----------");
        System.out.println("Type the recipient's document: ");
        long recipientDocument = scan.nextLong();

        Client recipient = database.verifyDocument(recipientDocument);

        if(recipient == null){
            System.out.println("Document not found on the database, cancelling transaction...");
            
            try {
                Thread.sleep(2000);
                return;
            } catch (InterruptedException e) {return;}

        } else {
            System.out.println("--- Type 0 to cancel transaction ---");
            System.out.println("Recipient's Name: " + recipient.clientName);
            System.out.println("Amount to be sent: ");
            float amount = scan.nextFloat();

            if(amount == 0){
                System.out.println("Cancelling transaction...");

                try {
                    Thread.sleep(2000);
                    return;
                } catch (InterruptedException e) {return;}

            } else {
                System.out.println("Processing Transaction...");
                client.balance -= amount;
                client.extrato.add("Transaction of " + amount + " $USD to " + recipient.clientName + " | " + java.time.LocalDate.now());
                
                recipient.balance += amount;
                recipient.extrato.add("Recieving of " + amount + " $USD from " + client.clientName + " | " + java.time.LocalDate.now());

                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}

                System.out.println("Transaction Complete! Returning...");
                return;
            }
        }
    }


}
