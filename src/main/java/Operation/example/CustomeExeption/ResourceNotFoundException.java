package Operation.example.CustomeExeption;

public class ResourceNotFoundException  extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
