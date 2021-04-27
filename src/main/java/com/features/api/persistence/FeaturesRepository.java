package com.features.api.persistence;

import com.features.api.persistence.entity.Features;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends CrudRepository<Features, Long> {
    Features findByEmailAndFeatureName(String email, String featureName);
}
