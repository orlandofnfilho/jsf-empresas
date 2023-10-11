package br.com.empresas.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.empresas.erp.model.Empresa;
import jakarta.inject.Inject;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Empresas() {

	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome) {
//		Query query = manager.createNativeQuery("SELECT * FROM EMPRESA WHERE nome LIKE ?", Empresa.class);
//		query.setParameter(1, "%" + nome + "%");
//		List<Empresa> lista = query.getResultList();

		TypedQuery<Empresa> query = manager.createQuery("from Empresa where nomeFantasia like :nomeFantasia",
				Empresa.class);
		query.setParameter("nomeFantasia", nome + "%");
		return query.getResultList();
	}

	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);
	}

	public void remover(Empresa empresa) {
		empresa = this.porId(empresa.getId());
		manager.remove(empresa);
	}

}
