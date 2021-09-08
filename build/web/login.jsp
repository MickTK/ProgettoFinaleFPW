<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Pagina di login">
        <meta name="keywords" content="avis, login, donazione, utente">
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
        
        <c:if test="${not empty user}">
            <c:redirect url="index"/>
        </c:if>
        
        <jsp:include page="header.jsp"/>
        
        <br>
            <form class="login-dim center bordo-arr" action="login" method="post">
                <h3 class="center">Accedi</h3>
                <br>
                <div>
                    <div style="display:block;">
                        <label for="user">Username</label>
                        <input style="margin-bottom: 10px;" type="text" id="user" name="user"/>
                    </div>
                    <div style="display:block;">
                        <label for="pass">Password</label>
                        <input type="password" id="pass" name="pass"/>
                    </div>
                </div>
                <br>
                <input class="pulsante-blu col-12" type="submit" value="Accedi"/>
            </form>
            <br>
            <form class="login-dim center bordo-arr" action="registrazione.jsp" method="post">
                <br>
                <h3 class="center">Registrazione</h3>
                <p class="center col-12">Non sei ancora registrato? Registrati ora!</p>
                <br>
                <input class="pulsante-blu col-12" type="submit" value="Registrati"/>
            </form>
            <br>
            <br>
        <p class="minuto center" style="clear: left;"><a href="Amministratore/login.jsp">Accedi come amministratore</a></p>
    </body>
</html>
