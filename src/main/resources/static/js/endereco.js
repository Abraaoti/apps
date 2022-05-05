
$(document).ready(function () {
    
    
    function limpa_formulário_cep() {
                // Limpa valores do formulário de cep.
                $("#rua").val("");
                $("#bairro").val("");
                $("#cidade").val("");
                $("#uf").val("");
                $("#numero").val("");
                $("#complemento").val("");
            }
            
            //Quando o campo cep perde o foco.
            $("#cep").blur(function() {

                //Nova variável "cep" somente com dígitos.
                var cep = $(this).val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep !== "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#rua").val("...");
                        $("#bairro").val("...");
                        $("#cidade").val("...");
                        $("#uf").val("...");
                        $("#numero").val("...");
                        $("#complemento").val("...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#rua").val(dados.logradouro);
                                $("#bairro").val(dados.bairro);
                                $("#cidade").val(dados.localidade);
                                $("#uf").val(dados.uf);
                               
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
    
    
    
    moment.locale('pt-BR');
    var table = $('#table-adrr').DataTable({
        searching: true,
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/enderecos/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'enderecoId'},
            {data: 'pessoaEndereco.nome'},
            {data: 'uf'},
            {data: 'cidade'},
            {data: 'bairro'},
            {data: 'rua'},
            {data: 'cep'},
            {data: 'numero'},
            {data: 'complemento'},
            {data: 'enderecoId',
                render: function (enderecoId) {
                    return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
                            .concat('href="').concat('/enderecos/editar/').concat(enderecoId, '"', ' ')
                            .concat('role="button" title="Editar" data-toggle="tooltip" data-placement="right">', ' ')
                            .concat('<i class="fas fa-edit"></i></a>');
                },
                orderable: false
            },
            {orderable: false,
                data: 'enderecoId',
                "render": function (enderecoId) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/enderecos/excluir/' +
                            enderecoId + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
    

});
