<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Projeto de Bloco - Java Web</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%
        String message = (String) request.getAttribute( "message" );
    %>

<div class="container-sm border border-light rounded">
    <h3>Login</h3>
    <%if (message != null){%>
        <div class="alert alert-danger">
            <strong>Atenção</strong> <%=message%>.
        </div>
    <%}%>



    <form  class="border border-light rounded p-3" action="/login" method="post">
        <div class="mb-3 mt-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
        <div class="mb-3">
            <label for="senha" class="form-label">Password:</label>
            <input type="password" class="form-control" id="senha" placeholder="Enter password" name="senha">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>