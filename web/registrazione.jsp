<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <!-- Registrazione / Modifica -->
        <c:if test="${empty user}">
            <title>Registrazione</title>
        </c:if>
        <c:if test="${not empty user}">
            <title>Modifica</title>
        </c:if>
       <meta name="author" content="Melis Michele">
        <meta name="description" content="Registrazione/modifica dati utente">
        <meta name="keywords" content="avis, registrazione, modifica, utente">
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
        <!-- Registrazione / Modifica -->
            <c:if test="${empty user}">
                <h3 class="center">Registrazione</h3>
            </c:if>
            <c:if test="${not empty user}">
                <h3 class="center">Modifica</h3>
            </c:if>
                <br>
        <!-- Registrazione / Modifica -->
        <c:if test="${empty user}">
            <form id="sub-form" class="reg-mod center col-6 bordo-arr" action="registrazione" method="post" enctype="multipart/form-data">
        </c:if>
        <c:if test="${not empty user}">
            <form class="reg-mod center col-6 bordo-arr" action="modifica" method="post" enctype="multipart/form-data">
        </c:if>
            <div>
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" value="${nome}"/>
            </div>
            <div>
            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" value="${cognome}"/>
            </div>
            <div>
                <label for="sesso">Sesso</label>
                <select id="sesso" name="sesso">
                    <option value="${sesso}" selected hidden>${sesso}</option>
                    <option value="M">M</option>
                    <option value="F">F</option>
                </select>
            </div>
            <div>
                <label for="gruppoSanguigno">Gruppo sanguigno</label>
                <select id="gruppoSanguigno" name="gruppoSanguigno">
                    <option value="${gruppoSanguigno}" selected hidden>${gruppoSanguigno}</option>
                    <option value="a+">A Rh D positivo (A+)</option>
                    <option value="a-">A Rh D negativo (A-)</option>
                    <option value="b+">B Rh D positivo (B+)</option>
                    <option value="b-">B Rh D negativo (B-)</option>
                    <option value="0+">0 Rh D positivo (0+)</option>
                    <option value="0-">0 Rh D negativo (0-)</option>
                    <option value="ab+">AB Rh D positivo (AB+)</option>
                    <option value="ab-">AB Rh D negativo (AB-)</option>
                </select>
            </div>
            <div>
                <label for="patologie">Patologie</label>
                <textarea class="col-9" rows="5" id="patologie" name="patologie" maxlength="200">${patologie}</textarea>
            </div>
            <div>
                <label for="dataDiNascita">Data di nascita</label>
                <input type="date" id="dataDiNascita" name="dataDiNascita"  value="${dataDiNascita}">
            </div>
            <!-- Registrazione / Modifica -->
            <c:if test="${empty user}">
                <div>
                <label for="codiceFiscale">Codice fiscale</label>
                <input type="text" id="codiceFiscale" name="codiceFiscale" value=""/>
                </div>
            </c:if>
            <div>
                <label for="email">E-mail</label>
                <input type="text" id="email" name="email"  value="${email}"/>
            </div>
            <div>
                <label for="cellulare">Cellulare</label>
                <input type="text" id="cellulare" name="cellulare"  value="${cellulare}"/>
            </div>
            <div>
                <label for="file">Fotografia</label>
                <input name="file" id="file" type="file" accept="image/*">
            </div>
            <!-- Registrazione / Modifica -->
            <c:if test="${empty user}">
                <div>
                    <label for="user">Username</label>
                    <input type="text" id="user" name="user" value=""/>
                </div>
            </c:if>
            <div>
            <label for="pass">Password</label>
            <input type="password" id="pass" name="pass"   value="${pass}"/>
            </div>
            
            <!-- Registrazione / Modifica -->
            <c:if test="${empty user}">
                <div>
                    <input class="col-12 pulsante-blu bordo-arr" id="pulsanteRegistrazione" type="submit" value="Registrati"/>
                </div>
            </c:if>
            <c:if test="${not empty user}">
                <div>
                    <input class="col-12 pulsante-blu bordo-arr" type="submit" value="Modifica"/>
                </div>
            </c:if>
            
        </form>
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/script.js"></script>
            
    </body>
</html>
