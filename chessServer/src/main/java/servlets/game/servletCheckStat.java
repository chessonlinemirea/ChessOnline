package servlets.game;

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

@WebServlet("/api/checkstat")
public class servletCheckStat extends HttpServlet {
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
        System.out.println("servletCheckStat - start");
        String login = req.getParameter("login");
        System.out.println("  prinyl login: " + login);

        try {
            int win = GetInfPlayer.getWin(CheckInfPlayer.nameToId(login));
            int lose = GetInfPlayer.getLose(CheckInfPlayer.nameToId(login));
            os.print(win + " " + lose);
        } catch (Exception e) {
            e.printStackTrace();
        }

        os.print("0");
        System.out.println("servletCheckStat - end");
    }
}