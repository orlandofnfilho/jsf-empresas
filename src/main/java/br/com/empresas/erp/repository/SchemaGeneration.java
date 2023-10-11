package br.com.empresas.erp.repository;

import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.mysql.cj.xdevapi.Result;

import br.com.empresas.erp.model.Empresa;

public class SchemaGeneration {
	
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
		
		EntityManager em = emf.createEntityManager();
		
		List<Empresa> lista = em.createQuery("from Empresa", Empresa.class)
				.getResultList();
		
		Query query  = em.createNativeQuery("SELECT * FROM EMPRESA WHERE ID=?", Empresa.class);
		query.setParameter(1, 1L);
		
		Empresa empNativeQuery = (Empresa) query.getSingleResult();
	
		
		
		TypedQuery<Empresa> query2 = em.createQuery("SELECT e FROM Empresa e WHERE e.id = :id", Empresa.class);
		query2.setParameter("id", 1L);
		Empresa empJPQL = query2.getSingleResult();
		
		
		Empresa emp3 = em.find(Empresa.class, 2L);
		
		System.out.println(lista);
		System.out.println(empNativeQuery);
		System.out.println(empJPQL);
		System.out.println(emp3);
		em.close();
		emf.close();
	}

}
