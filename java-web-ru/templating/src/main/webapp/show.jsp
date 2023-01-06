<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Users</title>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <table>
            <tr>
                <th>User Id</th>
                <th>User fullname</th>
                <th>User email</th>
            </tr>
            <tr>
                <td>${id}</td>
                <td>${fullName}</td>
                <td>${email}</td>
            </tr>
        </table>
    </body>
</html>
<!-- END -->
