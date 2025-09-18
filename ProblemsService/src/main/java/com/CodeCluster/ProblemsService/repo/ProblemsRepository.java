package com.CodeCluster.ProblemsService.repo;

import com.CodeCluster.ProblemsService.model.ProblemsTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface ProblemsRepository extends JpaRepository<ProblemsTable, UUID> {

    @NonNull
    Optional<ProblemsTable> findById(@NonNull UUID id);
    @NonNull
    Page<ProblemsTable> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<ProblemsTable> findByProblemName(String name);
}
