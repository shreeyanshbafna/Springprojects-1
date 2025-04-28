package com.start.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.start.Model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
