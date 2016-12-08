package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chunlong.wchl on 2015/3/29.
 */

public class ServletDemo extends HttpServlet { //第一步：扩展HttpServlet抽象类

    private static Logger         log = LoggerFactory.getLogger(ServletDemo.class);
    //第二步：覆盖doGet()方法
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        log.warn("already step into doGet();");

        //第三步：获取HTTP请求中的参数信息
        String clientName = request.getParameter("clientName");
        if (clientName != null)
            clientName = new String(clientName.getBytes("ISO-8859-1"), "GB2312");
        else
            clientName = "my friend";

        //第四步：生成HTTP响应结果
        PrintWriter out;
        String title = "HelloServlet";
        String heading1 = "HelloServlet's doGet fethod：";
        //set content type
        response.setContentType("text/html;charset=GB2312");
        //write html page
        out = response.getWriter();
        out.print("<HTML><HEAD><TITLE>" + title + "</TITLE>");
        out.print("</HEAD><BODY>");
        out.print(heading1);
        out.println("<h1><p>" + clientName + ": how are you </h1>");
        out.print("</BODY></HTML>");
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

