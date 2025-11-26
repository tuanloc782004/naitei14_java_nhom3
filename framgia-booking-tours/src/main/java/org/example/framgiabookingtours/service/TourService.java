package org.example.framgiabookingtours.service;

import java.math.BigDecimal;

import org.example.framgiabookingtours.dto.response.TourResponseDTO;
import org.springframework.data.domain.Page;

public interface TourService {

	Page<TourResponseDTO> getAvailableTours(int page, int size, String sortBy, String sortDirection, 
            String keyword, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice);
	
	TourResponseDTO getTourDetail(Long tourId);
}
