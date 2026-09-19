package com.KambaFlix.Repository;

import com.KambaFlix.Entity.Streaming;
import com.sun.jdi.LongValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming , Long> {
}
