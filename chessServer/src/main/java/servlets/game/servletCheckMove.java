package servlets.game;

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

@WebServlet("/api/checkmove")
public class servletCheckMove  extends HttpServlet {
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
        String whoPlay = req.getParameter("whoplay");


        try {
            if (GetInfPlayer.getPlay(CheckInfPlayer.nameToId(whoPlay)) == 0) {
                os.print("-3");
                System.out.println("-3");
            }
            else
            if (GetInfPlayer.getMove(CheckInfPlayer.nameToId(whoPlay)).equals("-1")){
                os.print("-1");
            }
            else{
                String move = GetInfPlayer.getMove(CheckInfPlayer.nameToId(whoPlay));
                UpdateInfPlayer.updateMove(whoPlay,"-1");
                os.print(move);
                System.out.println("servletCheckMove - start");
                System.out.println("  " + login + " get from " + whoPlay + "  move = " + move);
                System.out.println("servletCheckMove - end");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
