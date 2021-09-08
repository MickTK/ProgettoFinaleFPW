$(document).ready(function(){
    
    $(".sub").click(function(event){
        event.preventDefault();
        
        if($("#messaggio").val() == ""){
            alert("Messaggio vuoto");
            return;
        }
        
        $.ajax({
            url: "messaggioUtente",
            data: {
                user: $("#user").val() ,
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
});

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