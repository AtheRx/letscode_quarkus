package br.com.bb.letscode.projetofinal.service;

import br.com.bb.letscode.projetofinal.model.Category;
import br.com.bb.letscode.projetofinal.repository.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<Category> listCategory() {
        return categoryRepository.listAll();
    }

    @Transactional
    public Category createCategory(Category category) throws Exception {

        try {
            categoryRepository.persist(category);
            return category;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Category getCategory(long id) throws Exception {

        try {
            Category category = categoryRepository.findById(id);

            return category;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Category updateCategory(long id, Category category) {

        Category categoryOld = categoryRepository.findById(id);
        if (category == null) {
            throw new NotFoundException();
        }
        categoryOld.setCode(category.getCode());
        categoryOld.setName(category.getName());

        return categoryOld;
    }

    @Transactional
    public void deleteCategory(long id) throws Exception {

        Category category = categoryRepository.findById(id);
        if(category == null) {
            throw new NotFoundException();
        }
        categoryRepository.delete(category);

    }
}
