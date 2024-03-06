package com.metaxiii.fr.batchprocessing.listener;

import com.metaxiii.fr.batchprocessing.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JobCompletionNotificationListener implements JobExecutionListener {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public void afterJob(final JobExecution jobExecution) {
    log.info("We are after job");
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED ! Time to verify the result");
      jdbcTemplate
        .query("select first_name, last_name from people", new DataClassRowMapper<>(Person.class))
        .forEach(person -> log.info("Found <{{}} in the database>", person));
    }
  }
}
