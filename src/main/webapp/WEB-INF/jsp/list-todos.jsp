<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Todos for ${name}</title>
</head>
<body>
    <h1>Your todos</h1>
    <table>
        <caption>Your todos are</caption>
        <thead>
            <tr>
                <th>Description</th>
                <th>Date</th>
                <th>Is it Done?</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="/add-todo">Add Todo</a>
</body>
</html>