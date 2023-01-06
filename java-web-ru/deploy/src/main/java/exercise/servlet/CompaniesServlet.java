package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        if (request.getQueryString() == null || request.getQueryString().equals("")) {
            String bigMassiveOutput = getCompanies().stream().collect(Collectors.joining("\n"));
            out.println(bigMassiveOutput);
        } else {
            String output = getCompanies().stream()
                    .filter(symbol -> symbol.contains(request.getParameter("search")))
                    .collect(Collectors.joining("\n"));
            out.println(output.length() > 0 ? output : "Companies not found");
        }
        // END
    }
}
