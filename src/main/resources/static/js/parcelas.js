$(document).ready(function () {

    function limpa_processoFinanceiro() {
        // Limpa valores do formulário de cep.
        $('#valorPagar').val();
        $('#documento').val();
        $('#descricao').val();
        $('#vencimento').val();
        $('#parcela').val();
        $('#processoFinanceiro').val();
    }
    $("#processoFinanceiro").blur(function () {
        var processoId = $(this).val().replace(/\D/g, '');
        $.get("/pagarcontas/gerar/parcela/" + processoId, function (data) {
            if (!("erro" in data)) {
                let valorTotal = data.valor;
                let parcelatotal = data.qtdparcela;
                let documento = data.documento;
                let observacao = data.observacao;
                let vencimento = data.vencimento;

                $('#valorPagar').val(valorTotal);
                $('#documento').val(documento);
                $('#observacao').val(observacao);
                $('#vencimento').val(vencimento);
                $('#parcela').val(parcelatotal);
                $('#processoFinanceiro').val(data.id);

            } else {

                limpa_processoFinanceiro();
            }
        });
    });


    moment.locale('pt-BR');
    var table = $('#table-contas').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/pagarcontas/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},

            {data: 'documento'},

            {data: 'valorPagar', render: function (data, type) {

                    var number = $.fn.dataTable.render.number('.', ',', 2, 'R$ ').display(data);
                    if (type === 'display') {
                        let color = 'green';
                        if (data < 10) {
                            color = 'red';
                        } else if (data < 1000) {
                            color = 'orange';
                        }

                        return '<span style="color:' + color + '">' + number + '</span>';
                    }

                    return number;
                }
            },
            {data: 'banco'},
            {data: 'vencimento', render: function (vencimento) {
                    if (vencimento === null) {
                        return '';
                    } else {
                        return moment(vencimento).format('L');
                    }
                }

            },
            {data: 'data_pagamento', render: function (data_pagamento) {
                    if (data_pagamento === null) {
                        return '';
                    } else {
                        return moment(data_pagamento).format('L');
                    }
                }

            },

            {data: 'situacao', render: function (data, type) {
                    if (type === 'display') {
                        let color = 'orange';
                        if (data === 'VENCIDA') {
                            color = 'red';
                        } else if (data === 'PAGO') {
                            color = 'green';

                        } else if (data === 'CANCELADA') {
                            color = 'gray';
                        }

                        return '<span style="color:' + color + '">' + data + '</span>';
                    }

                    return data;
                }

            },
            {data: 'observacao'},
            {data: 'total', render: function (data, type) {

                    var number = $.fn.dataTable.render.number('.', ',', 2, 'R$ ').display(data);
                    if (type === 'display') {
                        let color = 'green';
                        if (data < 10) {
                            color = 'red';
                        } else if (data < 1000) {
                            color = 'orange';
                        }

                        return '<span style="color:' + color + '">' + number + '</span>';
                    }

                    return number;
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/pagarcontas/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-info btn-sm btn-block" href="/pagarcontas/pagar/' + id + '" role="button"><i class="fas fa-plus"></i></a>';
                }
            }
        ]
    });


});





