$(document).ready(function(){
    $(".sub").click(function(event){
        event.preventDefault();
        if($("#nome").val() == "" ||
                $("#cognome").val() == "" ||
                $("#cellulare").val() == "" ||
                $("#email").val() == "" ||
                $("#asl").val() == ""){
            alert("Errore: stringa vuota");
            return;
        }
        
        $.ajax({
            url: "NuovoMedicoServlet",
            data: {
                nome: $("#nome").val() ,
                cognome: $("#cognome").val(),
                cellulare: $("#cellulare").val(),
                email: $("#email").val(),
                asl: $("#asl").val()
            },
            dataType: "json",
            success: function(data, state) {
                controllo(data);
            },
            error: function(data, state){},
            asynch: true
        })
    });
});

function controllo(dati){
    if(dati.riscontro == "positivo"){
        alert("Medico registrato con successo");
    }
    else{
        alert("Errore nella registrazione del medico\n" + 
                "Errore:" + dati.riscontro);
    }
    $("#sub-form").submit();
}