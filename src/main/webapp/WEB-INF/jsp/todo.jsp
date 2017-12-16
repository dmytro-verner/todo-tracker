<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <title>Add a todo</title>
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form:form method="post" commandName="todo">
                <form:hidden path="id"/>
                <fieldset class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" type="text" class="form-control" required="required"/>
                    <form:errors path="description" cssClass="text-warning"/>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="targetDate">Target date</form:label>
                    <form:input path="targetDate" type="text" class="form-control" required="required"/>
                    <form:errors path="targetDate" cssClass="text-warning"/>
                </fieldset>
                <button type="submit" class="btn btn-success">Add</button>
            </form:form>
        </div>

    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>

    <script>
        $('#targetDate').datepicker({
            format : 'dd/mm/yyyy'
        });
    </script>
    </body>
</html>