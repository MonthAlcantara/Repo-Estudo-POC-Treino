package io.github.monthalcantara.core.command;

public interface Command<R> {

   R process(final CommandContext commandContext);

}
