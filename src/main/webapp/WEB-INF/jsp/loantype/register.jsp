<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>EmprestaNet</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.14/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bg-light">

<div class="d-flex align-items-center justify-content-center my-auto " style="height: 70vh;">
    <div class="container-fluid col-md-8 ">
        <form class="border border-light rounded p-5" action="loantype/create" method="post">
            <h3>Nova Variação de Empréstimo</h3>
            <c:if test="${not empty message}">

                <div class="alert ${messageType == 1 ? 'alert-danger' : 'alert-success'} alert-dismissible fade show "
                     role="alert" id="success-alert">
                    <strong>${messageType == 1 ? 'Error - ' : 'Sucesso - '}</strong> ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            </c:if>
            <div class="py-2 my-2 row bg-white border-start  border-5 border-success rounded shadow-sm">
                <h6>Informações Cadastrais</h6>
                <div class="col-5 form-group">
                    <label for="name">Nome</label>
                    <input type="text" name="name" id="name" value="" class="form-control" required>
                </div>
                <div class="col-3 form-group">
                    <label for="loanPurposes"> Modalidades de Empréstimo </label>
                    <select class="form-select" id="loanPurposes" name="loanPurpose" required>
                        <option value="RETIREES" selected>Aponsentados e Pensionistas</option>
                        <option value="SERVANT">Servidores Públicos</option>
                        <option value="FGTS">Antecipação do FGTS</option>
                    </select>
                </div>

            </div>

            <div class="py-3 mt-4 mb-2 row  border-start border-5 bg-white border-primary rounded shadow-sm">
                <h6>Informações Financeiras</h6>
                <div class=" col-4 form-group ">
                    <label for="rate">Taxa % a.m.</label>
                    <input type="number" id="rate" name="interestRate" step="0.01" min="1.50"
                           value="${loanType.interestRate == null ? "2.00" : loanType.interestRate}"
                           class="form-control text-right">
                </div>
                <div class=" col-4 form-group">
                    <label for="grace">Carência (em meses)</label>
                    <input type="number" id="grace" name="gracePeriod" step="1" min="1" value="1"
                           class="form-control text-right">
                </div>
                <div class=" col-4 form-group">
                    <label for="loanTerm">Prazo de Pagamento (em meses)</label>
                    <input type="number" id="loanTerm" name="loanTerm" step="1" min="1"
                           value="1" class="form-control text-right">
                </div>

            </div>

            <div class=" mt-3 d-flex justify-content-end">
                <button class="btn btn-primary me-2" type="submit">Cadastrar</button>
                <a href="/loantype/viewList" class="btn btn-danger ">Cancelar</a>
            </div>
        </form>
    </div>
</div>
</body>

<script>

    window.setTimeout(function () {
        $(".alert-success").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 3000);
    window.setTimeout(function () {
        $(".alert-danger").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 1000);

</script>
</html>