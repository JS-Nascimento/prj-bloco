<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <div class="container-fluid col-md-8 ">
        <form class="border border-light rounded p-5" action="costumer/create" method="post">
            <h3>Cadastro de Solicitantes</h3>
            <div class="py-2 my-2 row bg-white border-start  border-5 border-success rounded shadow-sm">
                <h6>Informações Cadastrais</h6>
                <div class="col-5 form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome" value="" class="form-control" required>
                </div>
                <div class="col-3 form-group">
                    <label for="tipoCliente"> Tipo de Cliente </label>
                    <select class="form-select" id="tipoCliente" name="tipoCliente">
                        <option value="">-- Selecione --</option>
                        <option value="APOSENTADOS">Aponsentados e Pensionistas</option>
                        <option value="SERVIDORES">Servidores Públicos</option>
                        <option value="FGTS">Antecipação do FGTS</option>
                    </select>
                </div>
                <div class="col form-group">
                    <label for="cpf">CPF</label>
                    <input type="text" id="cpf" name="cpf" value="" class="form-control" required>
                </div>

                <div class="col form-group">
                    <label for="idade">Idade</label>
                    <input type="number" id="idade" name="idade" step="1" min="1" value="1"
                           class="form-control text-right">
                </div>
            </div>

            <div class="py-3 mt-4 mb-2 row  border-start border-5 bg-white border-primary rounded shadow-sm">
                <h6>Informações Financeiras</h6>
                <div class=" col-4 form-group ">
                    <label for="rendatotal">Renda Total</label>
                    <input type="number" id="rendatotal" name="rendaTotal" step="0.01" min="0.00" value="0.00"
                           class="form-control text-right">
                </div>
                <div class=" col-4 form-group">
                    <label>Saldo FGTS</label>
                    <input type="number" name="saldoFgts" step="0.01" min="0.00" value="0.00"
                           class="form-control text-right">
                </div>
                <div class=" col-4 form-group">
                    <label>Limite Total Total</label>
                    <input type="number" name="limiteTotal" step="0.01" min="0.00"
                           value="0.00" ${user.nivelAcesso == 2 ? 'readonly' : ''} class="form-control text-right">
                </div>
                <div class=" col-4 form-group">
                    <label>Limite Total Disponível</label>
                    <input type="number" name="limiteDisponivel" step="0.01" min="0.00"
                           value="0.00" readonly class="form-control text-right">
                </div>
                <div class="col-4 form-group">
                    <label>Score</label>
                    <input type="number" name="score" step="1" min="1" value="1" class="form-control text-right">
                </div>
                <div class="col-4 form-group">
                    <label>Indimplência Recorrente</label>
                    <select class="form-select" name="recompra">
                        <option selected value="N">Não</option>
                        <option value="S">Sim</option>
                    </select>
                </div>
            </div>

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