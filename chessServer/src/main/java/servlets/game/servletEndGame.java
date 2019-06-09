package servlets.game;

import database.player.UpdateInfPlayer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/endgame")
public class servletEndGame extends HttpServlet {
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
        System.out.println("servletStat - start");
        String login = req.getParameter("login");
        System.out.println("  prinyl login: " + login);
        //String whoPlay = req.getParameter("whoplay");
        //System.out.println("  prinyl whoplay: " + whoPlay);
        String res = req.getParameter("res");
        System.out.println("  prinyl res: " + res);

        try {
            if (res.equals("win")) {
                UpdateInfPlayer.updateWin(login);
            } else if (res.equals("lose")) {
                UpdateInfPlayer.updateLose(login);
            }
            UpdateInfPlayer.updatePlay(login,0);
            UpdateInfPlayer.updateColor(login,"-1");
            UpdateInfPlayer.updateMove(login,"-1");

        } catch (Exception e) {
            e.printStackTrace();
        }

        os.print("0");
        System.out.println("servletStat - end");
    }
}
