package com.webshop.webshopbackend.domain.mapper;

import java.text.ParseException;

public interface Mapper<E, D> {

    E fromDTOToEntity(D d) throws ParseException;

    D fromEntityToDTO(E e);

    E fromIdToEntity(String id);
}
