package br.com.cmil.controle.dominio.services.interfaces;

import java.sql.Connection;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author ti
 */
public interface IJasperService {

    JasperReport compilarJrxml(String arquivo);

    void addParams(String key, Object value);

    void abrirJasperView(String jrxml,Connection connection);
}
