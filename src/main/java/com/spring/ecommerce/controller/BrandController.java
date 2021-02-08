package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.BrandDto;
import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.service.BrandService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Brand} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-31
 *************************************************************************/
@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class BrandController {

	@Autowired
	private BrandService service;

	/*************************************************************************
	 * Create a new Brand
	 * 
	 * @param ob {@link Brand} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Brand}
	 *************************************************************************/
	@PostMapping
	public Brand addBrand(@RequestBody Brand brand, HttpServletRequest rq, HttpServletResponse rs) {
		brand.setCreatedBy(Long.valueOf(rq.getHeader("createdBy")));
		brand.setCreatedByEmp(rq.getHeader("createdByEmp") + " (ID:" + rq.getHeader("createdBy") + ")");
		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(brand, rs);
	}

	/*************************************************************************
	 * Get all Brand {@link Brand}
	 * 
	 * @return {@link List< Brand>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<BrandDto> getAllBrand() {
		return service.getAllBrand();
	}

	 /*************************************************************************
     * Get all  active {@link  Brand}
     * 
     * @return {@link List< Brand>}
     *************************************************************************/
    @GetMapping("/getAll/active")
    public List<BrandDto> getAllActiveBrand() {
    	return service.getAllActiveBrand();
    }

}
