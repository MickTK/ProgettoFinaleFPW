<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Donatori</title>
        <meta name="author" content="Melis Michele">
        <meta name="description" content="Lista dei donatori registrati sul sito">
        <meta name="keywords" content="avis, amministratore, amministrazione, donatori">
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
        <h3 class='center'>Lista donatori</h3>
        <br>
        <div class='bordo-arr center col-6'>
            <br>
            <input type="radio" name="mostra-utente" value="lista" checked>Lista utenti<br>
            <input type="radio" name="mostra-utente" value="ricerca">Ricerca utente<br>
            <br>
            <div id="lista-mod">
                <table class="divisori">
                <tr>
                    <select id="campo" name="campo">
                        <option id="toc" value="cognome" selected hidden>Cognome</option>
                        <option value="cognome">Cognome</option>
                        <option value="donazioni">N. donazioni</option>
                    </select>
                </tr>
                <tr>
                    <select id="ordine" name="ordine">
                        <option value="crescente" selected hidden>Crescente</option>
                        <option value="crescente">Crescente</option>
                        <option value="decrescente">Decrescente</option>
                    </select>
                </tr>
                <tr>
                <button id="ordina-button">Ordina</button>
                </tr>
            </table>
            </div>
        </div>
        <c:forEach items="${utenti}" varStatus="loop" var="user">
            <div style="margin-top:10px" class="utente-frame bordo-arr center col-12">
                <table class="col-12 tb">

                    <!-- Prima riga -->
                    <tr>
                        <th class="col-4"></th>
                        <th class="col-4">Nome</th>
                        <th class="col-4">Cognome</th
                    </tr>
                    <tr class="tr-bottom">
                        <td class="col-4"><img width="100px" src="${user.foto}" title="Foto di ${user.nome}" alt="Foto di ${user.nome}"></td>
                        <td class="nome col-4">${user.nome}</td>
                        <td class="cognome" class="col-4">${user.cognome}</td>
                    </tr>

                    <!-- Seconda riga -->
                    <tr>
                        <th class="col-4">Username</th>
                        <th class="col-4">Codice fiscale</th>
                        <th class="col-4">Sesso</th
                    </tr>
                    <tr class="tr-bottom">
                        <td class="col-4">${user.username}</td>
                        <td class="col-4">${user.codiceFiscale}</td>
                        <td class="col-4">${user.sesso}</td>
                    </tr>

                    <!-- Terza riga -->
                    <tr>
                        <th class="col-4">Gruppo sanguigno</th>
                        <th class="col-4">Patologie</th>
                        <th class="col-4">E-mail</th
                    </tr>
                    <tr class="tr-bottom">
                        <td class="col-4">${user.gruppoSanguigno}</td>
                        <td class="col-4">${user.patologie}</td>
                        <td class="col-4">${user.email}</td>
                    </tr>

                    <!-- Quarta riga -->
                    <tr>
                        <th class="col-4">Data di nascita</th>
                        <th class="col-4">Cellulare</th>
                        <th class="col-4">N. donazioni effettuate</th
                    </tr>
                    <tr>
                        <td class="nascita col-4">${user.dataDiNascita}</td>
                        <td class="col-4">${user.cellulare}</td>
                        <td class="donazioni" class="col-4">${donazione[loop.index]}</td>
                    </tr>
                </table>
            </div>
        </c:forEach>
            
        <!-- Modalità ricerca -->
        <div id="ricerca-mod">
            <form class="bordo-arr center col-6" id="sub-form" action="RichiestaDonazioniServlet" method="post">
                <h5>Inserisci nome, cognome e data di nascita dell'utente</h5>
                <input class="br" list="barra-ricerca" type="text">
                <datalist id="barra-ricerca"></datalist>
                <input class="pulsante-blu col-12" id="ricerca-utente" type="submit" value="Cerca">
                <br>
            </form>
            <br>
            <div  id="risultato-ricerca"> 
                <!-- Qui andrà il risultato della ricerca --> 
            </div>
        </div>
        
            <script src="scripts/jquery-3.6.0.min.js"></script>
            <script src="scripts/listaUtenti.js"></script>
    </body>
</html>
