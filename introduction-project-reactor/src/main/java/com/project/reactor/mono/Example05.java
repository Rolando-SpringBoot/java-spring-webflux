package com.project.reactor.mono;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example05 {

  public static void main(String[] args) {
    /*
      concatWith(instance) ->  Join to mono and provide flux.
      delayElements() -> Delay each of this Flux elements (Subscriber.onNext signals) by
                       a given Duration. Signals are delayed and continue on the parallel
                       default Scheduler, but empty sequences or immediate error signals
                       are not delayed.
     */
    Mono<String> m1 = Mono.just("Learn Code With Durgesh");
    Mono<String> m2 = Mono.just("Subscribe to this channel");

    System.out.println(Thread.currentThread().getName());
    Flux<String> f1 = m1.concatWith(m2)
            .delayElements(Duration.ofMillis(2000));

    f1.subscribe(data -> {
      System.out.println(Thread.currentThread().getName());
      System.out.println("data = " + data);
    });

    try {
      Thread.sleep(4500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Terminated main thread");
  }

}
