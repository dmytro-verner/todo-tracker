<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

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
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" class="btn btn-warning" value="Delete"
                            />
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

<%@ include file="common/footer.jspf"%>