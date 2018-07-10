
function carregaEdicaoProduto(id) {
	$.ajax({
		type : "GET",
		url : "/carregaEdicaoProduto/" + id
	}).done(function(produto) {
		product = jQuery.parseJSON(produto);
		document.getElementById('id').value = product.id;
		document.getElementById('estoqueMinimo').value = product.estoqueMinimo;
		document.getElementById('nome').value = product.nome;
	});
}
