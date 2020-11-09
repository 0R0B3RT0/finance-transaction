package com.spring.financetransaction.domain.entity;

import static java.util.UUID.fromString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
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

    assertThat(model.getId(), notNullValue());
    assertThat(model.isNew(), is(false));
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
    model.setId(fromString("BD8CF80D-B3C2-4D41-B2B3-A9C1AB81FC7E"));

    assertThat(model.isNew(), is(false));
  }
}
