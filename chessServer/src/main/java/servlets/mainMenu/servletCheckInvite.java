package servlets.mainMenu;

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

@WebServlet("/api/checkinvite")
public class servletCheckInvite extends HttpServlet {
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
        System.out.println("servletCheckInvite start");
        System.out.println("  prinyl login: " + login);
        try {
            int id = GetInfPlayer.getMeInvite(CheckInfPlayer.nameToId(login));

            if (id > 0 && GetInfPlayer.getIInvite(CheckInfPlayer.nameToId(login)) == -1 && GetInfPlayer.getMeInvite(id) == -1 ){
                String temp = CheckInfPlayer.idToName(id);
                System.out.println("  idddddddddddddddddddddddddddddddddd =  >" + id + "<");
                os.print(temp);
                System.out.println("  resssssssssssssssssssssssssssssssssssssssssss = >" + temp + "<");
            } else {
                os.print("0");
                System.out.println("  res =  0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("servletCheckInvite end");
    }
}
