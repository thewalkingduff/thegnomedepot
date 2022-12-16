package com.devduffy.gnomedepot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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

}