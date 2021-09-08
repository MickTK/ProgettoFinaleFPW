<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Contatti</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Messaggi con l'amministratore">
        <meta name="keywords" content="avis, contatti, messaggi">
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
        
        <c:if test="${not empty user}">
            <c:set var="page" value="areaPersonale" scope="request"/>
            <jsp:include page="header.jsp"/>
            <br>
            <form id="sub-form" class="bordo-arr center sezione-messaggi" action="contatti" method="post">
                <h3>Nuovo messaggio</h3>
                <br>
                <textarea class='col-12' name="messaggio" id="messaggio" rows="30" maxlength="190"></textarea>
                <br>
                <input class="pulsante-blu sub col-12" type="submit" value="Invia"/>
            </form>

            <div class="sezione-messaggi ricevuto center">
                <br>
                <h3 class="center">Messaggi ricevuti</h3>
                <br>
                <c:forEach items="${ricevuti}" var="p">
                    <div class="col-10 center ricevuto bordo-arr">
                        <br>
                        <h4 class="center">${p.data} - Messaggio dall'amministratore</h4>
                        <br>
                        <p class="center minuto">${p.testo}</p>
                        <br>
                    </div>
                    <br>
                </c:forEach>
            </div>
        </c:if>
            
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/contattiUtenti.js"></script>
    </body>
</html>
