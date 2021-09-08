<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sessione</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Pagina prenotazione">
        <meta name="keywords" content="avis, prenotazione, donazione, sessione, data">
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
        <c:if test="${empty ora}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <jsp:include page="header.jsp"/>
        
        <form class="login-dim bordo-arr center" action="prenotazione" method="post">
            <input class="inv-field" style="display: none;" type="text" id="pk_sessione" name="pk_sessione" value="${pk_sessione}" readonly/>
            <div>
                <p><b>Data</b></p>
                <p>${sessione.data}</p>
                <p><b>Ora di inizio</b></p>
                <p>${sessione.inizio}</p>
                <p><b>Ora di fine</b></p>
                <p>${sessione.fine}</p>
                <p><b>Luogo</b></p>
                <p>${sessione.luogo}</p>
            </div>
            <label for="orarioPrenotazione"><b>Seleziona l'orario</b></label>
            <select id="orarioPrenotazione" name="orarioPrenotazione">
                <c:forEach items="${ora}" var="o">
                    <option value="${o}">${o}</option>
                </c:forEach>
            </select>
            <br><br>

            <!-- Effettua prenotazione -->
            <input class="col-12 pulsante-blu" type="submit" value="Prenotati">
        </form>
        
        <script src="scripts/jquery-3.6.0.min.js"></script>
        <script src="scripts/script.js"></script>
    </body>
</html>
