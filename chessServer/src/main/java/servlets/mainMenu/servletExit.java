package servlets.mainMenu;

import database.player.CheckInfPlayer;
import database.player.UpdateInfPlayer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/exit")
public class servletExit extends HttpServlet {
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
        String login = req.getParameter("login");
        System.out.println("login: " + login);

        try {
            UpdateInfPlayer.updateOnline(CheckInfPlayer.nameToId(login),0);
            UpdateInfPlayer.updateI_Invite(login,-1);
            UpdateInfPlayer.updateMe_Invite(-1,login);
            UpdateInfPlayer.updatePlay(login,0);
            UpdateInfPlayer.updateColor(login,"-1");
            UpdateInfPlayer.updateMove(login,"-1");
            System.out.println("exit - " + login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        os.print("0");
    }
}
