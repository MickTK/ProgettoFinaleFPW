<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Area Amministrazione</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Area dell'amministratore">
        <meta name="keywords" content="avis, amministratore, amministrazione">
        <link rel="icon" href="images/favicon.png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="styles/base.css" media="screen">
        <style>
            button {
                height: 50px;
            }
            br {
                margin-top: 30px;
            }
        </style>
    </head>
    <body>
        <c:if test="${not empty user}">
            <c:redirect url="../areapersonale"/>
        </c:if>
        
        <c:if test="${empty user_amm}">
            <c:redirect url="index"/>
        </c:if>
        
        <c:if test="${not empty user_amm}">
            <h3 class='center'>Area Amministratore</h3>
            <div class="center">
                <button class="col-6 pulsante-blu bordo-arr"><a style="color: white;" href="SessioniAmministratoreServlet">Aggiungi/visualizza sessioni</a></button>
                <br>
                <button class="col-6 pulsante-blu bordo-arr"><a style="color: white;" href="DonazioniEffettuateServlet">Registra le prenotazioni passate</a></button>
                <br>
                <button class="col-6 pulsante-blu bordo-arr"><a style="color: white;" href="ListaUtentiServlet">Lista donatori</a></button>
                <br>
                <button class="col-6 pulsante-blu bordo-arr"><a style="color: white;" href="MediciAmministratoreServlet">Registra/visualizza medici</a></button>
                <br>
                <button class="col-6 pulsante-blu bordo-arr"><a style="color: white;" href="MessaggiAmministratoreServlet">Messaggi</a></button>
                <br>
                <button class="col-6 pulsante-blu bordo-arr"><a style="color: white;" href="logoutAmministratore">Effettua il logout</a></button>
                <br>
            </div>
        </c:if>
    </body>
</html>
