//package com.epam.adminserviceimpl;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.epam.exception.CrediantialsEmptyException;
//import com.epam.exception.InvalidDetailsException;
//import com.epam.model.Admin;
//import com.epam.repository.AdminRepository;
//import com.epam.serviceimpl.AdminServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class AdminControllerTest {
//	@Mock
//	AdminRepository adminRepository;
//
//	@InjectMocks
//	AdminServiceImpl adminService;
//
//	@BeforeEach
//	void init() {
//
//	}
//
//	@Test
//	void testValidAdmin() {
//
//		Admin admin = new Admin();
//		admin.setUsername("Admin");
//		admin.setPassword("Pass@123");
//
//		when(adminRepository.validateAdmin("Admin", "Pass@123")).thenReturn(Optional.of(admin));
//
//		assertEquals(true, adminService.validateAdmin("Admin", "Pass@123"));
//
//	}
//
//	@Test
//	void testInvalidAdmin() {
//
//		when(adminRepository.validateAdmin("Admin", "Pass@12")).thenReturn(Optional.empty());
//
//		assertThrows(InvalidDetailsException.class, () -> adminService.validateAdmin("Admin", "Pass@12"));
//
//	}
//
//	@Test
//	void testAddAdmin() {
//
//		Admin admin = new Admin();
//
//		admin.setUsername("Admin");
//		admin.setPassword("Admin@123");
//
//		when(adminRepository.save(admin)).thenReturn(admin);
//
//		assertEquals(true, adminService.saveAdmin(admin));
//	}
//
//	@Test
//	void testAddAdminFailure() {
//
//		Admin admin = new Admin();
//
//		admin.setUsername("Admin");
//		admin.setPassword("");
//
//		assertEquals(false, adminService.saveAdmin(admin));
//	}
//
//}
