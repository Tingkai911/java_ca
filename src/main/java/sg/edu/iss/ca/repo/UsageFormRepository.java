package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.UsageForm;

public interface UsageFormRepository extends JpaRepository<UsageForm, Integer> {
//	@Query("SELECT p from Product p WHERE p.partNumber = :pn")
//	public List<Product> findProductbyPartNumber(@Param("pn") String partNumber);
//	
//	@Query("SELECT p from Product p WHERE p.description LIKE CONCAT('%',:pd,'%')")
//	public List<Product> findProductbyDescription(@Param("pd") String description);
//	
//	@Query("SELECT p from Product p WHERE p.color = :pc")
//	public List<Product> findProductbyColor(@Param("pc") String color);
//	
//	@Query("SELECT p from Product p WHERE p.brand.name = :pbn")
//	public List<Product> findProductbyBrand(@Param("pbn") String brandName);
//	
//	@Query("SELECT p from Product p WHERE p.type = :pt")
//	public List<Product> findProductbyType(@Param("pt") String type);
	
//	@Query("SELECT uf from UsageForm uf JOIN FormCart fc ")
//	public List<UsageForm> findUsagebyProductId();
	
	@Query("Select uf from UsageForm uf where uf.customer LIKE CONCAT('%',:name,'%')"+" OR uf.description LIKE CONCAT('%',:name,'%')"
	         +" OR uf.car LIKE CONCAT('%',:name,'%')" + " OR uf.id LIKE CONCAT('%',:name,'%')" + " order by uf.id"
	 )
	public List<UsageForm> search(@Param("name") String keyword);
	
	@Query("Select uf from UsageForm uf where uf.customer LIKE CONCAT('%',:name,'%')"+" OR uf.description LIKE CONCAT('%',:name,'%')"
	         +" OR uf.car LIKE CONCAT('%',:name,'%')" + " OR uf.id LIKE CONCAT('%',:name,'%')" + " order by uf.id"
	 )
	public Page<UsageForm> searchPageable(@Param("name") String keyword, Pageable pageable);
	
}
