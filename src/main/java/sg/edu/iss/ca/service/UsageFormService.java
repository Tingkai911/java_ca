package sg.edu.iss.ca.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.UsageForm;

public interface UsageFormService {
	public List<FormCart> listAllItems(UsageForm usageForm);
	public void createForm();
	public List<UsageForm> findUsageFormsByInventoryId(int iid);
	public Page<UsageForm> findPaginatedSearch(int pageNo, int pageSize, String keyword);
	public Page<UsageForm> findPaginated(int pageNo, int pageSize);
}
