package servlets.game;

import database.matchlog.AddMatchlog;
import database.matchlog.UpdateInfMatchlog;
import database.player.CheckInfPlayer;
import database.player.GetInfPlayer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/startgame")
public class servletStartGame extends HttpServlet {
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream os = resp.getOutputStream();
        System.out.println("servletStartGame - start");
        int id1 = Integer.parseInt(req.getParameter("id1"));
        System.out.println("  prinyl id1: " + id1);
        int id2 = Integer.parseInt(req.getParameter("id2"));
        System.out.println("  prinyl id2: " + id2);

        try {
            String color = GetInfPlayer.getColor(id1);
            System.out.println("  res = " + color);
            os.print(color);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("servletStartGame - end");
    }
}
