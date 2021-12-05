package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import pl.bpiatek.marinemonitoring.ais.api.token.AisTokenResponse;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Slf4j
@RequiredArgsConstructor
class ScheduledTasks {

  private final AisTokenRepository aisTokenRepository;
  private final AisTokenProvider tokenProvider;

  @EventListener(ContextRefreshedEvent.class)
  public void onStartup() {
    log.info("Requesting AIS for a token on application startup.");
    obtainAndSaveToken();
  }

  @Scheduled(cron = "${ais.scheduler.refresh.token}")
  public void refreshTokenEvery() {
    log.info("Refreshing AIS token on schedule.");
    obtainAndSaveToken();
  }

  private void obtainAndSaveToken() {
    AisTokenResponse aisTokenResponse = tokenProvider.provideToken();
    aisTokenRepository.save(AisToken.formResponse(aisTokenResponse));
    log.info("Access token for AIS obtained and saved.");
  }
}
