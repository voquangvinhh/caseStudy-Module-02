package com.example.casetest.repository;

import com.example.casetest.dto.RoleDto;
import com.example.casetest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRole(RoleDto roleDto);
    User findByAccount(String account); //Đổi lại thành account
    @Query(value = "select * from user where  user.firstname like %:firstname% ", nativeQuery = true)
    Page<User> findAllByFullNameContaining(String firstname, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT r.name FROM role r " +
                    "INNER JOIN user u ON r.id = u.role_id " +
                    "WHERE u.account = :account")
    List<String> findRolesByUsername(@Param("account") String account);
}
