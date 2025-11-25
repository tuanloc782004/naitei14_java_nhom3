package org.example.framgiabookingtours.repository;

import org.example.framgiabookingtours.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByReviewIdAndIsDeletedFalse(Long reviewId);
}
