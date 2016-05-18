package com.cjemison.demo.service.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import java.util.UUID;
import java.util.stream.IntStream;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by cjemison on 5/18/16.
 */
public class ExampleObserverCommand extends HystrixObservableCommand {
  private final String groupName;
  private final int value;


  public ExampleObserverCommand(final String groupName, final int value) {
    super(HystrixCommandGroupKey.Factory.asKey(groupName));
    this.groupName = groupName;
    this.value = value;
  }

  @Override
  protected Observable<String> construct() {
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        IntStream.range(0, value).forEach(c -> {
          if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(UUID.randomUUID().toString());
          }
        });
        subscriber.onCompleted();
      }
    });

    return observable;
  }
}
