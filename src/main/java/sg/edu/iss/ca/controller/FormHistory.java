package sg.edu.iss.ca.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.repo.FormCartRepository;
import sg.edu.iss.ca.repo.InventoryRepository;
import sg.edu.iss.ca.repo.UsageFormRepository;
import sg.edu.iss.ca.service.FormCartImplement;
import sg.edu.iss.ca.service.FormCartService;
import sg.edu.iss.ca.service.UsageFormImplement;
import sg.edu.iss.ca.service.UsageFormService;

@Controller
@RequestMapping("/FormHistory")
public class FormHistory {
	@Autowired
	private InventoryRepository irepo;

	@Autowired
	private UsageFormRepository ufrepo;

	@Autowired
	private FormCartRepository fcrepo;

	@Autowired
	private UsageFormService ufservice;

	@Autowired
	public void setUsageFormService(UsageFormImplement ufimp) {
		this.ufservice = ufimp;
	}

	@Autowired
	private FormCartService fcservice;

	@Autowired
	public void setFormCartService(FormCartImplement fcimp) {
		this.fcservice = fcimp;
	}

	@GetMapping("/listHistory")
	public String listHistory(Model model, @Param("keyword") String keyword, HttpSession session) {
//			List<Inventory> listInventories = inservice.listAllInventories(keyword);
//			model.addAttribute("InventoryList", listInventories);
//			model.addAttribute("keyword", keyword);
		session.setAttribute("searchHis", "true");
		session.setAttribute("keyword", keyword);
		return findPaginatedSearch(1, model, session);
	}

	@GetMapping("/page/{pageNo}/search")
	public String findPaginatedSearch(@PathVariable(value = "pageNo") int pageNo, Model model, HttpSession session) {
		String keyword = (String) session.getAttribute("keyword");
		int pageSize = 5;
		Page<UsageForm> page = ufservice.findPaginatedSearch(pageNo, pageSize, keyword);
		List<UsageForm> formList = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("formList", formList);
		return "FormHistory";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, HttpSession session) {
		int pageSize = 5;
		Page<UsageForm> page = ufservice.findPaginated(pageNo, pageSize);
		List<UsageForm> formList = page.getContent();
		session.setAttribute("searchHis", null);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("formList", formList);
		return "FormHistory";
	}
}
