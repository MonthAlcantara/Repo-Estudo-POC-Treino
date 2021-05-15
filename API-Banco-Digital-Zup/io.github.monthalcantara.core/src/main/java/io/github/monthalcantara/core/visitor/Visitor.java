package io.github.monthalcantara.core.visitor;

public interface Visitor<T, R> {

    R visit(T t);
}
