package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
