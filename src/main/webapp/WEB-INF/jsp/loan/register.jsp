<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>EmprestaNet</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.14/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bg-light">

<div class="d-flex align-items-center justify-content-center my-auto " style="height: 70vh;">
    <div class="container-fluid col-md-8 ">
        <form class="border border-light rounded p-5" action="loantype/create" method="post">
            <h3>Operação de Emprestimo</h3>
            <c:if test="${not empty message}">
                <div class="alert ${messageType == 1 ? 'alert-danger' : 'alert-success'} alert-dismissible fade show "
                     role="alert" id="success-alert">
                    <strong>${messageType == 1 ? 'Error - ' : 'Sucesso - '}</strong> ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            </c:if>
            <div class="py-2 my-2 row bg-white border-start  border-5 border-success rounded shadow-sm">
                <h6>Informações Cadastrais</h6>
                <div class="col-3 form-group">
                    <label for="status">Status</label>
                    <input type="text" name="name" id="status" value="Em Cadastramento..." class="form-control" required
                           disabled>
                </div>
                <div class="col-3 form-group">
                    <label for="startDate">Data de Solicitação</label>
                    <input id="startDate" class="form-control" type="date"/>

                </div>

                <div class="col-7 form-group">
                    <label for="costumerSelect">Cliente</label>
                    <select class="form-select" name="costumer" id="costumerSelect">
                        <option value="0" id="0">Selecione</option>
                        <c:forEach items="${costumers}" var="costumer">
                            <c:set var="cpfSemFormato" value="${costumer.cpf}"/>
                            <!-- Extrair os dígitos do CPF -->
                            <c:set var="cpfParte1" value="${fn:substring(cpfSemFormato, 0, 3)}"/>
                            <c:set var="cpfParte2" value="${fn:substring(cpfSemFormato, 3, 6)}"/>
                            <c:set var="cpfParte3" value="${fn:substring(cpfSemFormato, 6, 9)}"/>
                            <c:set var="cpfParte4" value="${fn:substring(cpfSemFormato, 9, 14)}"/>
                            <!-- CPF formatado -->
                            <c:set var="cpfFormatado" value="${cpfParte1}.${cpfParte2}.${cpfParte3}-${cpfParte4}"/>
                            <option value="${costumer.id}" id="${costumer.tipoCliente}"> ${costumer.nome} - Cpf
                                : ${cpfFormatado}"
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-5 form-group">
                    <label for="loanTypes"> Modalidades liberadas </label>
                    <select class="form-select" id="loanTypes" name="loanType" required>
                        <option value="0" selected>Selecione o Cliente primeiro...</option>

                    </select>
                </div>

            </div>

            <div class="py-3 mt-4 mb-2 row  border-start border-5 bg-white border-primary rounded shadow-sm">
                <h6>Informações Financeiras</h6>
                <div class=" col-4 form-group ">
                    <label for="rate">Taxa % a.m.</label>
                    <input type="number" id="rate" name="interestRate" step="0.01" min="1.50 "
                           value=""
                           class="form-control text-right" readonly>
                </div>
                <div class=" col-4 form-group">
                    <label for="grace">Carência (em meses)</label>
                    <input type="number" id="grace" name="gracePeriod" step="1" min="1" value="1"
                           class="form-control text-right" readonly>
                </div>
                <div class=" col-4 form-group">
                    <label for="loanTerm">Prazo de Pagamento (em meses)</label>
                    <input type="number" id="loanTerm" name="loanTerm" step="1" min="1"
                           value="1" class="form-control text-right" readonly>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

    var select1 = document.getElementById("costumerSelect");
    var novoValor;
    select1.addEventListener("change", function (event) {

        novoValor = event.target.options[event.target.selectedIndex].id;

        updateSelectLoanType()

    });


    function updateSelectLoanType() {

        $.ajax({
            url: "/updateSelectLoanType",
            data: {valorTipoCliente: novoValor},
            success: function (data) {
                $("#loanTypes").empty();

                for (var i = 0; i < data.length; i++) {
                    var option = document.createElement("option");
                    option.value = data[i].id;
                    option.text = data[i].name;

                    select2.add(option);

                }
                updateFieldsLoanType(data[0].id)
            }
        });
    }

    var select2 = document.getElementById("loanTypes");
    var novaModalidade;
    select2.addEventListener("change", function (event2) {
        novaModalidade = event2.target.options[event2.target.selectedIndex].value;

        updateFieldsLoanType(novaModalidade)

    });

    function updateFieldsLoanType(idModalidade) {


        $.ajax({
            url: "/updateFieldsLoanType",
            data: {valorModalidade: idModalidade},
            success: function (data) {

                var inputRate = document.getElementById("rate");
                inputRate.value = data.interestRate;


                var inputGrace = document.getElementById("grace");
                inputGrace.value = data.gracePeriod;

                var inputTerm = document.getElementById("loanTerm");
                inputTerm.value = data.loanTerm;

            }
        });
    }
</script>
</html>