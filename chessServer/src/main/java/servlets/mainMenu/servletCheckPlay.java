package servlets.mainMenu;

import database.matchlog.AddMatchlog;
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

@WebServlet("/api/checkplay")
public class servletCheckPlay extends HttpServlet {
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
        //System.out.println("login: " + login);

        //esli u igroka me_invite == 0 togda uznaem s kem igraem i check play u oboix

        try {
            if(GetInfPlayer.getPlay(CheckInfPlayer.nameToId(login)) == 0) {
                os.print("0");
            }
            else
            if (GetInfPlayer.getMeInvite(CheckInfPlayer.nameToId(login)) == 0){
                int id = GetInfPlayer.getIInvite(CheckInfPlayer.nameToId(login));
                if (GetInfPlayer.getPlay(id) == 1){
                    UpdateInfPlayer.updateColor(login, "light");
                    System.out.println("  " + login);
                    System.out.println("  light");
                    AddMatchlog.main(login,CheckInfPlayer.idToName(id));
                    os.print("1");
                }
                else os.print("0");
            }
            else if (GetInfPlayer.getIInvite(CheckInfPlayer.nameToId(login)) == 0){
                int id = GetInfPlayer.getMeInvite(CheckInfPlayer.nameToId(login));
                if (GetInfPlayer.getPlay(id) == 1){
                    UpdateInfPlayer.updateColor(login, "dark");
                    System.out.println("  " + login);
                    System.out.println("  dark");
                    os.print("1");
                }
                else os.print("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
