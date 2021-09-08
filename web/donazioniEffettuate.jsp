<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro donazioni</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Registro donazioni effettuate e da effettuare">
        <meta name="keywords" content="avis, registro, cronologia, donazioni, prenotazioni">
        <link rel="icon" href="images/favicon.png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="styles/base.css" media="screen">
    </head>
    <body>
        <c:if test="${not empty user_amm}">
            <c:redirect url="AreaAmministratoreServlet"/>
        </c:if>
        
        <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <jsp:include page="header.jsp"/>
        <br>
        <h3 class="center">Prenotazioni</h3>
        <c:if test="${empty prenotazione}">
            <p class="center">Nessuna prenotazione</p>
        </c:if>
        <c:forEach items="${prenotazione}" var="p">
            <table class="col-7 bordo-blu">
            <tr>
                <th class="col-4">Data</th>
                <th class="col-4">Ora</th>
                <th class="col-4">Luogo</th>
            </tr>
            <tr>
                <td class="col-4">${p.sessione.data}</td>
                <td class="col-4">${p.orario}</td>
                <td class="col-4">${p.sessione.luogo}</td>
            </tr>
        </table>
        <br>
        </c:forEach>
        
        <br>
        <h3 class="center">Donazioni effettuate</h3>
        <c:if test="${empty effettuate}">
            <p class="center">Nessuna donazione effettuata</p>
        </c:if>
        <c:forEach items="${effettuate}" var="e">
            <div>
            <table class="col-7 bordo-blu">
            <tr>
                <th class="col-4">Data</th>
                <th class="col-4">Ora</th>
                <th class="col-4">Luogo</th>
            </tr>
            <tr>
                <td class="col-4">${e.sessione.data}</td>
                <td class="col-4">${e.orario}</td>
                <td class="col-4">${e.sessione.luogo}</td>
            </tr>
        </table>
        </div>
        <br>
        </c:forEach>
        <br>
        <p>Nota bene: è possibile donare solo 4 volte (se sei uomo) o 2 volte (se sei donna) l'anno</p>
        <p>In ogni caso tra una donazione e la successiva devono passare minimo 90 giorni!</p>
        
         <script src="scripts/jquery-3.6.0.min.js"></script>
    </body>
</html>
