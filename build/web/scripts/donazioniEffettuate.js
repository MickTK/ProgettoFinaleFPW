$(document).ready(function(){
    $(".conferma-donazione").click(function(event){
        event.preventDefault();
        if(!Number.isInteger($(this).parent().find(".quantita").val())){
            alert("Errore: quantita' non valida");
            return;
        }
        
        $.ajax({
            url: "SalvaPrenotazioneServlet",
            data: {
                id: $(this).parent().find(".id").text(),
                quantita: $(this).parent().find(".quantita").val(),
                note: $(this).parent().find(".note").val(),
                medico_id: $(this).parent().find('select').val(),
                stato: "conferma"
            },
            dataType: "json",
            success: function(data, state) {},
            error: function(data, state){},
            asynch: true
        });
        $(this).parent().remove();
    });
    
    $(".elimina-prenotazione").click(function(event){
        event.preventDefault();
        $.ajax({
            url: "SalvaPrenotazioneServlet",
            data: {
                id: $(this).parent().find(".id").text(),
                quantita: "a",
                note: "a",
                medico_id: "0",
                stato: "elimina"
            },
            dataType: "json",
            success: function(data, state) {},
            error: function(data, state){},
            asynch: true
        });
        $(this).parent().remove();
    });
});