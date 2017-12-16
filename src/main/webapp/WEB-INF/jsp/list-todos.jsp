<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <title>Todos for ${name}</title>
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <table class="table table-striped">
                <caption>Your todos are</caption>
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Is it Done?</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.description}</td>
                            <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
                            <td>${todo.done}</td>
                            <td>
                                <a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Update</a>
                            </td>
                            <td>
                                <form action = "/delete-todo?id=${todo.id}" method = "post">
                                    <input type="submit" class="btn btn-warning" value="Delete" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <a href="/add-todo" class="button">Add Todo</a>
            </div>
        </div>

        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    </body>
</html>