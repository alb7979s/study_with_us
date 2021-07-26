package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile save(Profile profile);
}
