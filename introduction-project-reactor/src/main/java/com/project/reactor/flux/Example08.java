package com.project.reactor.flux;

import reactor.core.publisher.Flux;

public class Example08 {

  public static void main(String[] args) {
    /*
      doOnSubscribe() -> add behavior (side-effect) triggered when the Flux is being subscribed
      doOnNext() -> add behavior (side-effect) triggered when the Flux emits an item
      doOnError() -> add behavior (side-effect) triggered when the Flux completes with an error.
      doOnComplete() -> add behavior (side-effect) triggered when the Flux completes successfully
      doOnEach() -> add behavior (side-effects) triggered when the Flux emits an item,
      fails with an error or completes successfully
     */
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .doOnSubscribe(data -> System.out.println("It will be printed before to onSubscribe event : " + data))
        .doOnNext(name -> System.out.println("It will be printed before to execute onNext event : " + name))
        .doOnError(ex -> System.out.println("It will printed before to execute onError event : " + ex.getMessage()))
        .doOnComplete(() -> System.out.println("it will be printed before to onComplete event"))
        .doOnEach(signal -> System.out.println("each: " + signal))
        .log();

    f1.subscribe(name -> System.out.println("It will be printed after to onNext event : " + name),
        ex -> System.out.println("It will be printed after to onError event : " + ex.getMessage()),
        () -> System.out.println("It will be printed after to onComplete event"));
  }

}
