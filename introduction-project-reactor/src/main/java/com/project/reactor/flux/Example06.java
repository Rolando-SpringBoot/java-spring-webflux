package com.project.reactor.flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Flux;

public class Example06 {

  public static void main(String[] args) throws InterruptedException {
    /*
      merge(static) -> Merge data from Publisher sequences contained in an array / vararg into
                       an interleaved merged sequence. Unlike concat, sources are subscribed eagerly.
      mergeWith(instance) -> Merge data from this Flux and a Publisher into an interleaved merged sequence.
                             Unlike concat, inner sources are subscribed eagerly.
     */
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Guatam");
    Flux<String> f2 = Flux.fromIterable(List.of("Mongo", "Apple"));
    Flux<String> f3 = Flux.merge(f1.delayElements(Duration.ofSeconds(1)),
            f2.delayElements(Duration.ofSeconds(2)))
        .log();
    f3.subscribe(System.out::println);

    TimeUnit.SECONDS.sleep(10);

    Flux<String> f4 = f1.delayElements(Duration.ofSeconds(1))
        .mergeWith(f2.delayElements(Duration.ofSeconds(2)))
        .log();

    f4.subscribe(System.out::println);

    TimeUnit.SECONDS.sleep(10);

  }

}
