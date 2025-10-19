package org.common.entities.mapper;

import org.common.entities.dbentities.Customers;
import org.common.entities.dbentities.OrderItems;
import org.common.entities.dbentities.Orders;
import org.common.entities.dbentities.Products;
import org.common.entities.dtamodel.CustomersDTO;
import org.common.entities.dtamodel.OrderItemsDTO;
import org.common.entities.dtamodel.OrdersDTO;
import org.common.entities.dtamodel.ProductsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTAToEntityMapper {

	DTAToEntityMapper INSTANCE = Mappers.getMapper(DTAToEntityMapper.class);

	Customers customersToEntityMapping(CustomersDTO source);

	CustomersDTO customersFromEntityMapping(Customers source);

	OrderItems orderItemsToEntityMapping(OrderItemsDTO source);

	OrderItemsDTO orderItemsFromEntityMapping(OrderItems source);

	Orders ordersToEntityMapping(OrdersDTO source);

	OrdersDTO ordersFromEntityMapping(Orders source);

	Products productsToEntityMapping(ProductsDTO source);

	ProductsDTO productsFromEntityMapping(Products source);

}
