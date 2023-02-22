<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>Projeto de Bloco - Java WEB</title>
</head>
<body>
<div class="container-fluid container-sm">
    <form class="border border-light rounded p-5" action="user/create" method="post">
        <h3>Cadastro de Usuário</h3>

        <div class="form-group">
            <label>Nome:</label>
            <input type="text" name="nome" value="Elberth Moraes" class="form-control">
        </div>

        <div class="form-group">
            <label>Senha:</label>
            <input type="password" name="senha" value="123" class="form-control">
        </div>

        <div class="form-group">
            <label>E-mail:</label>
            <input type="email" name="email" value="elberth@gmail.com" class="form-control">
        </div>

        <div class="form-group">
            <label>Idade:</label>
            <input type="text" name="idade" value="44" class="form-control">
        </div>

        <div class="form-group">
            <label>Salário:</label>
            <input type="text" name="salario" value="999999" class="form-control">
        </div>

        <div class="form-group">
            <label>Características:</label>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" name="caracteristicas" value="De" class="form-check-input" > Developer
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" name="caracteristicas" value="An" checked class="form-check-input"> Analista
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" name="caracteristicas" value="Da" class="form-check-input"> Database
                </label>
            </div>
        </div>

        <div class="form-group">
            <label>Tipo:</label>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="radio" name="tipo" value="P" class="form-check-input" > Padrão
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="radio" name="tipo" value="D" class="form-check-input"> Diretor
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="radio" name="tipo" value="A" checked class="form-check-input"> Administrador
                </label>
            </div>
        </div>

        <div class="form-group">
            <label>Setor:</label>
            <select name="setor" class="form-control">
                <option value="1">Diretoria</option>
                <option value="2">Comercial</option>
                <option value="3" selected>Desenvolvimento</option>
            </select>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary " type="submit">Cadastrar</button>
            <button class="btn btn-danger " type="cancel">Cancelar</button>
        </div>
    </form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>