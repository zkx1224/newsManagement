package register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/regis")
public class RegisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        System.out.println(date);
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            UserService userService = new UserServiceImpl();
            User user = new User(userName, sex, passWord, email,date1);
            boolean userNameIsExit = false;
            userNameIsExit = userService.checkUserNameIsExit("userName");
            if (userNameIsExit) {
                userService.addUser(user);
                req.setAttribute("succ", "success");
                System.out.println("添加成功！");
            } else {
                req.setAttribute("succ", "error");
                System.out.println("用户名已存在！");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/jsp/regis.jsp");
    }
}

