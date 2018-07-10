function carregaEdicaoReagente(id) {
	$.ajax({
		type : "GET",
		url : "/carregaEdicaoReagente/" + id
	}).done(function(reagente) {
		reagent = jQuery.parseJSON(reagente);
		$("#titulo").html(reagent.nome);
		$("#formula-model").html("<strong>Fórmula quimica: </strong>" + reagent.nome);
		$("#massa-model").html("<strong>Massa molar: </strong>" + reagent.massaMolar);
		$("#familia-model").html("<strong>Familia: </strong>" + reagent.familia);
		$("#aparencia-model").html("<strong>Aparência: </strong>" + reagent.aparencia);
		$("#fispq-model").html("<strong>Fispq: </strong>" + reagent.link);
		$("#ph-model").html("<strong>PH: </strong>" + reagent.ph);
		$("#perigo-model").html("<strong>Perigo a Saúde: </strong>" + reagent.perigoSaude);
		$("#reatividade-model").html("<strong>Reatividade Com Outros Materias: </strong>" + reagent.reatividade);
		$("#estabilidade-model").html("<strong>Estabilidade Durante o Transporte: </strong>" + reagent.estabilidade);
		$("#armazenamento-model").html("<strong>Armazenamento: </strong>" + reagent.armazenamento);
		$("#indicador-model").html("<strong>Indicador: </strong>" + reagent.indicador);
//		$("#parametroIndicacao-model").html("<strong>Indicador: </strong>" + reagent.parametroIndicacao);
		$("#metodo-model").html("<strong>Método de Preparo: </strong>" + reagent.metodoPreparo);
		$("#passivoRevalidacao-model").html("<strong>Passivo de Revalidação: </strong>" + reagent.passivoRevalidacao);
		$("#indicacaoRevalidacao-model").html("<strong>Indicaçôes para Revalidação: </strong>" + reagent.indicacaoRevalidacao);
//		$("#descarte-model").html("<strong>Utilizado no descarte de outros composto : </strong>" + reagent.descarte);
//		$("#descarteCompostos-model").html("<strong>Apresenta Descarte Segundo a FISPQ: </strong>" + reagent.descarteCompostos);
		$("#compostoDescarte-model").html("<strong>Armário: </strong>" + reagent.compostoDescarte);
		$("#metodoDescarte-model").html("<strong>Método de Descarte: </strong>" + reagent.metodoDescarte);
		$("#metodoDescarte-model").html("<strong>Método de Descarte: </strong>" + reagent.quantidade);
		
		
	});
}

function buscaInfo() {
	$("#spinner").toggle();
	let nome = $("#nome").val();
	$.ajax({
		type : "GET",
		url : "/buscaInfoReagente/" + nome
	}).done(function(reagente) {
		
		reagent = jQuery.parseJSON(reagente);
		if(reagent.formula == null){
			$("#mensagem").html("Reagente não disponível no site da cetesb");
			$("#mensagem").addClass( "alert alert-danger" );
		
		}else{
			$("#mensagem").removeClass("alert alert-danger");
			$("#mensagem").html("");
			document.getElementById('formula').value = reagent.formula;
			document.getElementById('massaMolar').value = reagent.massaMolar;
			document.getElementById('familia').value = reagent.familia;
			document.getElementById('aparencia').value = reagent.aparencia;
			document.getElementById('link').value = reagent.link;
			document.getElementById('ph').value = reagent.ph;
			document.getElementById('perigoSaude').value = reagent.perigoSaude;
			document.getElementById('reatividade').value = reagent.reatividade;
			document.getElementById('estabilidade').value = reagent.estabilidade;
			document.getElementById('armazenamento').value = reagent.armazenamento;
//			document.getElementById('parametroIndicacao').value = reagent.parametroIndicacao;
			document.getElementById('metodoPreparo').value = reagent.metodoPreparo;
			document.getElementById('indicacaoRevalidacao').value = reagent.indicacaoRevalidacao;
			document.getElementById('compostoDescarte').value = reagent.compostoDescarte;
			document.getElementById('metodoDescarte').value = reagent.metodoDescarte;
			document.getElementById('radioativo').value = reagent.radioativo;
			document.getElementById('reage').value = reagent.reageH2O;
			document.getElementById('inflamavel').value = reagent.inflamavel;
		}
	})
	.always(function(){ //sempre escondendo o spinner
        $("#spinner").toggle();
    });
	
}