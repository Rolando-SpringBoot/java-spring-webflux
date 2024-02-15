package com.project.reactor.flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example05 {

  public static void main(String[] args) throws InterruptedException {
    /*
      concat(static) -> Concatenate all sources provided as a vararg, forwarding elements
                        emitted by the sources downstream. Sources who have been subscribed have an order.
      concatWith(instance) -> Concatenate emissions of this Flux with the provided Publisher.
                              Sources who have been subscribed have an order.

     */
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam");
    Flux<String> f2 = Flux.fromIterable(List.of("Mongo", "Apple"));
    Flux<String> f3 = Flux.concat(f1.delayElements(Duration.ofSeconds(1)),
            f2.delayElements(Duration.ofSeconds(2)), Mono.just("Hello World!"))
        .log();
    f3.subscribe(System.out::println);

    TimeUnit.SECONDS.sleep(10);

    Flux<String> f4 = f1.delayElements(Duration.ofSeconds(1))
        .concatWith(f2.delayElements(Duration.ofSeconds(2)))
        .concatWith(Mono.just("Hello World!!"))
        .log();
    f4.subscribe(System.out::println);

    TimeUnit.SECONDS.sleep(10);
  }

}
