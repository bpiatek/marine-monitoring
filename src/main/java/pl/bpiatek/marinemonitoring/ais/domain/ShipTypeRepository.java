package pl.bpiatek.marinemonitoring.ais.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Bartosz Piatek on 04/12/2021
 */
interface ShipTypeRepository extends Repository<ShipType, Long> {
  List<ShipType> findAll();
}
