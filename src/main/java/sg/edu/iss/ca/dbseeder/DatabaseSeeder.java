package sg.edu.iss.ca.dbseeder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.model.Supplier;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.repo.AdminLogRepository;
import sg.edu.iss.ca.repo.BrandRepository;
import sg.edu.iss.ca.repo.FormCartRepository;
import sg.edu.iss.ca.repo.InventoryRepository;
import sg.edu.iss.ca.repo.ProductRepository;
import sg.edu.iss.ca.repo.StaffRepository;
import sg.edu.iss.ca.repo.SupplierRepository;
import sg.edu.iss.ca.repo.UsageFormRepository;
import sg.edu.iss.ca.service.BrandService;
import sg.edu.iss.ca.service.InventoryImplement;
import sg.edu.iss.ca.service.ProductService;
import sg.edu.iss.ca.service.SupplierService;
import sg.edu.iss.ca.service.UserService;

@Component
public class DatabaseSeeder {

	@Autowired
	private ProductService productSvc;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private BrandService brandSvc;
	@Autowired
	private BrandRepository brandRepo;
	@Autowired
	private SupplierService supplierSvc;
	@Autowired
	private SupplierRepository supplierRepo;
	@Autowired
	private InventoryImplement inventorySvc;
	@Autowired
	private InventoryRepository inventoryRepo;
	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private UserService userSvc;
	@Autowired
	private AdminLogRepository adminRepo;
	@Autowired
	private FormCartRepository fromCartRepo;
	@Autowired
	private UsageFormRepository useFormRepo;
	
	@EventListener
	public void seed(ContextRefreshedEvent event) throws IOException, ParseException {
		createReportDir();
		resetData();
		seedBrandTable();
		seedProductTable();
		seedSupplierData();
		seedInventoryTable();
		seedStaffTable();
		seedUsageFormHistory();
	}
	
