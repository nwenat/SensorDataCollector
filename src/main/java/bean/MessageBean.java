package bean;

import javax.ejb.Stateless;

@Stateless
public class MessageBean {

    private String message = "New Message from BEAN";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
