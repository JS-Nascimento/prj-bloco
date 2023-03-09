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
    <form class="border border-light rounded p-5" action="costumer/create" method="post">
        <h3>Cadastro de Solicitantes</h3>
        <div class="py-2 my-2 row  border-start  border-5 border-success rounded shadow-sm">
        <h6>Informações Cadastrais</h6>

        <div class="form-group">
            <label>Nome</label>
            <input type="text" name="nome" value="Jorge Nascimento" class="form-control">
        </div>

            <div class="col form-group">
                <label>CPF</label>
                <input type="text" name="cpf" value="123" class="form-control">
            </div>

            <div class="col form-group">
                <label>Idade</label>
                <input type="number" name="idade" step="1" min="1" value="1" class="form-control text-right">
            </div>
        </div>

        <div class="py-3 mt-4 mb-2 row  border-start border-5  border-primary rounded shadow-sm">
            <h6>Informações Financeiras</h6>
            <div class=" col form-group ">
                <label>Renda Total</label>
                <input type="number" name="rendaTotal" step="0.01" min="0.00" value="0.00" class="form-control text-right">
            </div>
            <div class=" col form-group">
                <label>Saldo FGTS</label>
                <input type="number" name="saldoFgts" step="0.01" min="0.00" value="0.00" class="form-control text-right">
            </div>
            <div class=" col form-group">
                <label>Limite Total Total</label>
                <input type="number" name="limiteTotal" step="0.01" min="0.00" value="0.00" class="form-control text-right">
            </div>
            <div class=" col form-group">
                <label>Limite Total Disponível</label>
                <input type="number" name="limiteDisponivel" step="0.01" min="0.00" value="0.00" class="form-control text-right">
            </div>
            <div class="col form-group">
                <label>Score</label>
                <input type="number" name="score" step="1" min="1" value="0" class="form-control text-right">
            </div>
            <div class="col form-group">
                <label>Indimplência Recorrente</label>
                <select class="form-select" >
                    <option selected value="N">Não</option>
                    <option value="S">Sim</option>
                </select>
            </div>
        </div>

        <div class=" mt-3 d-flex justify-content-end">
            <button class="btn btn-primary me-2" type="submit">Cadastrar</button>
            <a href="/home" class="btn btn-danger " >Cancelar</a>
        </div>
    </form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>