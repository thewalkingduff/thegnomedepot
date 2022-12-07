package com.devduffy.gnomedepot.dto;


import com.devduffy.gnomedepot.entity.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private Integer id;
	private OrderDTO orderDTO;
	private ProductDTO productDTO;
	private Integer quantity;
	private Double total;

	// public OrderDetails toModel () {
	// 	return new OrderDetails(id, orderDTO.toModel(), productDTO.toModel()
	// 			, quantity, total);
	// }
}
