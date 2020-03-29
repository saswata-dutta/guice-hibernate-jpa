package org.sasdutta.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.sasdutta.entities.User;
import org.sasdutta.services.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    LOGGER.info(">>>>> App started");

    Injector injector = Guice.createInjector(
        new AppInjectionModule()
    );

    injector.getBindings().forEach(
        (key, value) -> LOGGER.info(">>>>> {} => {}", key, value));

    Actions actions = injector.getInstance(Actions.class);
    drive(actions);

    LOGGER.info(">>>>> End App");
  }

  static void drive(final Actions actions) {
    actions.upsertUsers(Arrays.asList(new User(1, "sas"), new User(2, "dut"), new User(3, "sas")));
    LOGGER.info(">>>>> findUserById(1) = {}", actions.findUserById(1));
    LOGGER.info(">>>>> findUserById(4) = {}", actions.findUserById(4));
    LOGGER.info(">>>>> findUserByName(\"sas\") = {}", actions.findUserByName("sas"));
    LOGGER.info(">>>>> findUserByName(\"s\") = {}", actions.findUserByName("s"));
  }
}
