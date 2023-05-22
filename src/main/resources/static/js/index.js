$(document).ready(function(){
    $.getJSON("/api/caniClassificaUtenteCorrente", function(data){
        $.each(data, function(index, value){
            $("#cani").append(`<tr id="row_${value.id}"><td>${value.id}</td><td>${value.eta}</td><td>${value.razza}</td><td>${value.peso}</td><td>${value.sex}</td><td>${value.voto}</td><td>${value.proprietario.nome}-${value.proprietario.cognome}</td></tr>`);
        });
    });
});

$("#btn_aggiungi_concorrente").on("click", aggiungi_concorrente);

function aggiungi_concorrente(){
    const eta = $("#eta").val();
    const razza =$("#razza").val();
    const peso = $("#peso").val();
    const sex =$("#sex").val();
    const voto =$("#voto").val();
    
   // if(eta.trim() > 2 && razza.trim().length > 3 && peso.trim() != null && sex.trim().length >1){
        $.ajax({
            type:"post",
            url:"api/nuoviCani",
            data:{
                eta:eta,
                razza:razza,
                peso:peso,
                sex:sex,
                voto:voto
            }
        }).done(function(id){
            alert("cane inserito con successo");
            $("#cani").append(`<tr id="row_${id}"><td>${id}</td><td>${eta}</td><td>${razza}</td><td>${peso}</td><td>${sex}</td><td>${voto}</td></tr>`);
        });
//    }else{
//        alert("i valori inseriti non sono corretti");
//    }
}