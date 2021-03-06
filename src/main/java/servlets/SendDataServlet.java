package servlets;

import bean.DataBean;
import data.UserMetrics;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "/SendData", urlPatterns = "/SendData")
public class SendDataServlet extends HttpServlet {

    private UserMetrics userMetrics = new UserMetrics();

    @Inject
    DataBean DataBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        JSONObject jsonData = new JSONObject(data);

        userMetrics.setInboxSMS(jsonData.getInt("inboxSMS"));
        userMetrics.setOutboxSMS(jsonData.getInt("outboxSMS"));
        userMetrics.setIncoming(jsonData.getInt("incoming"));
        userMetrics.setOutgoing(jsonData.getInt("outgoing"));
        userMetrics.setMissed(jsonData.getInt("missed"));

        String time = jsonData.getString("dateTime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        userMetrics.setDateTime(LocalDateTime.parse(time,formatter));

        try {
            DataBean.save(userMetrics);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().print("ok" + data);
    }
}
