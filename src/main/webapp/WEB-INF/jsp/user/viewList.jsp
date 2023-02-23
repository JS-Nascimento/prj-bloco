<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Projeto de Bloco - Java Web</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container-sm border border-light rounded">
    <h3 class="mt-3 mb-3">Usuários</h3>
    <c:if test="${not empty message}">

        <div class="alert alert-success">
            <strong>Sucesso</strong> ${message}.
        </div>

    </c:if>
    <form action="/user" method="get">
        <button class="btn btn-success mb-3 " type="submit">Novo Usuário</button>
    </form>
    <c:if test="${empty users}">
        <h5>Ainda não há usuários cadastrados.</h5>
    </c:if>
    <c:if test="${not empty users}">
            <div class="d-flex justify-content-end">
            <div class="my-3 badge bg-primary position-relative ">
                Registros
                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                 ${users.size()}
                <span class="visually-hidden">Registros</span>
             </span>
            </div>
            </div>


        <table class="table table-striped table-hover border border-light rounded">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Senha</th>
                <th>E-mail</th>
                <th>Ações</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.nome}</td>
                    <td>${u.senha}</td>
                    <td>${u.email}</td>
                    <td><a class="btn btn-danger" href="/user/delete/${u.id}"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                    </svg></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>