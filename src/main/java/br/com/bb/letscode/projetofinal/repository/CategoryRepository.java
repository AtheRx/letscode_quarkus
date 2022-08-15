package br.com.bb.letscode.projetofinal.repository;

import br.com.bb.letscode.projetofinal.model.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
}
