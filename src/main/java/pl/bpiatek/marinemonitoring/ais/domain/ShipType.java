package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Bartosz Piatek on 04/12/2021
 */
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
class ShipType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Long id;
  private String number;
  private String value;
}
