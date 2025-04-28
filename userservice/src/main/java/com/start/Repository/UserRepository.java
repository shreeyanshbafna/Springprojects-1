package com.start.Repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.start.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	

}
