package servlets.game;

import database.matchlog.GetInfMatchlog;
import database.matchlog.UpdateInfMatchlog;
import database.player.CheckInfPlayer;
import database.player.GetInfPlayer;
import database.player.UpdateInfPlayer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/move")
public class servletMove extends HttpServlet {
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
        System.out.println("servletMove - start");
        String login = req.getParameter("login");
        System.out.println("  prinyl login: " + login);
        String whoPlay = req.getParameter("whoplay");
        System.out.println("  prinyl whoplay: " + whoPlay);
        String move = req.getParameter("move");
        System.out.println("  prinyl move: " + move);

        try {
            UpdateInfPlayer.updateMove(login, move);

            if (GetInfPlayer.getColor(CheckInfPlayer.nameToId(login)).equals("light")){
                int id_m = GetInfMatchlog.getLastId(whoPlay,login);
                String log = GetInfMatchlog.getLastLog(id_m);
                UpdateInfMatchlog.updateLog(id_m,log + " " + move);
                System.out.println(id_m + "// " + log + " " + move);
            }
            else {
                int id_m = GetInfMatchlog.getLastId(login,whoPlay);
                String log = GetInfMatchlog.getLastLog(id_m);
                UpdateInfMatchlog.updateLog(id_m,log + " " + move);
                System.out.println(id_m + "// " + log + " " + move);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        os.print("0");
        System.out.println("servletMove - end");
    }
}
