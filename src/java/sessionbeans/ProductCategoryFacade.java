/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.ProductCategory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fred
 */
@Stateless
public class ProductCategoryFacade extends AbstractFacade<ProductCategory> {
    @PersistenceContext(unitName = "0_CSD322_lecture04_f15_gitPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductCategoryFacade() {
        super(ProductCategory.class);
    }
    
}
