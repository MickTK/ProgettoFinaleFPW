<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Pagina principale AVIS">
        <meta name="keywords" content="avis, sessione, donazione, prenotazione">
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
        
        <jsp:include page="header.jsp"/>
        
        <br>
        <h3 class="center">Sessioni disponibili</h3>
        <br>
        <c:forEach items="${sessione}" var="a">
            <form class="col-9 bordo-blu" action="sessione" method="post">
            <table class="col-12">
                <input class="inv-field" type="text" id="pk_sessione" name="pk_sessione" value="${a.id}" readonly/>
                <tr>
                    <th class="col-3">Data</th>
                    <th class="col-3">Ora di inizio</th>
                    <th class="col-3">Ora di fine</th>
                    <th class="col-3">Luogo</th>
                </tr>
                <tr>
                    <td class="col-3">${a.data}</td>
                    <td class="col-3">${a.inizio}</td>
                    <td class="col-3">${a.fine}</td>
                    <td class="col-3">${a.luogo}</td>
                </tr>
            </table>
            <input class="col-12 pulsante-blu" type="submit" value="Visualizza">
        </form>
                <br>
        </c:forEach>
        
        <script src="scripts/jquery-3.6.0.min.js"></script>
    </body>
</html>
