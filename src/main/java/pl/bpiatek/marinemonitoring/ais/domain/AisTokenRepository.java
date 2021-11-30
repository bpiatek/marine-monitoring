package pl.bpiatek.marinemonitoring.ais.domain;

import org.springframework.data.repository.Repository;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
interface AisTokenRepository extends Repository<AisToken, Long> {
  AisToken save(AisToken token);
  AisToken findTopByOrderByCreatedAtDesc();
}
