$(document).ready(function(){
    $(".sub").click(function(event){
        event.preventDefault();
        
        if(!pre_controllo()){
            alert("Orario non corretto. Sintassi esempio: '13.45'");
            return;
        }
        
        $.ajax({
            url: "NuovaSessioneServlet",
            data: {
                data: $("#data").val() ,
                inizio: $("#inizio").val(),
                fine: $("#fine").val(),
                luogo: $("#luogo").val()
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

function pre_controllo(){
    var inizio = $("#inizio").val();
    inizio.replace(":", ".");
    inizio.replace(",", ".");
    inizio.replace("-", ".");
    
    var fine = $("#fine").val();
    fine.replace(":", ".");
    fine.replace(",", ".");
    fine.replace("-", ".");
    
    if(!fine.includes('.') || !inizio.includes('.'))
        return false;
    
    var a, b;
    
    // Fine
    a = parseInt(fine.split(".")[0]);   // Ora
    b = parseInt(fine.split(".")[1]);   // Minuto
    
    $("#fine").val(a.toString() + "." + b.toString());
    
    if(!(a >= 0 && a <= 24 && b >= 0 && b <= 59))
        return false;
    
    // Inizio
    a = parseInt(inizio.split(".")[0]);   // Ora
    b = parseInt(inizio.split(".")[1]);   // Minuto
    
    $("#inizio").val(a.toString() + "." + b.toString());
    
    if(!(a >= 0 && a <= 24 && b >= 0 && b <= 59))
        return false;
    
    return true;
}

function controllo(dati){
    if(dati.riscontro == "positivo"){
        alert("Sessione aggiunta con successo");
    }
    else{
        alert("Errore nell'aggiunta della sessione\n" + 
                "Errore:" + dati.riscontro);
    }
    $("#sub-form").submit();
}