package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    final String START_HTML = """
            <!DOCTYPE html>
            <html lang=\"ru\">
                <head>
                    <meta charset=\"UTF-8\">
                    <title>Example application</title>
                    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                          rel=\"stylesheet\"
                          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                          crossorigin=\"anonymous\">
                </head>
                <body>
                    <div class=\"container\">
            """;
    final String END_HTML = """
                      </div>
                </body>
            </html>
            """;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/users.json"),
                                        new TypeReference<List<Map<String, String>>>() {});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN

        response.setContentType("text/html;charset=UTF-8");
        StringBuffer responseHTML = new StringBuffer(START_HTML);
        responseHTML.append("""
                <table>
                    <tr>
                        <th>Id user</th>
                        <th>fullname user</th>
                    </tr>
                """);
        List<Map<String, String>> nestedList = getUsers();
        for (Map<String, String> map: nestedList) {
            responseHTML.append("<tr><td>" + map.get("id") + "</td>");
            responseHTML.append("<td><a href=\"/users/" + map.get("id") + "\">"
                                + map.get("firstName")
                                + " " + map.get("lastName")
                                + "</a></td></tr>");
        }
        responseHTML.append("</table>");
        responseHTML.append(END_HTML);
        PrintWriter out = response.getWriter();
        out.println(responseHTML);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> nestedList = getUsers();
        String userId = "";
        String fullname = "";
        String usersEmail = "";
        for (var user: nestedList) {
            if (user.get("id").equals(id)) {
                userId = user.get("id");
                fullname = user.get("firstName") + " " + user.get("lastName");
                usersEmail = user.get("email");
            }
        }
        if (id == null || userId.equals("")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter out = response.getWriter();
            out.println("Not found");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            StringBuffer responseHTML = new StringBuffer(START_HTML);
            responseHTML.append("""
                <table>
                    <tr>
                        <th>Id user</th>
                        <th>fullname user</th>
                        <th>email user</th>
                    </tr>
                    <tr>
                """);

            responseHTML.append("<td>" + userId + "</td>");
            responseHTML.append("<td>" + fullname + "</td>");
            responseHTML.append("<td>" + usersEmail + "</td>");


            responseHTML.append("</tr></table>");
            responseHTML.append(END_HTML);
            PrintWriter out = response.getWriter();
            out.println(responseHTML);
        }
        // END
    }
}
