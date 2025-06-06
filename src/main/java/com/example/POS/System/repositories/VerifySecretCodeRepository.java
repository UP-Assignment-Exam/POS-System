package com.example.POS.System.repositories;

import com.example.POS.System.models.User;
import com.example.POS.System.models.VerifySecretCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifySecretCodeRepository extends JpaRepository<VerifySecretCode, Integer> {
    Optional<VerifySecretCode> findOneByEmailAndCodeAndStatus(String email, String code, Boolean status);
    Optional<VerifySecretCode> findOneByEmailAndStatus(String email, Boolean status);
}

