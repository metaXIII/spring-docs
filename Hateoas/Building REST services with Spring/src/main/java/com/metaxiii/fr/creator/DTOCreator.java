package com.metaxiii.fr.creator;

/**
 * @param <E> Entity
 * @param <D> DTO
 */
public interface DTOCreator<E, D> {
    D toDTO(final E e);
}
