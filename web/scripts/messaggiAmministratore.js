$(document).ready(function(){
    $(".sub").click(function(event){
        var d ;
        
        event.preventDefault();
        
        if($('input[name="utente-destinatario"]:checked').val() == "disponibili"){
            d = "disp";
        }
        else if($('input[name="utente-destinatario"]:checked').val() == "utente"){
            d = $("#destinatario").val();
        }
        else if($('input[name="utente-destinatario"]:checked').val() == "data"){
            d = $("#data").val();
        }
        
        if(d == "" || d == undefined){
            alert("Campo 'destinatario' vuoto");
            return;
        }
        
        $.ajax({
            url: "InviaMessaggioAmministratoreServlet",
            data: {
                destinatario: d ,
                messaggio: $("#messaggio").val()
            },
            dataType: "json",
            success: function(data, state) {
                controllo(data);
            },
            error: function(data, state){},
            asynch: false
        })
    });
    
    /* Tipo di destinatario */
    dest();
    $("input[name='utente-destinatario']").change(dest);
});

function dest(){
    if($('input[name="utente-destinatario"]:checked').val() == "disponibili"){
            $(".txt").hide();
            $("#destinatario").val("");
            $(".data").hide();
        }
        else if($('input[name="utente-destinatario"]:checked').val() == "utente"){
            $(".txt").show();
            $("#destinatario").val("");
            $(".data").hide();
        }
        else if($('input[name="utente-destinatario"]:checked').val() == "data"){
            $(".txt").hide();
            $("#destinatario").val("");
            $(".data").show();
        }
}

// Messaggio mostrato a schermo dopo l'invio del messaggio
function controllo(dati){
    if(dati.riscontro == "positivo"){
        alert("Messaggio inviato con successo");
    }
    else{
        alert("Si è verificato un errore nell'invio del messaggio, si prega di riprovare\n" + 
                "Errore:" + dati.riscontro);
    }
    $("#sub-form").submit();
}
