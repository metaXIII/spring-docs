package com.metaxiii.fr.creator;

/**
 * @param <E> Entity
 * @param <M> Model
 */
public interface DomainCreator<E, M> {
  E toDomain(final M m);
}
