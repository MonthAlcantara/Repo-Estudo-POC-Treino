package io.github.monthalcantara.domain.visitor;

public interface Visitor<T, R> {

    R visit(T t);
}
