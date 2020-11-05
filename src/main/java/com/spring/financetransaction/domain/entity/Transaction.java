package com.spring.financetransaction.domain.entity;

import com.spring.financetransaction.domain.enumeration.OperationType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Transaction extends Model {

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "operation_type")
  private OperationType operationType;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Column(name = "amount")
  private BigDecimal amount;
}
