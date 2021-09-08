<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conferma donazioni</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Salva nel database le donazioni effettuate">
        <meta name="keywords" content="avis, amministratore, amministrazione, donazioni">
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
        <h3 class='center'>Prenotazioni da confermare</h3>
        <br>
        <c:if test="${empty prenotazioni}">
            <p class="center">Nessuna prenotazione passata da revisionare</p>
        </c:if>
        <c:forEach items="${prenotazioni}" var="prenotazione">
            <div>
                <form id="sub-form" class="col-12 bordo-arr center" action="" method="post">
                <p class="id" hidden>${prenotazione.id}</p>
            <table class="col-12 center">
                <tr>
                    <th class="col-2">Data</th>
                    <th class="col-2">Luogo</th>
                    <th class="col-2">Nome</th>
                    <th class="col-2">Cognome</th>
                </tr>
                <tr>
                    <td class="data col-2">${prenotazione.sessione.data}</td>
                    <td class="luogo col-2">${prenotazione.sessione.luogo}</td>
                    <td class="nome col-2">${prenotazione.utente.nome}</td>
                    <td class="cognome col-2">${prenotazione.utente.cognome}</td>
                </tr>
            </table>
                <br>
                <div style='margin-top: 5px;'>
                    <label for="quantita">Quantità di sangue prelevato</label>
                    <input type="text" class="quantita" name="quantita"/>
                </div>
                <div style='margin-top: 5px;'>
                    <label for="note">Note aggiuntive</label>
                    <textarea class="col-6 note" name="note" id="note" cols="20" rows="5" maxlength="190"></textarea>
                </div>
                <div style='margin-top: 5px;'>
                    <label for="medico_id">Medico</label>
                    <select id="medico_id" name="medico_id">
                
                    <c:forEach items="${medici}" var="medico">
                        <option class="mid" value="${medico.id}">${medico.nome} ${medico.cognome}</option>
                    </c:forEach>
                    </select>
                </div>
                <input class="col-12  center elimina-prenotazione" style='margin-top: 15px; background-color: red; color: white; padding: 5px;' type="submit" value="Elimina permanentemente">
                <input class="col-12  pulsante-blu conferma-donazione" style='margin-top: 5px; ' type="submit" value="Salva nel registro">
                </form>
                <br>
        </c:forEach>
                
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/donazioniEffettuate.js"></script>
    </body>
</html>
