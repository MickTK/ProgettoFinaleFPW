$(document).ready(function(){
    $("#ordina-button").click(press);
    
    init_lista();
    
    $("#ricerca-mod").hide();
    $("input[name='mostra-utente']").change(function(){
        if($('input[name="mostra-utente"]:checked').val() == "lista"){
            init_lista();
            lista_mod();
        }
        else if($('input[name="mostra-utente"]:checked').val() == "ricerca")
            ric_mod()
    });
    
    $("#ricerca-utente").click(ricercaScheda);
    
    raccogliDati();
    $(".br").keyup(popolaAutoCompletamento);
});

/* Inizializza la lista di utenti in ordine alfabetico per cognome */
function init_lista(){
    $("#toc").prop("checked", true);
    var array = $(".utente-frame").sort(ordineCrescentePerCognome).appendTo("body");
}

/* Funzioni per ordinare la lista di utenti visualizzata */
function ordineCrescentePerCognome(a, b){
  var x = $(a).find(".cognome").text().toLowerCase();
  var y = $(b).find(".cognome").text().toLowerCase();
  return ((x < y) ? -1 : ((x > y) ? 1 : 0));
}
function ordineDecrescentePerCognome(b, a){
  var x = $(a).find(".cognome").text().toLowerCase();
  var y = $(b).find(".cognome").text().toLowerCase();
  return ((x < y) ? -1 : ((x > y) ? 1 : 0));
}

function ordineCrescentePerDonazioni(a, b){
  var x = $(a).find(".donazioni").text().toLowerCase();
  var y = $(b).find(".donazioni").text().toLowerCase();
  return ((x < y) ? -1 : ((x > y) ? 1 : 0));
}
function ordineDecrescentePerDonazioni(b, a){
  var x = $(a).find(".donazioni").text().toLowerCase();
  var y = $(b).find(".donazioni").text().toLowerCase();
  return ((x < y) ? -1 : ((x > y) ? 1 : 0));
}
function  press(){
    var x;
    if($("#campo").val() === "cognome")
        if($("#ordine").val() === "crescente")
            x = ordineCrescentePerCognome;
        else if($("#ordine").val() === "decrescente")
            x = ordineDecrescentePerCognome;
    if($("#campo").val() === "donazioni")
        if($("#ordine").val() === "crescente")
            x = ordineCrescentePerDonazioni;
        else if($("#ordine").val() === "decrescente")
            x = ordineDecrescentePerDonazioni;
        
    var array = $(".utente-frame").sort(x).appendTo("body");
};

/* Funzioni per il cambio di modalità di visualizzazione, tra: visualizza lista e ricerca singolo utente */
function lista_mod(){
    $(".br").val("");
    $(".donazione").remove();
    $("#ricerca-mod").hide();
    $(".utente-frame").show();
    $("#lista-mod").show();
}
function ric_mod(){
    $(".utente-frame").hide();
    $("#lista-mod").hide();
    $("#ricerca-mod").show();
}

/* Funzioni per l'autocompletamento */
class ParametriAC{
    constructor(nome, cognome, data){
        this.comp = nome + " " + cognome + " " + data;
    }
}
var listaDonatori = new Array();
function raccogliDati(){
    var s, nome, cognome, data;
    var array = $(".utente-frame").sort(ordineCrescentePerCognome);
    
    for(var i = 0; i < array.length; i++){
        nome = $(array[i]).find(".nome").text();
        cognome = $(array[i]).find(".cognome").text();
        data = $(array[i]).find(".nascita").text();
        s = new ParametriAC(nome, cognome, data);
        listaDonatori.push(s);
    }
}
function popolaAutoCompletamento(){
    $("#barra-ricerca > option").remove();
    if($(".br").val()){
        for(var i = 0; i < listaDonatori.length; i++){
            if(listaDonatori[i].comp.toLowerCase().includes($(".br").val().toLowerCase()))
                $("#barra-ricerca").append('<option value="' + listaDonatori[i].comp + '">');
        }
    }
}
function ricercaScheda(event){
    $("#risultato-ricerca > .utente-frame").appendTo("body");
    $("body > .utente-frame").hide();
    $(".donazione").remove();
    
    var arrayParams = $(".br").val().split(" ");
    var arrayUtenti = $(".utente-frame");
    var a;
    event.preventDefault();
    
    for(var i = 0; i < arrayUtenti.length; i++){
        if($(arrayUtenti[i]).find(".nome").text() == arrayParams[0])
            if($(arrayUtenti[i]).find(".cognome").text() == arrayParams[1])
                if($(arrayUtenti[i]).find(".nascita").text() == arrayParams[2]){
                    $(arrayUtenti[i]).show();
                    $(arrayUtenti[i]).appendTo("#risultato-ricerca");
                    $.ajax({
                        url: "RichiestaDonazioniServlet",
                        data: {
                            nome: $(".br").val().split(" ")[0],
                            cognome: $(".br").val().split(" ")[1],
                            data: $(".br").val().split(" ")[2]
                        },
                        dataType: "json",
                        success: function(data, state) {
                            aggiungiInfo(data);
                        },
                        error: function(data, state){},
                        asynch: true
                    });
                }
    }
}

function aggiungiInfo(data){
    $.each(data, function(key, val){
        // orario + quantita + data + note
        var arr = val.split("//*-*-*//");
        var a = '<div class="donazione center bordo-arr">' +
                '<table class="col-12"><tr> <th class="col-3">Ora</th>'+
                        '<th class="col-3">Quantità</th>'+
                        '<th class="col-3">Data</th>'+
                        '<th class="col-3">Note</th>'+
                    '</tr><tr>'+
                        '<td class="nascita col-3">'+arr[0]+'</td>'+
                       ' <td class="col-3">'+arr[1]+'</td>'+
                        '<td class="donazioni" class="col-3">'+arr[2]+'</td>'+
                        '<td class="donazioni" class="col-3">'+arr[3]+'</td>'+
                    '</tr></table></div>';
            $(a).appendTo("#risultato-ricerca");
    });
}