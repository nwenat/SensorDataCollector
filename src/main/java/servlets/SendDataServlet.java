package servlets;

import bean.DataBean;
import data.UserMetrics;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "/SendData", urlPatterns = "/SendData")
public class SendDataServlet extends HttpServlet {

    private UserMetrics userMetrics;

    @Inject
    DataBean DataBean;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        DataBean.save();

//
//        Gson gson = new Gson();
//        userMetrics = gson.fromJson(reader, UserMetrics.class);
//        DataBean.save(userMetrics);


        resp.getWriter().print("ok");
    }
}
