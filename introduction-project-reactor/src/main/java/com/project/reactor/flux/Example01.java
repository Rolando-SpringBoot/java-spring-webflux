package com.project.reactor.flux;

import java.util.List;
import reactor.core.publisher.Flux;

public class Example01 {

  public static void main(String[] args) {
    // Flux - Publisher that have 0..n items
    /*
      empty() -> Create a Flux that completes without emitting any item.
      just() -> Create a Flux that emits the provided elements and then completes.
      log() -> Observe all Reactive Streams signals and trace them using Logger support.
               Default will use Level.INFO and java.util.logging. If SLF4J is available, it will be used
      fromIterable() -> Create a Flux that emits the items contained in the provided Iterable.
                        The Iterable.iterator() method will be invoked at least once and at most
                        twice for each subscriber.
     */

    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi");

    f1.subscribe(data -> {
      System.out.println("data = " + data);
      System.out.println("Done with flux data");
    });

    List<String> fruits = List.of("Mongo", "Apple");
    Flux<String> f2 = Flux.fromIterable(fruits).log();

    f2.subscribe(System.out::println);

    Flux<Void> f3 = Flux.empty();
    f3.subscribe(System.out::println);

  }

}
