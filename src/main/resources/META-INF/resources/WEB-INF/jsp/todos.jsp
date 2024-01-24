<%@ include file="Common/Header.jspf"%>
<%@ include file="Common/Navigation.jspf"%>
<div class="container">
        <hr>
        <table class="table">
            <thead>
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>description</th>
                    <th>target Date</th>
                    <th>isDone?</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todo}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.username}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>False</td>
                        <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning"> Delete</th>
                        <td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</th>
                    </tr>
                </c:forEach>
            </tbody>
         </table>
         <a href="add-todo" class="btn btn-success">
         Add Todo
         </a>
        </hr>



<script src="webjars\bootstrap\5.1.3\js\bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>


</div>
</body>
</html>