import java.util.LinkedList;

public class Database{
    
    LinkedList<Client> clientsList;
    
    public Database(){
        this.clientsList = new LinkedList<>();
        initialFill();
    }

    protected void addClient(String userName, String userPassword, long document) {
        
        clientsList.add(new Client(userName, userPassword, document));
        
    }

    protected Client verifyUserName(String userName){
        for (Client client : clientsList) {
            if(userName.equals(client.userName)){
                return client;
            }
        }
        return null;
    }

    protected Client verifyDocument(long document){
        for (Client client : clientsList) {
            if(document == client.document){
                return client;
            }
        }
        return null;
    }

    protected Client verifyLogIn(String userName, String userPassword){
        
        Client client = verifyUserName(userName);

        if(client != null){
            if(client.verifyPassword(userPassword)){
                System.out.println("Password matches...");
                return client;
            }
            
            return null;
        }
        else
            System.out.println("Username not found...");
            return null;
    }

    private void initialFill(){

        clientsList.add(new Client("brenokleine", "mypassword", 111111111));
        
        clientsList.add(new Client("armandojose", "mypassword", 222222222));
        
        clientsList.add(new Client("arthurreinaldo", "mypassword", 333333333));
        
        clientsList.add(new Client("bilaragao", "mypassword", 444444444));

    }
    

}
