package br.com.cmil.controle.dominio.datatable;

public class DatatablesColunas {

    public static final String[] ESPECIALIDADES = {"id", "titulo"};

    public static final String[] MEDICOS = {"id", "nome", "crm", "dtInscricao", "especialidades"};
    public static final String[] PESSOA = {"id", "nome", "sobrenome", "nascimento"};
    public static final String[] FORNECEDORES = {"id", "nome", "sobrenome", "nascimento", "cnpj", "ie", "im"};

    public static final String[] AGENDAMENTOS = {"id", "paciente.nome", "dataConsulta", "medico.nome", "especialidade.titulo"};

    public static final String[] USUARIOS = {"id", "email", "ativo", "perfis"};
    public static String[] DEPARTAMENTOS = {"id", "depa"};
    public static String[] CATEGORIAS = {"id", "cate"};
    public static String[] FUNCIONARIOS = {"id", "nome", "sobrenome","nascimento","matricula","cpf","rg","passaporte",
        "mae",
        "pai",
        "ec",
        "genero",
        "cargo.titulo",
        "admissao",
        "salario",
        "naturalidade",
        "empresa.nome",
       
    };
    public static String[] CENTROCUSTO = {"id", "centro"};
    public static String[] PROCESSOPAGARCONTA = {"id", "emissao","vencimento","fornecedor.nome","documento","centroCusto.centro","valor","qtdparcela","forma_pagamento","arquivo","usuario.email" };
    public static String[] CONTAPAGAR = {"id", "processoFinanceiro.id", "documento", "valorPagar","banco", "vencimento", "forma_pagamento", "data_pagamento", "status","observacao","total"};
    public static String[] CARGO = {"idCargo", "titulo", "departamentos.depa"};
    public static String[] ENDERECO = {"enderecoId", "pessoaEndereco", "uf", "cidade", "bairro", "rua", "cep", "numero", "complemento"};
    public static String[] TELEFONE = {"telefoneId", "pessoaTelefone.nome", "telefone"};
    public static String[] EMAIL = {"emailId", "pessoaEmail", "email"};
    public static String[] EMPRESA = {"id", "nome", "sobrenome", "nascimento", "cnpj", "ie", "im", "socios","capital"};
    public static String[] ARQUIVO = {"idFile", "name", "type", "data"};
   

    
}
