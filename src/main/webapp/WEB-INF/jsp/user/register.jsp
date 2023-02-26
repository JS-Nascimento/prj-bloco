<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>EmprestaNet</title>
</head>
<body>
<div class="container-fluid container-sm">
    <form class="border border-light rounded p-5" action="user/create" method="post">
        <h3>Cadastro de Usuários</h3>

        <div class="form-group">
            <label>Nome:</label>
            <input type="text" name="nome" value="Jorge Nascimento" class="form-control">
        </div>

        <div class="form-group">
            <label>Senha:</label>
            <input type="password" name="senha" value="123" class="form-control">
        </div>

        <div class="form-group">
            <label>E-mail:</label>
            <input type="email" name="email" value="jorge@gmail.com" class="form-control">
        </div>
        <div class=" mt-3 d-flex justify-content-end">
            <button class="btn btn-primary me-2" type="submit">Cadastrar</button>
            <a href="/user/viewList" class="btn btn-danger " >Cancelar</a>
        </div>
    </form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>