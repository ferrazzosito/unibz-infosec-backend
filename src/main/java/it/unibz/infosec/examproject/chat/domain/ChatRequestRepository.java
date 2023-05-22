package it.unibz.infosec.examproject.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ChatRequestRepository extends JpaRepository<ChatRequest, Long> {
    @NonNull
    Optional<ChatRequest> findById(@NonNull Long id);
}
