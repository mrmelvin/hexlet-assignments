<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add new user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href="/users">Все пользователи</a>
            <!-- BEGIN -->
            <form action="/users/new" method="post">
                <div class="mb-3">
                    <label>Firstname</label>
                    <input class="form-control" type="text" name="firstname" value='${user.getOrDefault("firstName", "John")}'>
                </div>
                <div class="mb-3">
                    <label>Lastname</label>
                    <input class="form-control" type="text" name="lastname" value='${user.getOrDefault("lastName", "Doe")}'>
                </div>
                <div class="mb-3">
                    <label>Email</label>
                    <input class="form-control" type="text" name="email" value='${user.getOrDefault("email", "johndoe@hotmail.com")}'>
                </div>
                <button class="btn btn-primary" type="submit">Create</button>
            </form>
            <c:if test="${error == true}">
                <p>Firstname and lastname dont be empty!</p>
            </c:if>
            <!-- END -->
        </div>
    </body>
</html>
