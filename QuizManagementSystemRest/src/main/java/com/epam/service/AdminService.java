package com.epam.service;




import com.epam.dto.AdminDto;
import com.epam.model.Admin;

public interface AdminService {
	
	AdminDto validateAdmin(String username, String password);
	
	Admin dtoToEntity(AdminDto adminDto);
	
	AdminDto saveAdmin(AdminDto admin);

	AdminDto entityToDto(Admin admin);
	

}
