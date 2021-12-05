package pl.bpiatek.marinemonitoring.ais.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.ais.domain.AisFacade;

import javax.annotation.PostConstruct;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Slf4j
@Component
@RequiredArgsConstructor
class ScheduledTasks {

  private final AisFacade aisFacade;

  @PostConstruct
  public void onStartup() {
    log.info("Requesting AIS for a token on application startup.");
    aisFacade.obtainAndSaveToken();
  }

  @Scheduled(cron = "${ais.scheduler.refresh.token}")
  public void refreshTokenEvery() {
    log.info("Refreshing AIS token on schedule.");
    aisFacade.obtainAndSaveToken();
  }
}
