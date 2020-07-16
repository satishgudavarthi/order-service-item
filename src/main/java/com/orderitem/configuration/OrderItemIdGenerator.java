package com.orderitem.configuration;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderItemIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session,
			Object object) throws HibernateException {
		StringBuilder builder = new StringBuilder(UUID.randomUUID().toString()
				.replace("-", ""));

		return "OT-" + builder.toString();
	}

}