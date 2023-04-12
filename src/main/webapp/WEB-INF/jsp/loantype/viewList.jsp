<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>EmprestaNet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="d-flex align-items-center justify-content-center" style="height: 100vh; ">
    <div class="container-fluid col-md-8 bg-white  shadow-sm  rounded ">
        <h3 class="mt-3 mb-3">Modalidade Empréstimos</h3>
        <c:if test="${not empty message}">

            <div class="alert ${messageType == 1 ? 'alert-danger' : 'alert-success'} alert-dismissible fade show "
                 role="alert" id="success-alert">
                <strong>${messageType == 1 ? 'Error - ' : 'Sucesso - '}</strong> ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>

        </c:if>

        <form action="/loantype" method="get">
            <button class="btn btn-success mb-3 " type="submit">Nova variação de empréstimo</button>
        </form>
        <c:if test="${empty loans}">
            <h5>Ainda não há empréstimos cadastrados.</h5>
        </c:if>
        <c:if test="${not empty loans}">
            <form action="/loantypefiltered" method="post">
                <div class="col-3 form-group">
                    <label for="loanPurposes"> Filtros </label>
                    <select class="form-select" id="loanPurposes" name="filtro" required>
                        <option value="ALL" selected>Todas as modalidades</option>
                        <option value="RETIREES">Aponsentados e Pensionistas</option>
                        <option value="SERVANT">Servidores Públicos</option>
                        <option value="FGTS">Antecipação do FGTS</option>
                    </select>
                </div>
                <div>
                    <button class="btn btn-secondary m-3 " type="submit">Filtrar</button>
                </div>
            </form>
            <div class="d-flex justify-content-end">
                <div class="my-3 badge bg-primary position-relative ">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-journals" viewBox="0 0 16 16">
                        <path d="M5 0h8a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2 2 2 0 0 1-2 2H3a2 2 0 0 1-2-2h1a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1H1a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v9a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H5a1 1 0 0 0-1 1H3a2 2 0 0 1 2-2z"></path>
                        <path d="M1 6v-.5a.5.5 0 0 1 1 0V6h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V9h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 2.5v.5H.5a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1H2v-.5a.5.5 0 0 0-1 0z"></path>
                    </svg>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                 ${loans.size()}
                <span class="visually-hidden">Registros</span>
             </span>
                </div>
            </div>

            <div class="overflow-auto" style="max-height: 500px; height: 500px;">
                <table class="table table-striped table-hover border border-light rounded">
                    <thead>
                    <tr>
                        <th>Código</th>
                        <th>Tipo</th>
                        <th>Descrição</th>
                        <th>Taxa</th>
                        <th>Carência (em meses)</th>
                        <th>Prazo (em meses)</th>
                        <th>Ações</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="u" items="${loans}">
                        <tr>
                            <td>${u.id}</td>
                            <c:choose>
                                <c:when test="${u.loanPurpose == 'RETIREES'}">
                                    <td><span class="badge rounded-pill bg-danger">Aposentados</span></td>
                                </c:when>
                                <c:when test="${u.loanPurpose == 'SERVANT'}">
                                    <td><span class="badge rounded-pill bg-warning text-dark">Servidores Públicos</span>
                                    </td>
                                </c:when>
                                <c:when test="${u.loanPurpose == 'FGTS'}">
                                    <td><span class="badge rounded-pill bg-success">FGTS</span></td>
                                </c:when>
                            </c:choose>
                            <td>${u.name}</td>
                            <td><fmt:formatNumber value="${u.interestRate}" pattern="#,##0.00"/></td>
                            <td>${u.gracePeriod}</td>
                            <td>${u.loanTerm}</td>
                            <td>
                                    <%--<a class="btn btn-primary" href="/loantype/update/${u.id}" data-id="${u.id}"
                                       data-id-nome="${u.name}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-save" viewBox="0 0 16 16">
                                            <path d="M2 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H9.5a1 1 0 0 0-1 1v7.293l2.646-2.647a.5.5 0 0 1 .708.708l-3.5 3.5a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L7.5 9.293V2a2 2 0 0 1 2-2H14a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h2.5a.5.5 0 0 1 0 1H2z"></path>
                                        </svg>
                                    </a>--%>
                                <button type="button"
                                        class="btn btn-danger"  ${userLogged.nivelAcesso == 2 ? 'disabled' : ''}
                                        data-id="${u.id}" data-id-nome="${u.name}" data-bs-toggle="modal"
                                        data-bs-target="#staticBackdrop">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-trash-fill" viewBox="0 0 16 16">
                                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                                    </svg>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </c:if>
        <div class="d-flex justify-content-end">
            <a href="/home" class="btn btn-secondary mb-3">Voltar</a>
        </div>

        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
        return 'Confirma a exclusão do cliente ' + nomeTagged + ' ?';
    }


    function updateDeleteLink(id) {
        link.attr('href', '/loantype/delete/' + id);
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