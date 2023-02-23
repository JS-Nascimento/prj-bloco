<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <form action="/user" method="get">

        <button class="btn btn-success mb-3 " type="submit">Novo</button>
    </form>

    <table class="table table-striped table-hover border border-light rounded">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Senha</th>
            <th>E-mail</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Elberth Moraes</td>
            <td>123</td>
            <td>elberth.moraes@prof.infnet.edu.br</td>

        </tr>
        <tr>
            <td>Elberth Moraes</td>
            <td>123</td>
            <td>elberth.moraes@prof.infnet.edu.br</td>

        </tr>
        <tr>
            <td>Elberth Moraes</td>
            <td>123</td>
            <td>elberth.moraes@prof.infnet.edu.br</td>


        </tr>
        </tbody>
    </table>

</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>