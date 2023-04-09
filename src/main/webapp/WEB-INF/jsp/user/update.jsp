<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>EmprestaNet</title>
</head>
<body class="bg-light">
<div class="d-flex align-items-center justify-content-center my-auto " style="height: 70vh;">
    <div class="container-fluid col-md-6 bg-white">
        <form class="row border-start border-5  border-primary rounded p-5 shadow-sm" action="/user/save/${usr.id}"
              method="post">
            <h3>Atualização de Usuários</h3>
            <c:if test="${not empty message}">

                <div class="alert ${messageType == 1 ? 'alert-danger' : 'alert-success'} alert-dismissible fade show "
                     role="alert" id="success-alert">
                    <strong>${messageType == 1 ? 'Error - ' : 'Sucesso - '}</strong> ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            </c:if>
            <c:if test="${not empty usr}">

            <div class=" col-2 form-group">
                <label>Id</label>
                <input type="text" name="id" value="${usr.id}" disabled class="form-control">
            </div>
            <div class="col-10 form-group">
                <label>Nome</label>
                <input type="text" name="nome" value="${usr.nome}" class="form-control">
            </div>

            <div class="form-group col-3">
                <label>Senha</label>
                <input type="password" name="senha" value="${usr.senha}" disabled class="form-control">
            </div>

            <div class="col-9 form-group">
                <label>E-mail</label>
                <input type="email" name="email" value="${usr.email}" class="form-control">
            </div>
            <div class="form-group col-3 ">
                <label>Nível de Acesso</label>
                <select name="nivelAcesso"
                        class="form-select form-select-md" ${userLogged.nivelAcesso == 2 || usrLogged.nivelAcesso == 3 || userLogged.id == usr.id ? 'disabled' : ''} >
                    <option value="2" ${usr.nivelAcesso == 2 ? 'selected' : ''}>Padrão</option>
                    <option value="1" ${usr.nivelAcesso == 1 ? 'selected' : ''}>Administrador</option>
                    <option value="3" ${usr.nivelAcesso == 3 ? 'selected' : ''}>Diretoria</option>
                </select>
            </div>
            <div class="col-12 mt-3 d-flex justify-content-end">
                <button class="btn btn-primary me-2" type="submit">Cadastrar</button>
                </c:if>
                <a href="/user/viewList" class="btn btn-danger ">Cancelar</a>
            </div>


        </form>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    window.setTimeout(function () {
        $(".alert-success").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 1000);
    window.setTimeout(function () {
        $(".alert-danger").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 1000);

</script>
</html>