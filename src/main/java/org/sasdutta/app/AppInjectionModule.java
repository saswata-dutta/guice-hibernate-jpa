package org.sasdutta.app;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.sasdutta.persistors.InMemUserDao;
import org.sasdutta.persistors.UserDao;
import org.sasdutta.services.Actions;
import org.sasdutta.services.DefaultActions;

public class AppInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    // "user-persister" is defined in the persistence.xml
    install(new JpaPersistModule("user-persister"));

    // bind classes
    bind(Actions.class).to(DefaultActions.class).in(Singleton.class);
    bind(UserDao.class).to(InMemUserDao.class).in(Singleton.class);
  }
}
