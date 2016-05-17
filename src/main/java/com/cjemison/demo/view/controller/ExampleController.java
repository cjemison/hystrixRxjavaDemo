package com.cjemison.demo.view.controller;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by cjemison on 5/16/16.
 */
public interface ExampleController {

  DeferredResult<?> findAll();
}
