package org.sasdutta.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import org.sasdutta.entities.User;
import org.sasdutta.services.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    LOGGER.info(">>>>> App started");

    Injector injector = Guice.createInjector(
        new AppInjectionModule()
    );

    injector.getBindings().forEach(
        (key, value) -> LOGGER.info(">>>>> {} => {}", key, value));

    // start jpa
    PersistService persistService = injector.getInstance(PersistService.class);
    persistService.start();

    Actions actions = injector.getInstance(Actions.class);
    drive(actions);

    LOGGER.info(">>>>> End App");
  }

  static void drive(final Actions actions) {
    List<User> users =
        actions.upsertUsers(
            Arrays.asList(new User("sas"), new User("dut"), new User("sas")));

    long id = users.get(0).getId();
    LOGGER.info(">>>>> findUserById(1) = {}", actions.findUserById(id));
    LOGGER.info(">>>>> findUserById(4) = {}", actions.findUserById(4));

    LOGGER.info(">>>>> findUserById(4) = {}", actions.findUserById(4));

    LOGGER.info(">>>>> findUserByName(\"sas\") = {}", actions.findUserByName("sas"));
    LOGGER.info(">>>>> findUserByName(\"s\") = {}", actions.findUserByName("s"));

    actions.delete(users.subList(1,3));
    LOGGER.info(">>>>> delete(1,2)");
    LOGGER.info(">>>>> findUserByName(\"dut\") = {}", actions.findUserByName("dut"));
    LOGGER.info(">>>>> findUserByName(\"sas\") = {}", actions.findUserByName("sas"));
  }
}
