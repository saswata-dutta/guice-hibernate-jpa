package org.sasdutta.app;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceInitializer.class);


  @Inject
  PersistenceInitializer(PersistService service) {
    service.start();
    LOGGER.info(">>>>> JPA started and ready");
  }
}
