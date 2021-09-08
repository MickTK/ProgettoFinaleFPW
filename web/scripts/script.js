
$(document).ready(function(){
    
    // Pulsante per la registrazione di un nuovo utente al sito
    $("#pulsanteRegistrazione").click(function(event){
        
        event.preventDefault();
        
        if(!c())
            return;
        console.log("click");
        $.ajax({
            url: "ControlloNomeECodiceServlet",
            data: {
                user: $("#user").val() ,
                codiceFiscale: $("#codiceFiscale").val()
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

// In caso di username o codice fiscale duplicato
function controllo(dati){
    if(dati.usr == "invalido"){
        alert("Username non valido: esiste già un utente con questo username");
    }
    else if (dati.code == "invalido"){
        alert("Codice fiscale non valido: esiste già un utente con questo codice");
    }
    else{
        $("#sub-form").submit();
    }
}

function c(){
    var sel;
    
    if($("#nome").val().length < 3){
        alert('Nome: minimo 3 caratteri');
        return false;
    }
    if($("#nome").val().length > 50){
        alert('Nome: massimo 50 caratteri');
        return false;
    }
    
    if($("#cognome").val().length < 3){
        alert('Cognome: minimo 3 caratteri');
        return false;
    }
    if($("#cognome").val().length > 50){
        alert('Cognome: massimo 50 caratteri');
        return false;
    }
    
    if($("#patologie").val().length > 200){
        alert('Patologie: massimo 200 caratteri');
        return false;
    }
    
    if($("#cellulare").val().length < 8){
        alert('Cellulare: minimo 8 cifre');
        return false;
    }
    if($("#cellulare").val().length > 12){
        alert('Cellulare: massimo 12 cifre');
        return false;
    }
    
    if($("#codiceFiscale").val().length < 12){
        alert('Codice fiscale: minimo 12 caratteri');
        return false;
    }
    
    if($("#codiceFiscale").val().length > 16){
        alert('Codice fiscale: massimo 16 caratteri');
        return false;
    }
    
    if($("#user").val().length < 5){
        alert('Username: minimo 5 caratteri');
        return false;
    }
    if($("#user").val().length > 20){
        alert('Username: massimo 20 caratteri');
        return false;
    }
    
    if($("#pass").val().length < 5){
        alert('Password: minimo 5 caratteri');
        return false;
    }
    if($("#pass").val().length > 20){
        alert('Password: massimo 20 caratteri');
        return false;
    }
    
    if($("#file").val().length < 0){
        alert('Fotografia: assente');
        return false;
    }
    
    sel = $('#gruppoSanguigno option:selected');
    if($(sel).val() == ""){
        alert('Gruppo sanguingo: non selezionato');
        return false;
    }
    
    sel = $('#sesso option:selected');
    if($(sel).val() == ""){
        alert('Sesso: non specificato');
        return false;
    }
    
    sel = $('#dataDiNascita');
    if($(sel).val() == ""){
        alert('Data di nascita: non specificata');
        return false;
    }
    
    return true;
}