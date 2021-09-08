<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Errore</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/base.css" media="screen">
        
        <script type="text/javascript" src="js/myScript.js"></script>
        <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <h1>:(</h1>
        <h3>Ops, qualcosa è andato storto!</h3>
        <h4>Messaggio di errore: ${errorMessage}</h4>
        <br>
        <button class="pulsante-blu bordo-arr"><a style="color: white;" href="${link}">Torna indietro</a></button>
        
    </body>
</html>
