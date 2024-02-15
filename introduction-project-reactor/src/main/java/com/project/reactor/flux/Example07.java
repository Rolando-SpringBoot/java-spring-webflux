package com.project.reactor.flux;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

public class Example07 {

  public static void main(String[] args) {
    /*
      zip(static) -> Zip two sources together, that is to say wait for all the sources to emit one element
                     and combine these elements once into a Tuple2.
      zipWith(instance) -> Zip this Flux with another Publisher source, that is to say wait for
                           both to emit one element and combine these elements once into a Tuple2.
     */
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam");
    Flux<Integer> f2 = Flux.just(123, 2, 24, 200);
    Flux<Tuple2<String, Integer>> f3 = Flux.zip(f1, f2).log();

    f3.subscribe(data -> {
      System.out.println(data.getT1());
      System.out.println(data.getT2());
    });

    Flux<String> f4 = Flux.zip(f1, f2, (name, num) -> name + ":" + num)
        .log();
    f4.subscribe(System.out::println);

    Flux<Tuple2<Tuple2<String, Integer>, String>> f5 = f1.zipWith(f2).zipWith(Flux.fromIterable(List.of("Mongo", "Apple", "Macbook")))
        .log();
    f5.subscribe(data -> {
      System.out.println(data.getT1().getT1());
      System.out.println(data.getT1().getT2());
      System.out.println(data.getT2());
    });

    Flux<String> f6 = f1.zipWith(f2, (name, num) -> name + ":" + num);
    f6.subscribe(System.out::println);
  }

}
