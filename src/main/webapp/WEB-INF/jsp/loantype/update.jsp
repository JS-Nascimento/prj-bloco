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
    <div class="container-fluid col-md-8">
        <form class="border border-light rounded p-5" action="/costumer/save/${costumer.id}" method="post">
            <h3>Variação de Operação</h3>
            <c:if test="${not empty costumer}">
                <div class="py-2 my-2 row  border-start bg-white  border-5 border-success rounded shadow-sm">
                    <h6>Informações Cadastrais</h6>
                    <div class="col-1 form-group">
                        <label>Id </label>
                        <input type="text" name="id" value="${costumer.id}" disabled class="form-control">
                    </div>
                    <div class="col-5 form-group">
                        <label>Nome</label>
                        <input type="text" name="nome" value="${costumer.nome}" class="form-control" required>
                    </div>
                    <div class="col form-group">
                        <label for="tipoCliente">Tipo de Cliente</label>>
                        <select class="form-select" id="tipoCliente" name="tipoCliente">
                            <option value="APOSENTADOS" ${costumer.tipoCliente == 'APOSENTADOS' ? 'selected' : ''}>
                                Aponsentados e Pensionistas
                            </option>
                            <option value="SERVIDORES" ${costumer.tipoCliente == 'SERVIDORES' ? 'selected' : ''}>
                                Servidores Públicos
                            </option>
                            <option value="FGTS" ${costumer.tipoCliente == 'FGTS' ? 'selected' : ''}>Antecipação do
                                FGTS
                            </option>
                        </select>
                    </div>
                    <div class="col form-group">
                        <label>CPF</label>
                        <input type="text" name="cpf" value="${costumer.cpf}" class="form-control" required>
                    </div>

                    <div class="col form-group">
                        <label>Idade</label>
                        <input type="number" name="idade" step="1" min="1" value="${costumer.idade}"
                               class="form-control text-right">
                    </div>
                </div>

                <div class="py-3 mt-4 mb-2 row  border-start border-5 bg-white border-primary rounded shadow-sm">
                    <h6>Informações Financeiras</h6>
                    <div class=" col-4 form-group ">
                        <label>Renda Total</label>
                        <input type="number" name="rendaTotal" step="0.01" min="0.00" value="${costumer.rendaTotal}"
                               class="form-control text-right">
                    </div>
                    <div class=" col-4 form-group">
                        <label>Saldo FGTS</label>
                        <input type="number" name="saldoFgts" step="0.01" min="0.00" value="${costumer.saldoFgts}"
                               class="form-control text-right">
                    </div>
                    <div class=" col-4 form-group">
                        <label>Limite Total Total</label>
                        <input type="number" name="limiteTotal" step="0.01"
                               min="0.00" ${user.nivelAcesso != 3 ? 'disabled' : ''} value="${costumer.limiteTotal}"
                               class="form-control text-right">
                    </div>
                    <div class=" col-4 form-group">
                        <label>Limite Total Disponível</label>
                        <input type="number" name="limiteDisponivel" step="0.01"
                               min="0.00" ${user.nivelAcesso != 3 ? 'disabled' : ''}
                               value="${costumer.limiteDisponivel}"
                               class="form-control text-right">
                    </div>
                    <div class="col-4 form-group">
                        <label>Score</label>
                        <input type="number" name="score" step="1" min="1" value="${costumer.score}"
                               class="form-control text-right">
                    </div>
                    <div class="col-4 form-group">
                        <label>Indimplência Recorrente</label>
                        <select class="form-select" name="recompra">
                            <option selected value="N" ${costumer.recompra == 'N' ? 'selected' : ''}>Não</option>
                            <option value="S" ${costumer.recompra == 'S' ? 'selected' : ''}>Sim</option>
                        </select>
                    </div>
                </div>
            </c:if>
            <div class=" mt-3 d-flex justify-content-end">
                <button class="btn btn-primary me-2" type="submit">Cadastrar</button>
                <a href="/costumer/viewList" class="btn btn-danger ">Cancelar</a>
            </div>
        </form>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>