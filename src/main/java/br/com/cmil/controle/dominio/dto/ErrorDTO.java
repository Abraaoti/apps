package br.com.cmil.controle.dominio.dto;

import java.util.Date;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author ti
 */
@ControllerAdvice
public class ErrorDTO {

    private String status;
    private String menssage;
    private Date timetemp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public Date getTimetemp() {
        return timetemp;
    }

    public void setTimetemp(Date timetemp) {
        this.timetemp = timetemp;
    }

}
