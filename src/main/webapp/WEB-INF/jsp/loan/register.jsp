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
</head>
<body class="bg-light">

<div class="d-flex align-items-center justify-content-center my-auto " style="height: 100vh;">
    <div class="container-fluid col-md-8 ">
        <form class="border border-light rounded p-5" action="loan/create" method="post">
            <h3>Opera��o de Emprestimo</h3>
            <c:if test="${not empty message}">
                <div class="alert ${messageType == 1 ? 'alert-danger' : 'alert-success'} alert-dismissible fade show "
                     role="alert" id="success-alert">
                    <strong>${messageType == 1 ? 'Error - ' : 'Sucesso - '}</strong> ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            </c:if>
            <div class="py-2 my-2 row bg-white border-start  border-5 border-success rounded shadow-sm">
                <h6>Informa��es Cadastrais</h6>
                <div class="col-3 form-group">
                    <label for="status">Status</label>
                    <input type="text" name="status" id="status" value="Em Cadastramento..." class="form-control"
                           readonly>
                </div>
                <div class="col-3 form-group">
                    <label for="startDate">Data de Solicita��o</label>
                    <input id="startDate" name="data" class="form-control" type="date" readonly/>
                </div>
                <div class="col-7 form-group">
                    <label for="costumerSelect">Cliente</label>
                    <select class="form-select" name="costumer" id="costumerSelect">
                        <option value="0" id="0">Selecione</option>
                        <c:forEach items="${costumers}" var="costumer">
                            <c:set var="cpfSemFormato" value="${costumer.cpf}"/>
                            <!-- Extrair os d�gitos do CPF -->
                            <c:set var="cpfParte1" value="${fn:substring(cpfSemFormato, 0, 3)}"/>
                            <c:set var="cpfParte2" value="${fn:substring(cpfSemFormato, 3, 6)}"/>
                            <c:set var="cpfParte3" value="${fn:substring(cpfSemFormato, 6, 9)}"/>
                            <c:set var="cpfParte4" value="${fn:substring(cpfSemFormato, 9, 14)}"/>
                            <!-- CPF formatado -->
                            <c:set var="cpfFormatado" value="${cpfParte1}.${cpfParte2}.${cpfParte3}-${cpfParte4}"/>
                            <option value="${costumer.id}" id="${costumer.tipoCliente}"
                                    data-limit="${costumer.limiteDisponivel}"
                                    data-fgts="${costumer.saldoFgts}"> ${costumer.nome} - Cpf : ${cpfFormatado}"
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
            <div class="py-3 mt-2 mb-2 row  border-start border-5 bg-white border-primary rounded shadow-sm">
                <h6>Informa��es Financeiras</h6>
                <div class=" col-4 form-group ">
                    <label for="rate">Taxa % a.m.</label>
                    <input type="number" id="rate" name="interestRate" step="0.01" min="1.50 "
                           value=""
                           class="form-control text-right" readonly>
                </div>
                <div class=" col-4 form-group">
                    <label for="grace">Car�ncia (em meses)</label>
                    <input type="number" id="grace" name="gracePeriod" step="1" min="1" value="1"
                           class="form-control text-right" readonly>
                </div>
                <div class=" col-4 form-group">
                    <label for="loanTerm">Prazo de Pagamento (em meses)</label>
                    <input type="number" id="loanTerm" name="loanTerm" step="1" min="1"
                           value="1" class="form-control text-right" readonly>

                </div>
                <div class=" col-4 form-group ">
                    <label for="totalFgts">Fgts</label>
                    <input type="number" id="totalFgts" name="totalFgts" step="0.01" min="0.00"
                           value="0.00"
                           class="form-control text-right" readonly>
                </div>
                <div class="col-4 form-group ">
                    <label for="totalRequired">Valor Pretendido</label>
                    <div class="input-group ">
                        <input type="number" name="totalRequired" id="totalRequired" step="0.01" min="0.00"
                               value="0.00" class="form-control text-right" <%--aria-describedby="totalRequiredB"--%>>
                        <button class="btn btn-primary" type="button" onclick="updateLimitLoan()" id="totalRequiredB">
                            Verificar Limite
                        </button>
                    </div>
                </div>
                <div class=" col-4 form-group ">
                    <label for="totalLoan">Valor Liberado</label>
                    <input type="number" id="totalLoan" name="totalLoan" step="0.01" min="0.00"
                           value="0.00"
                           class="form-control text-right" readonly>
                </div>

            </div>
            <div class=" mt-3 d-flex justify-content-end">
                <button class="btn btn-danger me-2" type="button" onclick="getInstallments()">Calcular Parcelas</button>
            </div>
            <div class="py-3 mt-2 mb-2 row  border-start border-5 bg-white border-danger rounded shadow-sm">
                <h6>Parcelas</h6>
                <div class="overflow-auto" style="max-height: 2500px; height: 250px;">
                    <table id="tabelaParcelas" class="table table-striped table-hover border border-light rounded">
                        <thead>
                        <tr>
                            <th>Parcela</th>
                            <th>Valor</th>
                            <th>Vencimento</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
            <div class=" mt-3 d-flex justify-content-end">
                <button class="btn btn-primary me-2" type="submit">Cadastrar</button>
                <a href="/loan/viewList" class="btn btn-danger ">Cancelar</a>
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
                    option.id = data[i].loanPurpose;

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

    var dataAtual = new Date().toISOString().slice(0, 10);

    document.getElementById("startDate").value = dataAtual;


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

    function updateLimitLoan() {

        var meuSelect = $("#costumerSelect");

        var optionSelecionado = $("#costumerSelect").val();


        if (optionSelecionado > 0) {

            var limiteDisponivel = parseFloat(meuSelect.find("option:selected").data("limit"));


            var valorSolicitado = document.getElementById("totalRequired").value;


            var inputLimit = document.getElementById("totalLoan");

            var resultado = limiteDisponivel - valorSolicitado;

            var fgtsLim = parseFloat(meuSelect.find("option:selected").data("fgts"));

            var inputFgts = document.getElementById("totalFgts");

            inputFgts.value = fgtsLim;

            if (resultado < 0) {

                inputLimit.value = limiteDisponivel;
                alert("N�o h� limite dispon�vel para atender todo valor solicitado.");

            } else {
                inputLimit.value = valorSolicitado;
                alert("O campo Valor Liberado foi atualizado.");
            }
        } else {
            alert("Selecione o Cliente.")
        }

    }

    function getInstallments() {

        var varTotalLoan = parseFloat($("#totalLoan").val());
        var varGrace = parseInt($("#grace").val());
        var varTerm = parseInt($("#loanTerm").val());
        var varRate = parseFloat($("#rate").val());

        //alert(varGrace +"-"+ varTerm+"-"+varRate+"-"+varTotalLoan)

        if (varTotalLoan > 0 && varGrace > 0 && varRate > 0 && varTerm > 0) {

            $.ajax({
                url: "/getInstalments",

                data: {
                    gracePeriod: varGrace,
                    loanTerm: varTerm,
                    interestRate: varRate,
                    totalLoan: varTotalLoan
                },
                dataType: 'json',
                success: function (data) {
                    // Limpar o conte�do da tabela
                    $("#tabelaParcelas tbody").empty();

                    // Loop pelos dados recebidos
                    for (var i = 0; i < data.length; i++) {
                        // Criar uma nova linha na tabela
                        var row = $("<tr></tr>");

                        // Inserir os dados da parcela na linha da tabela
                        row.append($("<td>" + data[i].installment + "</td>"));
                        row.append($("<td>" + data[i].installmentAmount + "</td>"));
                        row.append($("<td>" + data[i].dueDate + "</td>"));

                        // Adicionar a linha na tabela
                        $("#tabelaParcelas tbody").append(row);
                    }
                },
                error: function (xhr, status, error) {
                    // Tratar erros, se necess�rio
                }
            });
        } else {
            alert("Revise os dados")
        }

    }

    // document.getElementById("totalRequiredB").addEventListener("click", updateLimitLoan());
</script>
</html>