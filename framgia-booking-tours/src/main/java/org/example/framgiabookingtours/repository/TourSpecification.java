package org.example.framgiabookingtours.repository;

import org.example.framgiabookingtours.entity.Tour;
import org.example.framgiabookingtours.enums.TourStatus;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TourSpecification {

    /**
     * Specification: Chỉ lấy Tour có status là AVAILABLE
     */
    public static Specification<Tour> isAvailable() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("status"), TourStatus.AVAILABLE);
    }
    
    /**
     * Specification: Tìm kiếm theo từ khóa (Name hoặc Location)
     * @param keyword Từ khóa tìm kiếm (q)
     */
    public static Specification<Tour> hasKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return null;
        }
        String pattern = "%" + keyword.toLowerCase().trim() + "%";
        
        return (root, query, criteriaBuilder) -> {
            Predicate nameLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern);
            Predicate locationLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), pattern);
            // Kết hợp điều kiện OR: tìm theo Tên HOẶC Địa điểm
            return criteriaBuilder.or(nameLike, locationLike);
        };
    }

    /**
     * Specification: Lọc theo Category ID
     * @param categoryId ID của Category
     */
    public static Specification<Tour> hasCategoryId(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    /**
     * Specification: Lọc theo khoảng giá (Price Range)
     */
    public static Specification<Tour> hasPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) >= 0) {
                // Lớn hơn hoặc bằng giá Min
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) > 0) {
                // Nhỏ hơn hoặc bằng giá Max
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            
            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction(); // Trả về điều kiện TRUE nếu không có lọc giá nào
            }
            // Kết hợp điều kiện AND: Price >= Min AND Price <= Max
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}