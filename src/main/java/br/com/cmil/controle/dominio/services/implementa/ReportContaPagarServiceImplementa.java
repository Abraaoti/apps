package br.com.cmil.controle.dominio.services.implementa;

import org.springframework.stereotype.Service;

/**
 *
 * @author cmilseg
 */
@Service
public class ReportContaPagarServiceImplementa  {

    
    /**
   
    @Override
    public List<ContaPagar> buscarDataInicialDataFinalAndValorMinimo(LocalDate dataInicial, LocalDate dataFinal, BigDecimal valorMinimo) {

        StringBuilder sb = new StringBuilder();
        sb.append("select c ");
        sb.append(" form ContaPagar c ");
        sb.append(" where c.vencimento >= :dataInicial");
        if (dataInicial != null) {
            sb.append(" and c.vencimento <= :dataFinal");
        }
        if (valorMinimo != null) {
            sb.append(" and c.total <= :valorMinimo " );
        }
        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dataInicial", dataInicial);
        if (dataFinal != null) {
            query.setParameter("dataFinal", dataFinal);
        }
        
        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }
        
        return query.getResultList();

    }
   
    @Override
    public ContaPagar buscarDataInicialDataFinal(LocalDate dataInicial, LocalDate dataFinal) {

        StringBuilder sb = new StringBuilder();
        sb.append("select count(c.id), sum(c.total), avg(c.total)");
        sb.append(" form ContaPagar c ");
        
        sb.append(" where c.vencimento >= :dataInicial");
        sb.append(" and c.vencimento >= :dataFinal");
        
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("dataInicial", dataInicial);
        
            query.setParameter("dataFinal", dataFinal);
        Object[] obj = (Object[])query.getSingleResult();
        
        ContaPagar conta = new ContaPagar();
        conta.setId((Long)obj[1]);
        conta.setDocumento((String)obj[2]);
        conta.setObservacao((String)obj[3]);
        conta.setVencimento((LocalDate)obj[4]);
        conta.setData_pagamento((LocalDate)obj[5]);
        conta.setValorPagar((BigDecimal)obj[6]);
        conta.setTotal((BigDecimal)obj[7]);
        conta.setSituacao((Situacao)obj[8]);
        conta.setBanco((Banco)obj[9]);
        conta.setProcessoFinanceiro((ProcessoFinanceiro)obj[10]);
       
             
        
        return conta;

    }*/

}
