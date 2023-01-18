package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    private Integer getCurrentPage(String requestPage) {
        return (requestPage == null || requestPage.equals("1")) ? 1 : Integer.parseInt(requestPage);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        List<Map<String, String>> articles = new ArrayList<>();
        // BEGIN
        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT id, title, body FROM articles ORDER BY id LIMIT 10 OFFSET ?");
            Integer pageOffset;
            Integer currentPage = getCurrentPage(request.getParameter("page"));
            if (currentPage == 1) {
                pageOffset = 0;
            } else {
                pageOffset = (currentPage - 1) * 10;
            }
            statement.setInt(1, pageOffset);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                articles.add(Map.of(
                        "id", result.getString("id"),
                        "title", result.getString("title")
                ));
            }
            request.setAttribute("currentpage", currentPage);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("articles", articles);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String articleId = getId(request);
        if (articleId == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT title, body FROM articles WHERE id = ?");
            statement.setString(1, articleId);
            ResultSet result = statement.executeQuery();
            if (result.first()) {
                request.setAttribute("title", result.getString("title"));
                request.setAttribute("body", result.getString("body"));
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
