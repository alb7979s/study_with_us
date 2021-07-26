package com.ssafy.study_with_us.domain.repository;


import com.ssafy.study_with_us.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, String> {
    Theme save(Theme theme);
}
