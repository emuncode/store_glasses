package com.fpoly.repository;

import com.fpoly.entity.Cart;
import com.fpoly.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByProductDetail(Integer id);
}
