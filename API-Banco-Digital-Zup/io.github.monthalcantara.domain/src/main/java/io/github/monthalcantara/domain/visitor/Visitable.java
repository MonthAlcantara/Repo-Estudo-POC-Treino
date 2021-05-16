package io.github.monthalcantara.domain.visitor;

public interface Visitable<T> {

    void accept(final Visitor<T, ?> element);
}
