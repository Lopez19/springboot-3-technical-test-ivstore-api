package com.horacioing.ivstore.infrastructure.persistence.mappers;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericMapper<E, D> {
    /**
     * To domain
     *
     * @param entity the entity
     * @return the Domain
     */
    D toDomain(E entity);

    /**
     * To Entity
     *
     * @param domain the domain
     * @return the Entity
     */
    E toEntity(D domain);

    /**
     * To DomainList
     *
     * @param entityList the EntityList
     * @return the List<D>
     */
    List<D> toDomainList(List<E> entityList);

    /**
     * to EntityList
     *
     * @param domainList the DomainList
     * @return the list<E>
     */
    List<E> toEntityList(List<D> domainList);

    /**
     * To DomainPage.
     *
     * @param entityPage the entity page
     * @return the domainPage
     */
    default Page<D> toDomainPage(Page<E> entityPage) {
        return entityPage.map(this::toDomain);
    }

    /**
     * To EntityPage.
     *
     * @param domainPage the domain page
     * @return the entityPage
     */
    default Page<E> toEntityPage(Page<D> domainPage) {
        return domainPage.map(this::toEntity);
    }

    /**
     * To DomainList.
     *
     * @param entityPage the entity Page
     * @return the domainList
     */
    List<D> toDomainList(Page<E> entityPage);
}
