package com.cjemison.demo.service.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by cjemison on 5/17/16. <p>aggregates GUIDS, nothing special</p>
 */
public class ExampleCommand extends HystrixCommand<List<String>> {
  private final static Logger logger = LoggerFactory.getLogger(ExampleCommand.class);
  private String name;

  public ExampleCommand(final String name) {
    super(HystrixCommandGroupKey.Factory.asKey(name));
    this.name = name;
  }

  @Override
  protected List<String> run() throws Exception {
    logger.info("started");

    List<String> list = IntStream.range(0, 1000).boxed().map(c -> UUID.randomUUID().toString()
    ).collect
          (Collectors.toList());

    logger.info("done");
    return list;
  }
}
