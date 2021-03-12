package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.dto.RequisitionDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.Transaction;
import com.spring.ecommerce.repository.BrandRepo;
import com.spring.ecommerce.repository.CategoryRepo;
import com.spring.ecommerce.repository.ImageRepo;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.RequisitionRepo;
import com.spring.ecommerce.repository.SubCategoryRepo;
import com.spring.ecommerce.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link RequisitionServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-3-11
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequisitionServiceImpl implements RequisitionService {
	
	private final RequisitionRepo requisitionRepo;
	private final ProductRepo productRepo;
	private final UserRepo userRepo;

	/*************************************************************************
	 * Create a new Requisition
	 * 
	 * @param ob {@link Requisition} object
	 * @return {@link Requisition}
	 *************************************************************************/
	@Override
	public Requisition create(Requisition requisition) {
		try {
			requisition.setProduct(productRepo.findById(requisition.getProductId()).orElse(null));
			requisition.setUser(userRepo.findById(requisition.getUserId()).orElse(null));
			return requisitionRepo.save(requisition);
		} catch (Exception e) {
			log.warn("Failed to create  requisition: ", e);
			return requisition;
		}
	}

	/*************************************************************************
	 * Get all Requisition {@link Requisition}
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	@Override
	public List<RequisitionDto> getAllRequisitions() {
		return requisitionRepo.findAll().stream()
				.map(this::getRequisitionDtoFromEntity).collect(Collectors.toList());
	}


	/*************************************************************************
	 * Get Requisition {@link Requisition} by Id
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	public RequisitionDto getRequisitionById(String id) {
		return requisitionRepo.findById(id).map(this::getRequisitionDtoFromEntity).orElse(null);
	}
	
	/*************************************************************************
	 * Get Requisition {@link Requisition} by On Cart
	 * 
	 * @return {@link List<Requisition>}
	 *************************************************************************/
	@Override
	public List<RequisitionDto> getRequisitionByOnCart(String userId) {
		return requisitionRepo.findAllByStatusAndUser("cart",userRepo.findById(userId).orElse(null)).stream().map(this::getRequisitionDtoFromEntity).collect(Collectors.toList());
	}
	
	/*************************************************************************
	 * Get Requisition {@link Requisition} by Status
	 * 
	 * @return {@link List<Requisition>}
	 *************************************************************************/
	@Override
	public List<RequisitionDto> getRequisitionByStatus(String status) {
		return requisitionRepo.findAllByStatus(status).stream().map(this::getRequisitionDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Update {@link Requisition}
	 * 
	 * @param ob {@link Requisition} object
	 * @return {@link Requisition}
	 *************************************************************************/
	@Override
	public Requisition update(Requisition requisition) {
		try {
			Requisition existingRequisition = requisitionRepo.findById(requisition.getId()).orElse(null);
			 requisition.setUser(userRepo.findById(requisition.getUserId()).orElse(null));
			 requisition.setProduct(productRepo.findById(requisition.getProductId()).orElse(null));
			BeanUtils.copyProperties(requisition, existingRequisition);
			return requisitionRepo.save(existingRequisition);
		} catch (Exception e) {
			log.warn("Failed to update  Requisition: ", e);
			return requisition;
		}
	}

	/*************************************************************************
	 * Delete {@link Requisition}
	 * 
	 * @param ob {@link Requisition} object
	 * @return {@link Requisition}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			requisitionRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to delete  Product: ", e);
			return new ResponseEntity<>("Unsccecfull.....!!!!!!!!!.", HttpStatus.BAD_REQUEST);
		}
	}

	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/
	@Override
	public Requisition completeRequisition(String id) {
		Requisition ob = requisitionRepo.findById(id).orElse(null);
		ob.setStatus("Complete");
		requisitionRepo.save(ob);
		return ob;
	}
	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/
	@Override
	public Requisition processRequisition(String id) {
		Requisition ob = requisitionRepo.findById(id).orElse(null);
		ob.setStatus("Processing");
		requisitionRepo.save(ob);
		return ob;
	}
	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/
	@Override
	public Requisition verifyRequisition(String id) {
		Requisition ob = requisitionRepo.findById(id).orElse(null);
		ob.setStatus("Verified");
		requisitionRepo.save(ob);
		return ob;
	}
	public RequisitionDto getRequisitionDtoFromEntity(Requisition ob) {
		ob.setProductId(ob.getProduct().getId());
		ob.setProductName(ob.getProduct().getName());
		ob.setUserId(ob.getUser().getId());
		RequisitionDto obj = new RequisitionDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
