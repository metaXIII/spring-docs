package com.metaxiii.fr.batchprocessing.processor;

import com.metaxiii.fr.batchprocessing.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public Person process(final Person person) {
    final String firstName = person.firstName().toUpperCase();
    final String lastName = person.lastName().toUpperCase();
    final var transformed = new Person(firstName, lastName);
    if (log.isDebugEnabled()) {
      log.info("Converting (%s) into (%s)".formatted(person, transformed));
    }
    return transformed;
  }
}
