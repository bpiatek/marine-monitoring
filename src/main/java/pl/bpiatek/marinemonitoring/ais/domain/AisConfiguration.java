package pl.bpiatek.marinemonitoring.ais.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Bartosz Piatek on 05/12/2021
 */
@Configuration
class AisConfiguration {

  @Bean("testFacade")
  AisFacade aisFacadeTest(AisTokenRepository tokenRepository,
                          AisApiFeignClient aisApiFeignClient,
                          ShipTypeRepository shipTypeRepository) {
    return aisFacade(tokenRepository,
                     aisApiFeignClient,
                     shipTypeRepository);
  }

  @Primary
  @Bean
  AisFacade aisFacade(AisTokenRepository tokenRepository,
                      AisApiFeignClient aisApiFeignClient,
                      ShipTypeRepository shipTypeRepository) {
    var shipTypeProvider = new ShipTypeProvider(shipTypeRepository);
    var vesselApiToGuiObjectMapper = new VesselApiToGuiObjectMapper(shipTypeProvider);

    return new AisFacade(tokenRepository,
                         aisApiFeignClient,
                         vesselApiToGuiObjectMapper);
  }

  @Bean
  ScheduledTasks scheduledTasks(AisProperties aisProperties,
                                AisTokenFeignClient aisTokenFeignClient,
                                AisTokenRepository aisTokenRepository) {
    var aisTokenProvider = new AisTokenProvider(aisProperties, aisTokenFeignClient);
    return new ScheduledTasks(aisTokenRepository, aisTokenProvider);
  }
}
