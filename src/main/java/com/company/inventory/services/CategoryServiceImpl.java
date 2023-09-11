package com.company.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoryResponseRest> search() {
		// TODO Auto-generated method stub
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> cateogry  = (List<Category>) categoryDao.findAll(); 
			response.getCategoryResponse().setCategory(cateogry);
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Respuesta no OK", "-1","error al consultar");
			e.getStackTrace();
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
	}
	

}
