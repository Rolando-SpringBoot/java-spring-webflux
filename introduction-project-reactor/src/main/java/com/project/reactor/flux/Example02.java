package com.project.reactor.flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Flux;

public class Example02 {

  public static void main(String[] args) {
    /*
      map() -> Transform the items emitted by this Flux by applying a synchronous
               function to each item.
      flatMap() -> Transform the elements emitted by this Flux asynchronously into Publishers,
                   then flatten these inner publishers into a single Flux through merging,
                   which allow them to interleave.
      filter() -> Evaluate each source value against the given Predicate.
                  If the predicate test succeeds, the value is emitted.
                  If the predicate test fails, the value is ignored and a request of 1 is made upstream.
      delayElements() -> Delay each of this Flux elements (Subscriber.onNext signals) by a given Duration.
                         Signals are delayed and continue on the parallel default Scheduler,
                         but empty sequences or immediate error signals are not delayed.
     */
    Flux<String> f1 = Flux.just("sue", "luck", "peter")
        .map(String::toUpperCase)
        .log();
    f1.subscribe(System.out::println);

    Flux<String> f2 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .filter(name -> name.length() > 4).log();
    f2.subscribe(System.out::println);

    Flux<String> f3 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .flatMap(name -> Flux.just(name.split("")))
        .delayElements(Duration.ofSeconds(2)).log();
    f3.subscribe(System.out::println);
    try {
      TimeUnit.SECONDS.sleep(44);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
