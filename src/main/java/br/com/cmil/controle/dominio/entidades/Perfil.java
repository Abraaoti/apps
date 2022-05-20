package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Entidade;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_perfis")
public class Perfil extends Entidade {
	
	@Column(name = "descricao", nullable = false, unique = true)
	private String desc;
	
	public Perfil() {
		super();
	}

	public Perfil(Long id) {
		super.setId(id);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
