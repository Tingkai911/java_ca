package sg.edu.iss.ca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Brand;
import sg.edu.iss.ca.service.BrandImplementation;
import sg.edu.iss.ca.service.BrandInterface;

@Controller
@RequestMapping("/brand")
public class BrandController {
	
	@Autowired
	private BrandInterface brandserv;
	
	@Autowired
	public void setBrandInterface(BrandImplementation brandimpl) {
		this.brandserv = brandimpl;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("brands", brandserv.listAllBrands());
		return "brandIndex";
	}
	
	@GetMapping("/create")
	public String createBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "BrandForm";
	}

	@GetMapping("/delete/{id}")
	public String deleteBrand(@PathVariable("id") Integer id) {
		brandserv.deleteBrand(brandserv.findByBrandId(id));
		return "forward:/brand/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editBrand(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("brand", brandserv.findByBrandId(id));
		return "BrandForm";
	}
	
	@PostMapping("/save")
	public String saveBrand(@ModelAttribute("brand") @Valid Brand brand, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "BrandForm";
		}
		brandserv.updateBrand(brand);
		return "redirect:/brand/index";
	}


}
