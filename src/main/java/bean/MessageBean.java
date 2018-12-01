package bean;


//import javax.ejb.Singleton;




public class MessageBean {

    private String message = "***";
    //implements Serializable

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
