import java.io.*;
import java.util.ArrayList;

public class RequestsManager {

    private ArrayList<Request> requests ;

    public RequestsManager() throws Exception {
        requests = new ArrayList<>() ;
        setSavedRequests();
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void saveRequests() throws IOException {
        File savedRequests = new File("Requests/requests") ;
        FileOutputStream fileOutputStream = new FileOutputStream(savedRequests) ;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream) ;
        for (Request temp : requests) {
            try {
                objectOutputStream.writeObject(temp);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void setSavedRequests() throws IOException {
        File savedRequests = new File("Requests/requests") ;
        FileInputStream fileInputStream = new FileInputStream(savedRequests);   //reads the file
        Request temp ;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            while ((temp = (Request) objectInputStream.readObject()) != null) {
                requests.add(temp);
            }
        } catch (Exception e) {
        }
        fileInputStream.close();
    }

    public void add(Request request){
        requests.add(request);
    }
}
