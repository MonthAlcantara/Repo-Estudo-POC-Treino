package io.github.monthalcantara.core.visitor;

public interface Visitable<T> {

    void accept(final Visitor<T, ?> element);
}
