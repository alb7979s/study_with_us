package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Blacklist save(Blacklist blacklist);
}
