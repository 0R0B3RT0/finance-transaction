package com.spring.financetransaction.domain.entity;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {

  private Model model;

  @Before
  public void before() {
    model = new Model() {};
  }

  @Test
  public void shouldUpdateCreatedAtUpdateAtEnabledAndIdFieldsWhenBeforePersistMethodAreCalled() {
    model.beforePersist();

    assertThat(model.getId(), nullValue());
    assertThat(model.isNew(), is(true));
    assertThat(model.getEnabled(), is(true));
    assertThat(model.getCreatedAt(), notNullValue());
    assertThat(model.getUpdatedAt(), notNullValue());
    assertThat(model.getCreatedAt(), equalTo(model.getUpdatedAt()));
  }

  @Test
  public void shouldUpdateUpdatedAtFieldWhenBeforeUpdateMethodAreCalled() {
    model.beforeUpdate();

    assertThat(model.getUpdatedAt(), notNullValue());
    assertThat(model.getUpdatedAt(), not(equalTo(model.getCreatedAt())));
  }

  @Test
  public void shouldSetCorrectValueToDeletedAtField() {
    LocalDateTime deletedAt = LocalDateTime.now();

    model.setDeletedAt(deletedAt);

    assertThat(model.getDeletedAt(), equalTo(deletedAt));
  }

  @Test
  public void shouldNewwRegister() {
    assertThat(model.isNew(), is(true));
  }

  @Test
  public void shouldNotNewwRegister() {
    model.setId(1l);

    assertThat(model.isNew(), is(false));
  }
}
