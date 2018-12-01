package servlets;

import bean.MessageBean;
//import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {


//    @Inject
    MessageBean messageBean = new MessageBean();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toPrint = "test test";
        String toPrint2 = messageBean.getMessage();
        resp.getWriter().print(toPrint + "\n\r" + toPrint2);
    }

}
