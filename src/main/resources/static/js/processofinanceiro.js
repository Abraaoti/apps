$(document).ready(function () {

    moment.locale('pt-BR');
    var table = $('#table-processo').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/processofinanceiro/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'emissao', render:
                        function (emissao) {
                            return moment(emissao).format('L');
                        }
            },
            {data: 'vencimento', render:
                        function (vencimento) {
                            return moment(vencimento).format('L');
                        }

            },
            {data: 'fornecedor.nome'},
            {data: 'documento'},
            {data: 'centroCusto.centro'},
            {data: 'valor', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ ')},
            {data: 'qtdparcela'},
            {data: 'forma_pagamento'},
            {data: 'arquivo', render: function (data, type, full, meta) {
                    return '<a href="../docs/' + 
                            data + '" dawnload target="_blank">'+data+'</a>';
                }
            },
            {data: 'usuario.email'},
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/processofinanceiro/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {data: 'id',
                render: function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/processofinanceiro/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';

                },
                orderable: false
            },

            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-info btn-sm btn-block" href="/pagarcontas/gerar/parcela/' + id + '" role="button"><i class="fas fa-plus"></i></a>';
                }
            }
        ]
    });


});




