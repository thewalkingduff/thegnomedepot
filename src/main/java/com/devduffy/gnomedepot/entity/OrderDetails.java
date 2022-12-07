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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.devduffy.gnomedepot.dto.OrderDTO;
import com.devduffy.gnomedepot.dto.OrderDetailsDTO;
import com.devduffy.gnomedepot.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orderdetails")
public class OrderDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orders_id", nullable = false)
    private Order order;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    @Min(0)
    @Column(name="quantity")
    private Integer quantity;

    @Min(0)
    @Column(name="total")
    private Double total;

    public OrderDetailsDTO toDTO () {
		return new OrderDetailsDTO(id, order.toDTO(), product.toDTO(), quantity, total);
	}
    
}