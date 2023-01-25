package com.opentrends.apibuilder.adapters.outbound.persistence.repositories;

import com.opentrends.apibuilder.adapters.outbound.persistence.dto.BeanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeanEntityRepository extends JpaRepository<BeanEntity, Integer> {
}
