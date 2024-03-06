package com.metaxiii.fr.batchprocessing.config;

import com.metaxiii.fr.batchprocessing.Person;
import com.metaxiii.fr.batchprocessing.listener.JobCompletionNotificationListener;
import com.metaxiii.fr.batchprocessing.processor.PersonItemProcessor;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Slf4j
@Configuration
public class BatchConfiguration {

  @Bean
  public Job importUserJob(
    final JobRepository jobRepository,
    final Step step1,
    final JobCompletionNotificationListener listener
  ) {
    return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
  }

  @Bean
  public PersonItemProcessor processor() {
    log.info("processor called");
    return new PersonItemProcessor();
  }

  @Bean
  public FlatFileItemReader<Person> reader() {
    log.info("reader called");
    return new FlatFileItemReaderBuilder<Person>()
      .name("personItemReader")
      .resource(new ClassPathResource("sample-data.csv"))
      .delimited()
      .names("firstName", "lastName")
      .targetType(Person.class)
      .build();
  }

  @Bean
  public Step step1(
    final JobRepository jobRepository,
    final DataSourceTransactionManager transactionManager,
    final FlatFileItemReader<Person> reader,
    final PersonItemProcessor processor,
    final JdbcBatchItemWriter<Person> writer
  ) {
    return new StepBuilder("step1", jobRepository)
      .<Person, Person>chunk(3, transactionManager)
      .reader(reader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public JdbcBatchItemWriter<Person> writer(final DataSource dataSource) {
    log.info("call writer");
    return new JdbcBatchItemWriterBuilder<Person>()
      .sql("insert into people (first_name, last_name) values (:firstName, :lastName)")
      .dataSource(dataSource)
      .beanMapped()
      .build();
  }
}
