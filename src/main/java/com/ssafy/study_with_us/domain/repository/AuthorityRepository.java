package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
