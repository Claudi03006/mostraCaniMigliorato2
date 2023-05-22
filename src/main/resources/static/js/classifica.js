$(document).ready(function(){
    reloadClassifica();
});


function reloadClassifica(){
    console.log("Reload Classifica");
    $('#cani').empty();
    $.getJSON("/api/caniClassifica", function(data){
        $.each(data, function(index, value){//index rappresenta l'indice progressivo da 0 in poi
            //mentre value rappresenta il cane
            $("#cani").append(`<tr id="row_${value.id}">
            <td>${value.id}</td>
            <td>${value.nome}</td>
            <td>${value.eta}</td>
            <td>${value.razza}</td>
            <td>${value.peso}</td>
            <td>${value.sex}</td>
            <td>${value.voto}</td>
            <td>${value.proprietario.nome}-${value.proprietario.cognome}</td>
            <td> <button onclick="showDialogVotaCane(${value.id})" class="btn btn-primary" >Vota</td>
</tr>`);
        });
    });
}


let caneChoosed;
let caneChoosedId;
function showDialogVotaCane(value){
    console.log('id: '+value);
    //mi prendo la riga html pari alla riga selezionata 
    //relativa al click sul pulsante vota
    let row=$("#row_"+value);
    
    //prende il valore testuale della prima colonna della riga selezionata
    caneChoosedId=row.find("td:eq(0)").text();
    
    caneChoosed="Codice:"+row.find("td:eq(0)").text()+",nome: "+row.find("td:eq(1)").text()+",razza: "+row.find("td:eq(2)").text()+",peso: "+row.find("td:eq(3)").text()+",età: "+row.find("td:eq(4)").text()+",sex: "+row.find("td:eq(5)").text();
    console.log("Cane scelto: "+caneChoosed);
    $("#caneChoosed").val(caneChoosed);  
    
    $('#votaCaneModal').modal('toggle');
}


$("#btn_vota_cane").on("click", vota_cane);

function vota_cane(){
    const voto = $("#votoCaneChoosed").val();
 
    console.log("Voto: "+voto);
   // if(eta.trim() > 2 && razza.trim().length > 3 && peso.trim() != null && sex.trim().length >1){
        $.ajax({
            type:"post",
            dataType:"application/json",
            contentType:"application/json",
            url:"api/votaCane/"+caneChoosedId+"/"+voto
        }).done(function(id){
           
            alert("voto cane aggiornato con successo!!!");
           
        });
        $('#votaCaneModal').modal('hide');
        reloadClassifica();
//    }else{
//        alert("i valori inseriti non sono corretti");
//    }
}