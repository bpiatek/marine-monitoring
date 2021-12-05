package pl.bpiatek.marinemonitoring.ais.domain;

import static org.mockito.BDDMockito.given;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselApiResponse;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselApiResponse.Geometry;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselGuiObject;

import java.util.List;

/**
 * Created by Bartosz Piatek on 05/12/2021
 */
@ExtendWith(MockitoExtension.class)
class VesselApiToGuiObjectMapperTest {

  @Mock
  private ShipTypeProvider shipTypeProvider;

  VesselApiToGuiObjectMapper mapper;

  @BeforeEach
  void setup() {
    mapper = new VesselApiToGuiObjectMapper(shipTypeProvider);
  }
  private static final String SHIP_TYPE = "Passenger";
  private static final String SHIP_NAME = "some ship";
  private static final Double LONGITUDE = 12.3;
  private static final Double LATITUDE = 20.1;

  @Test
  void shouldCorrectlyMap() {
    // given
    given(shipTypeProvider.getShipTypeName("31")).willReturn(SHIP_TYPE);
    VesselApiResponse vessel = createVessel(SHIP_NAME, LONGITUDE, LATITUDE);

    // when
    VesselGuiObject result = mapper.map(vessel);

    // then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(result.getCountry()).isEqualTo(vessel.getCountry());
    softly.assertThat(result.getShipType()).isEqualTo(SHIP_TYPE);
    softly.assertThat(result.getLongitude()).isEqualTo(LONGITUDE);
    softly.assertThat(result.getLatitude()).isEqualTo(LATITUDE);
    softly.assertThat(result.getName()).isEqualTo(SHIP_NAME);
    softly.assertAll();

  }

  private VesselApiResponse createVessel(String shipName, Double longitude, Double latitude) {
    Geometry geometry = new Geometry("", List.of(longitude, latitude));
    return new VesselApiResponse(
        "",
        123,
        geometry,
        "31",
        shipName,
        999,
        "aasa",
        "UK",
        "",
        "Norway",
        true,
        2,
        32.0
    );
  }
}
