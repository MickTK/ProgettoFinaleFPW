<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sessioni</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Aggiunta sessioni">
        <meta name="keywords" content="avis, amministratore, amministrazione, sessioni">
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
        <br>
        <h3 class='center'>Nuova sessione</h3>
        <br>
        <form id="sub-form" class="col-6 bordo-arr center" action="SessioniAmministratoreServlet" method="post">
            <div>
                <label for="data">Data</label>
                <input type="date" id="data" name="data" value="2000-10-10">
            </div>
            <div>
                <label for="inizio">Ora di inizio</label>
                <input type="text" id="inizio" name="inizio" value="07.30"/>
            </div>
            <div>
                <label for="fine">Ora di fine</label>
                <input type="text" id="fine" name="fine" value="12.00"/>
            </div>
            <div>
                <label for="luogo">Luogo</label>
                <input type="text" id="luogo" name="luogo"/>
            </div>
            <div>
            </div>
            <div>
                <input style='margin-top: 10px;' class="col-12 sub pulsante-blu" type="submit" value="Crea nuova sessione"/>
            </div>
        </form>
        
        <br><br>
        <h3 class='center'>Sessioni presenti nel database</h3>
        <br>
        <c:forEach items="${sessione}" var="a">
            <table class="col-12 bordo-arr center">
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
                <br>
        </c:forEach>
                
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/nuovaSessione.js"></script>
    </body>
</html>
