package pl.bpiatek.marinemonitoring.ais.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.ais.domain.AisFacade;

import javax.annotation.PostConstruct;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Component
@RequiredArgsConstructor
class ScheduledTasks {

  private final AisFacade aisFacade;

  @PostConstruct
  public void onStartup() {
    //TODO uncomment
//    aisFacade.generateAndSaveToken();
  }

  @Scheduled(cron = "${ais.scheduler.refresh.token}")
  public void refreshTokenEvery() {
    aisFacade.generateAndSaveToken();
  }


}
