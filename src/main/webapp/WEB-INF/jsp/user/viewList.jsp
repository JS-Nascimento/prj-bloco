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

        <div class="alert alert-success alert-dismissible fade show" role="alert" id="success-alert">
            <strong>Sucesso</strong> ${message}.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
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
                    <td>
                        <button type="button" class="btn btn-danger" data-id="${u.id}" data-id-nome="${u.nome}" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                            </svg>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <a id="deleteLink" href="#" class="btn btn-primary">Confirmar</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>


    var modal = $('#staticBackdrop');
    var modalTagged = modal.find('.modal-body');
    var modalFooter = modal.find('.modal-footer');
    var link = modalFooter.find('#deleteLink');


    function tagText(text) {
        return '<strong>' + text + '</strong>';
    }


    function formatModalText(nomeTagged) {
        return 'Confirma a exclusão do usuário ' + nomeTagged + ' ?';
    }


    function updateDeleteLink(id) {
        link.attr('href', '/user/delete/' + id);
    }


    $('#staticBackdrop').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);


        var id = button.data('id');
        var nome = button.data('id-nome');


        var nomeTagged = tagText(nome);


        var modalText = formatModalText(nomeTagged);


        modalTagged.html(modalText);


        updateDeleteLink(id);
    });

    window.setTimeout(function() {
        $(".alert-success").fadeTo(500, 0).slideUp(500, function(){
            $(this).remove();
        });
    }, 3000);


</script>
</html>