	private void createReportDir() throws IOException {
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "report");
        if (!Files.exists(filePath)) { 
            Files.createDirectory(filePath);
            System.out.println("Directory created");
        } else {   
            System.out.println("Directory already exists");
        }
	}
	
	private void resetData() {
		fromCartRepo.deleteAll();
		useFormRepo.deleteAll();
		adminRepo.deleteAll();
		productRepo.deleteAll();
		brandRepo.deleteAll();
		supplierRepo.deleteAll();
		inventoryRepo.deleteAll();
		staffRepo.deleteAll();
	}

	private void seedBrandTable() {
		brandSvc.createBrand(new Brand("Ferrari trial"));
		brandSvc.createBrand(new Brand("Lamborghini"));
		brandSvc.createBrand(new Brand("Maserati"));
	}
	
	private void seedProductTable() {		
		productSvc.createProduct(new Product("1A", "YokoKama", "YK (Farm-class)", 
				 "Black", "2x2", 
				"Car", "Tire", "Premium Car Tire", new Brand("YokoKama")));
		
		productSvc.createProduct(new Product("2A", "Chervoh", "Very good tire", 
				"Red", "2x2", 
				"Car", "Tire", "Premium Car Tire", new Brand("Chervoh")));
		
		productSvc.createProduct(new Product("3A", "Polymeowphism", "Cat Tire", 
				"Black", "2x2", 
				"Car", "Tire", "Premium Car Tire", new Brand("PM")));
		
		
		List<Brand> brands = brandSvc.listAllBrands();

		Product p1 = productSvc.createProduct(new Product("1B", "Michelin", "Car Tire", 
				"Black", "2x2", 
				"Car", "Tire", "Premium Car Tire"));
		
		Product p2 = productSvc.createProduct(new Product("2B", "Michelin", "Car Tire", 
				"Red", "2x2", 
				"Car", "Tire", "Premium Car Tire"));
		
		Product p3 = productSvc.createProduct(new Product("3B", "Bridgestone", "Car Tire", 
				"Black", "2x2", 
				"Car", "Tire", "Premium Car Tire"));
		
		Product p4 = productSvc.createProduct(new Product("3B", "Pirelli", "Car Tire", 
				"Black", "2x2", 
				"Car", "Tire", "Premium Car Tire"));
		
		Product p5 = productSvc.createProduct(new Product("1B", "ABC Window", "ABC", 
				"Transparent", "3x3", "Car Window", "Premium Car Window", 
				"Window"));
		
		Product p6 = productSvc.createProduct(new Product("2B", "Hololive", "ABC", 
				"Transparent", "3x3", "Car Window", "Premium Car Window", 
				"Window"));
		
		Product p7 = productSvc.createProduct(new Product("3B", "team2chewhuimin", "ABC", 
				"Opaque", "3x3", "Car Window", "Premium Car Window", 
				"Window"));
		
		p1.setBrand(brands.get(0));
		p2.setBrand(brands.get(0));
		p3.setBrand(brands.get(0));	
		p4.setBrand(brands.get(0));	
		p5.setBrand(brands.get(0));
		p6.setBrand(brands.get(0));
		p7.setBrand(brands.get(0));	
		
		productSvc.updateProduct(p1);
		productSvc.updateProduct(p2);
		productSvc.updateProduct(p3);
		productSvc.updateProduct(p4);
		productSvc.updateProduct(p5);
		productSvc.updateProduct(p6);
		productSvc.updateProduct(p7);

	}
	
	private void seedSupplierData() {
		supplierSvc.createSupplier(new Supplier("e0639657@u.nus.edu", "tier one"));
		supplierSvc.createSupplier(new Supplier("ericforgotafeature@gmail.com", "tier two"));
		supplierSvc.createSupplier(new Supplier("hellomynameistom@gmail.com", "tier three"));
	}
	
	private void seedInventoryTable () {
//		inventorySvc.createInventory(new Inventory(36, "A123", 10, 30, 13.0, 15.5, 14.0));
//		inventorySvc.createInventory(new Inventory(50, "B394", 40, 15, 20.0, 17.5, 16.0));
//		inventorySvc.createInventory(new Inventory(20, "B349", 60, 20, 40.0, 50.5, 45.0));
		
		List<Product> products = productSvc.listAllProducts();
		List<Supplier> suppliers = supplierSvc.listAllSuppliers();
		
		Inventory i1 = inventorySvc.createInventory(new Inventory(38, "A123", 10, 40, 13.0, 16.5, 14.0));
		Inventory i2 = inventorySvc.createInventory(new Inventory(10, "A123", 12, 30, 16.0, 16.0, 14.0));
		Inventory i3 = inventorySvc.createInventory(new Inventory(3, "A123", 11, 50, 13.5, 18.5, 14.0));
		
		i1.setProduct(products.get(0));
		i1.setSupplier(suppliers.get(0));
		
		i2.setProduct(products.get(1));
		i2.setSupplier(suppliers.get(2));
		
		i3.setProduct(products.get(2));
		i3.setSupplier(suppliers.get(0));
		
		inventorySvc.updateInventory(i1);
		inventorySvc.updateInventory(i2);
		inventorySvc.updateInventory(i3);

	}
	
	private void seedStaffTable() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		userSvc.addStaff(new Staff("ROLE_ADMIN", "john@email.com", "John Doe", "john", encoder.encode("123456"), true));
		userSvc.addStaff(new Staff("ROLE_MECHANIC", "jane@email.com", "Jane Doe", "jane", encoder.encode("123456"), true));
		userSvc.addStaff(new Staff("ROLE_MECHANIC", "tom@email.com", "Tom Doe", "tom", encoder.encode("password"), true));
		userSvc.addStaff(new Staff("ROLE_ADMIN", "tingkai911@gmail.com", "Ting Kai", "tk", encoder.encode("password"), true));
	}
	
	private void seedUsageFormHistory() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");
		
		UsageForm uForm = useFormRepo.save(new UsageForm("YK", "Yk's car", "His nice car", "20/12/2020", true));
		
		List<Inventory> inventory = inventoryRepo.findAll();
		
		fromCartRepo.save(new FormCart(inventory.get(0), 5, uForm));
		fromCartRepo.save(new FormCart(inventory.get(1), 5, uForm));
		
		UsageForm uForm2 = useFormRepo.save(new UsageForm("Esther", "Esther's car", "Her nice car", "22/12/2020", true));
		
		fromCartRepo.save(new FormCart(inventory.get(2), 1, uForm2));
		fromCartRepo.save(new FormCart(inventory.get(1), 10, uForm2));
		
		UsageForm uForm3 = useFormRepo.save(new UsageForm("Cher Wah", "Cher Wah's car", "His nice car", "21/12/2020", true));
		
		fromCartRepo.save(new FormCart(inventory.get(2), 1, uForm3));
		fromCartRepo.save(new FormCart(inventory.get(1), 10, uForm3));
		
		UsageForm uForm4 = useFormRepo.save(new UsageForm("Tin", "Tin's car", "His nice car", "22/12/2020", true));
		
		fromCartRepo.save(new FormCart(inventory.get(2), 1, uForm4));
		fromCartRepo.save(new FormCart(inventory.get(1), 10, uForm4));
		
		UsageForm uForm5 = useFormRepo.save(new UsageForm("Suria", "Suria's car", "Her nice car", "21/12/2020", true));
		
		fromCartRepo.save(new FormCart(inventory.get(2), 1, uForm5));
		fromCartRepo.save(new FormCart(inventory.get(1), 10, uForm5));
		
		UsageForm uForm6 = useFormRepo.save(new UsageForm("Bill Gates", "Bill's car", "His nice car", "20/12/2020", true));
		
		fromCartRepo.save(new FormCart(inventory.get(2), 1, uForm6));
		fromCartRepo.save(new FormCart(inventory.get(1), 10, uForm6));
	}
	
}





