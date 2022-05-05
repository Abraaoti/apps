package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.services.interfaces.IJasperService;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
 */
@Service
public class JasperServiceImplementa implements IJasperService {

    private Map<String, Object> params = new HashMap<>();

    @Override
    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    @Override
    public JasperReport compilarJrxml(String arquivo) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo);
            return JasperCompileManager.compileReport(is);
        } catch (JRException ex) {
            Logger.getLogger(JasperServiceImplementa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void abrirJasperView(String jrxml, Connection connection) {
        JasperReport report = compilarJrxml(jrxml);
        try {
            JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
            JasperViewer views = new JasperViewer(print);
            views.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(JasperServiceImplementa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
