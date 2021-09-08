<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login come amministratore</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Pagina di login per l'amministratore">
        <meta name="keywords" content="avis, amministratore, amministrazione">
        <link rel="icon" href="../images/favicon.png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="../styles/base.css" media="screen">
    </head>
    <body>
        <c:if test="${not empty user}">
            <c:redirect url="../areapersonale"/>
        </c:if>
        
        <c:if test="${not empty user_amm}">
            <c:redirect url="AreaAmministratoreServlet"/>
        </c:if>
        
        <button class="pulsante-blu bordo-arr"><a style="color: white;" href="../login.jsp">Torna indietro</a></button>
        <br>
          <form class="col-6 center bordo-arr" action="../loginAmministratore" method="post">
                <h3 class="center">Accedi come amministratore</h3>
                <br>
                <div>
                    <div style="display:block;">
                        <label for="user_amm">Username</label>
                        <input type="text" id="user_amm" name="user_amm"/>
                    </div>
                    <div style="display:block;">
                        <label for="pass">Password</label>
                        <input type="password" id="pass" name="pass"/>
                    </div>
                </div>
                <br>
                <input class="pulsante-blu col-12" type="submit" value="Accedi"/>
            </form>
        
    </body>
</html>
