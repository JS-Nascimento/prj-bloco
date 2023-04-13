<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EmprestaNet</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <c:if test="${not empty userLogged}">


                <li class="nav-item  ">
                    <a class="nav-link " aria-current="page" href="/costumer/viewList">Clientes</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-bs-toggle="dropdown">Modalidades de Empréstimos</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/loantype/viewList">Listagem</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="/loan/viewList">Efetivação</a>
                </li>
            </ul>
            </c:if>
            <c:if test="${not empty userLogged}">
            <ul class="nav navbar-nav navbar-right ms-auto">
                <div class="btn-group dropstart">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown"
                            aria-expanded="false">${userLogged.nome}</button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item " href="/user/viewList">Cadastro</a></li>
                        <li><a class="dropdown-item " href="#">Trocar Senha</a></li>
                        <li><a class="dropdown-item " href="/logout">Logout</a></li>
                    </ul>
                </div>
                </c:if>
                <c:if test="${empty userLogged}">
                    <li class="navbar-right nav-item">
                        <a class="nav-link" href="/login">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                                <path fill-rule="evenodd"
                                      d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                                <path fill-rule="evenodd"
                                      d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                            </svg>
                            LogIn</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>


