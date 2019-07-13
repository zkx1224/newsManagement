package service.register;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.User;
import service.UserService;
import service.UserServiceImpl;

public class registerService extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String  date = req.getParameter("date");
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            UserService userService = new UserServiceImpl();
            User user = new User(userName, sex, passWord, email, date1);
            System.out.println("userName:"+userName);
            boolean userNameIsExit = userService.checkUserNameIsExit(userName);
            if(userNameIsExit!=true){
              userService.addUser(user);
            }
            resp.sendRedirect("regis.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
