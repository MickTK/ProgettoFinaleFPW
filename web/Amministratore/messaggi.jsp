<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Messaggi</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Messaggi scambiati con gli utenti">
        <meta name="keywords" content="avis, amministratore, amministrazione, messaggi">
        <link rel="icon" href="images/favicon.png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="styles/base.css" media="screen">
    </head>
    <body>
        <c:if test="${not empty user}">
            <c:redirect url="../areapersonale"/>
        </c:if>
        
        <c:if test="${empty user_amm}">
            <c:redirect url="index"/>
        </c:if>
        
        <button class="pulsante-blu bordo-arr"><a style="color: white;" href="AreaAmministratoreServlet">Torna indietro</a></button>
        <h1></h1>

            <form id="sub-form" class="bordo-arr center sezione-messaggi" action="MessaggiAmministratoreServlet" method="post">
                <h3>Nuovo messaggio</h3>
                <br>
                <hr>
                <p class="center"><b>Destinatario</b></p>
                <input type="radio" name="utente-destinatario" value="disponibili">Utenti disponibili<br>
                <input type="radio" name="utente-destinatario" value="utente" checked>Utente<br>
                <input type="radio" name="utente-destinatario" value="data">Data<br>
                <hr>
                <br>
                <label class="data" for="data">Reminder per gli utenti in data: </label>
                <select class="data" id="data" name="data">
                    <c:forEach items="${sessione}" var="s">
                        <option value="${s.data}">${s.data}</option>
                    </c:forEach>
                </select>
                
                <label class="txt" for="destinatario">Username del destinatario: </label>
                <input class="txt" type="text" id="destinatario" name="destinatario" value="${destinatario}"/>
                
                <textarea style="margin-top:20px" class="col-12" name="messaggio" id="messaggio" cols="30" rows="30" maxlength="190"></textarea>
                <input class="sub col-12 pulsante-blu" type="submit" value="Invia"/>
            </form>

            <div class="sezione-messaggi ricevuto center ricevuto">
                <h3>Ricevuti</h3>
                <br>
                <c:forEach items="${ricevuti}" var="p">
                    <div class="col-10 ricevuto center bordo-arr">
                        <h4>${p.data} - ${p.utente.username}</h4>
                        <p>${p.testo}</p>
                    </div>
                    <br>
                </c:forEach>
            </div>
            
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/messaggiAmministratore.js"></script>
    </body>
</html>
