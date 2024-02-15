import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactiveTest {

  @Test
  void mapFlux() {
    Flux<String> f1 = Flux.just("ana", "sue", "peter")
        .map(String::toUpperCase).log();
    StepVerifier.create(f1)
        .expectNext("ANA", "SUE", "PETER")
        .verifyComplete();

    StepVerifier.create(f1)
        .expectNextCount(3)
        .verifyComplete();
  }

  @Test
  void filterFlux() {
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .filter(name -> name.length() > 4).log();
    StepVerifier.create(f1)
        .expectNextCount(3)
        .verifyComplete();
  }

  @Test
  void flatMapFlux() {
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .flatMap(name -> Flux.just(name.split("")))
        .delayElements(Duration.ofSeconds(1))
        .log();

    StepVerifier.create(f1)
        .expectNextCount(22)
        .verifyComplete();
  }

  @Test
  void transformExample() {
    Function<Flux<String>, Flux<String>> funInterface = fluxName -> fluxName
        .map(String::toUpperCase);

    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .transform(funInterface).log();

    StepVerifier.create(f1)
        .expectNextCount(4)
        .verifyComplete();
  }

  @ValueSource(ints = {8,4})
  @ParameterizedTest()
  void defaultIfEmptyExample(int length) {
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .filter(name -> name.length() > length)
        .defaultIfEmpty("Learn code with Durgesh")
        .log();
    StepVerifier.create(f1)
        .expectNextCount((length == 8) ? 1 : 3)
        .verifyComplete();
  }

  @ValueSource(ints = {8,4})
  @ParameterizedTest()
  void switchIfEmptyExample(int length) {
    Flux<String> f2 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .filter(name -> name.length() > length)
        .switchIfEmpty(Flux.fromIterable(List.of("Mongo", "Apple")))
        .log();

    StepVerifier.create(f2)
        .expectNextCount((length == 8) ? 2 : 3)
        .verifyComplete();
  }

  @Test
  void concatExample() {
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam");
    Flux<String> f2 = Flux.concat(f1, Flux.fromIterable(List.of("Mongo", "Apple")))
        .log();
    StepVerifier.create(f2)
        .expectNextCount(6)
        .verifyComplete();
  }

}