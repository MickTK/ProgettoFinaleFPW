<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Area Personale</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Pagina principale AVIS">
        <meta name="keywords" content="avis, utente, modifica, personale">
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
            <h3 class="center">Area Personale</h3>
            <br>
            <form class="bordo-blu col-5" action="registrazione.jsp" method="post">
                <div>
                    <img class="col-4" src="${foto}" title="Foto del profilo" alt="Foto del profilo">
                    <p><b>Nome: </b>${nome}</p>
                    <p><b>Cognome: </b>${cognome}</p>
                    <p><b>Sesso: </b>${sesso}</p>
                    <p><b>Codice fiscale: </b>${codiceFiscale}</p>
                    <p><b>Gruppo sanguigno: </b>${gruppoSanguigno}</p>
                    <p><b>Patologie: </b>${patologie}</p>
                    <p><b>Data di nascita </b>${dataDiNascita}</p>
                    <p><b>E-mail: </b>${email}</p>
                    <p><b>Cellulare: </b>${cellulare}</p>
                    <p><b>Username: </b>${user}</p>
                </div>

                <!-- Modifica dati -->
                <input class="col-12 pulsante-blu" type="submit" value="Modifica">
            </form>
                
                <br>
                
                <form class="col-5 center"  action="cronologia" method="post">
                    <input class="col-12 bordo-arr pulsante-blu" type="submit" value="Registro donazioni"/>
                </form>
                
                <br>
                
                <form class="col-5 center"  action="logout" method="post">
                    <input class="col-12 bordo-arr pulsante-blu" type="submit" value="Logout"/>
                </form>
        </c:if>
    </body>
</html>
