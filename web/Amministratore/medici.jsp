<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medici</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Aggiungi nuovi medici nel database">
        <meta name="keywords" content="avis, amministratore, medici">
        <link rel="icon" href="images/favicon.png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="styles/base.css" media="screen">
        <style>
            table {
                table-layout: fixed;
            }
            .tb {
                border: none; 
                border-collapse: collapse;
            }
            .tb td, .tb th { 
                border-left: 1px solid #000;
            }
            td {
                word-wrap: break-word;
            }
            .tb td:first-child,  .tb th:first-child { 
                border-left: none; 
            }
            .tb .tr-bottom{
                border-bottom: solid;
                border-width: 1px 0;
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
        
        <button class="pulsante-blu bordo-arr"><a style="color: white;" href="AreaAmministratoreServlet">Torna indietro</a></button>
        <br>
        <h3 class="center">Registra un nuovo medico</h3>
        <br>
        <form id="sub-form" class="col-6 bordo-arr center" action="MediciAmministratoreServlet" method="post">
            <div>
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome">
            </div>
            <div>
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome"/>
            </div>
            <div>
                <label for="cellulare">Cellulare</label>
                <input type="text" id="cellulare" name="cellulare"/>
            </div>
            <div>
                <label for="email">E-mail</label>
                <input type="text" id="email" name="email"/>
            </div>
            <div>
                <label for="asl">Asl di appartenenza</label>
                <input type="text" id="asl" name="asl"/>
            </div>
            <div class="col-12">
                <input class="sub col-12 pulsante-blu" type="submit" value="Aggiungi medico"/>
            </div>
        </form>
        
        <br>
        <h3 class="center">Medici registrati</h3>
        <br>
        <c:forEach items="${medici}" var="medico">
            <table class="col-12 bordo-arr center">
                <tr>
                    <th class="col-1">Nome</th>
                    <th class="col-1">Cognome</th>
                    <th class="col-1">Cellulare</th>
                    <th class="col-1">E-mail</th>
                    <th class="col-1">ASL</th>
                </tr>
                <tr>
                    <td class="col-1">${medico.nome}</td>
                    <td class="col-1">${medico.cognome}</td>
                    <td class="col-1">${medico.cellulare}</td>
                    <td class="col-1">${medico.email}</td>
                    <td class="col-1">${medico.asl}</td>
                </tr>
            </table>
                <br>
        </c:forEach>
                
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/nuovoMedico.js"></script>
    </body>
</html>
