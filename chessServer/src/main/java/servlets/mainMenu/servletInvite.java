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

@WebServlet("/api/invite")
public class servletInvite extends HttpServlet {
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
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("servletInvite start");
        System.out.println("  prinyl id: " + id);
        String whoInvite = req.getParameter("whoinvite");
        System.out.println("  prinyl whoinvite: " + whoInvite);
        try {
            System.out.println(CheckInfPlayer.checkMeInvite(whoInvite));
            System.out.println(CheckInfPlayer.checkMeInvite(CheckInfPlayer.idToName(id)));
            if (CheckInfPlayer.checkMeInvite(whoInvite) == 1 && CheckInfPlayer.checkMeInvite(CheckInfPlayer.idToName(id)) == 1) {

                UpdateInfPlayer.updateMe_Invite(id, whoInvite);
                UpdateInfPlayer.updateI_Invite(CheckInfPlayer.idToName(id), CheckInfPlayer.nameToId(whoInvite));
                System.out.println("  res = 1");
                os.print(1);

            }else {
                System.out.println("  res =0");
                os.print(0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("servletInvite end");
    }
}

