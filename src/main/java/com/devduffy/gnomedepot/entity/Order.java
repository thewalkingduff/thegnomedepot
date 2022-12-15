package com.devduffy.gnomedepot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.devduffy.gnomedepot.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "orders")
public class Order {

		public Order(Integer id2, User model, Date orderDate2, String status2, Date shippedDate2) {
    }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "order_date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date orderDate;

        @Column(name = "status", nullable = false)
        private String status;

        @Column(name = "shipped_date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date shippedDate;

    
        @Column(name="total_amount")
        private Double totalAmount;

        @ToString.Exclude
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "users_id", nullable = false)
        private User user;

        public Order(Integer id2, User model) {
        }

        public OrderDTO toDTO() {
          return new OrderDTO(id, user.toDTO(), orderDate, status, shippedDate);
        }


  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getOrderDate() {
    return this.orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getShippedDate() {
    return this.shippedDate;
  }

  public void setShippedDate(Date shippedDate) {
    this.shippedDate = shippedDate;
  }

  public Double getTotalAmount() {
    return this.totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }
        

}