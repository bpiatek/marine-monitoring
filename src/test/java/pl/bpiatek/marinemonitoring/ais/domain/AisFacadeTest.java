package pl.bpiatek.marinemonitoring.ais.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Created by Bartosz Piatek on 05/12/2021
 */
@SpringBootTest
class AisFacadeTest {

  @Autowired
  @Qualifier("testFacade")
  private AisFacade aisFacade;

  @Autowired
  private AisTokenRepository aisTokenRepository;

  @MockBean
  ScheduledTasks scheduledTasks;

  @MockBean
  AisApiFeignClient aisApiFeignClient;

  @Test
  void shouldGetValidToken() {
    // given
    String token = "123abc";
    aisTokenRepository.save(createAisToken(token));

    // when
    String actualValidationToken = aisFacade.getValidToken();

    // then
    assertThat(actualValidationToken).isEqualTo("Bearer 123abc");
  }

  private AisToken createAisToken(String accessToken) {
    return new AisToken(
        null,
        accessToken,
        "Bearer",
        "3600",
        null
    );
  }
}
