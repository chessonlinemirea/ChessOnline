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

@WebServlet("/api/inviteresult")
public class servletInviteResult extends HttpServlet {
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
        System.out.println("servletInviteResult - start");
        //System.out.println("  prinyl login: " + login);
        String result = req.getParameter("result");
        //System.out.println("  prinyl result: " + result);

        try {

            int who_id = CheckInfPlayer.searchWhoInvite(CheckInfPlayer.nameToId(login));

            if (result.equals("yes")){

                UpdateInfPlayer.updateI_Invite(login, 0 );

                UpdateInfPlayer.updateMe_Invite(0, CheckInfPlayer.idToName(who_id));
            } else if (result.equals("no")){


                UpdateInfPlayer.updateMe_Invite(-1, login);
                UpdateInfPlayer.updateI_Invite(login, -1);

                UpdateInfPlayer.updateMe_Invite(-1, CheckInfPlayer.idToName(who_id));
                UpdateInfPlayer.updateI_Invite(CheckInfPlayer.idToName(who_id), -1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        os.print("0");
        System.out.println("servletInviteResult - end");
    }
}
