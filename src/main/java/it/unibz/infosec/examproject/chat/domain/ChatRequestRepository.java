package it.unibz.infosec.examproject.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRequestRepository extends JpaRepository<ChatRequest, Long> {
    @Override
    @NonNull
    Optional<ChatRequest> findById(@NonNull Long id);

    @NonNull
    List<ChatRequest> findByChatId(@NonNull String id);

    List<ChatRequest> findByVendor(Long id);
}
