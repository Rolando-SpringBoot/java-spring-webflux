package com.project.reactor.mono;

import static java.lang.StringTemplate.STR;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example01 {

  public static void main(String[] args) {
    // Mono - Publisher that have 0..1 items

    /*
      empty() -> Create a Mono that completes without emitting any item.
      just() -> Create a new Mono that emits the specified item,
                which is captured at instantiation time.
      justOrEmpty() -> Create a new Mono that emits the specified item if Optional.isPresent()
                       otherwise only emits onComplete.
      suscribe() -> Subscribe a Consumer to this Mono that will consume all the sequence.
      log() -> Observe all Reactive Streams signals and trace them using Logger support.
     */

    Mono<String> m1 = Mono.just("testing");

    m1.subscribe(new Subscriber<String>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("Subscription done");
        subscription.request(1);
      }
      @Override
      public void onNext(String data) {
        System.out.println(STR."data : \{data}");
      }
      @Override
      public void onError(Throwable throwable) {
        System.out.println(STR."error: \{throwable.getMessage()}");
      }
      @Override
      public void onComplete() {
        System.out.println("Completed");
      }
    });

    Mono<String> m2 = Mono
        .just("Learn Code with Durgesh")
        .log();
    /* consume mono by subscribing */
    m2.subscribe(System.out::println);

    Mono<Void> m3 = Mono.empty();
    m3.subscribe(System.out::println);

    String cad = "hola";
    Mono<String> m4 = Mono.justOrEmpty(Optional.ofNullable(cad)).log();
    m4.subscribe(System.out::println);

  }

}
