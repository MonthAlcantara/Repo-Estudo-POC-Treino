package io.github.monthalcantara.casadocodigo.exception

import java.util.function.Supplier

data class CampoDuplicadoException(var mensagem: String = "Campo duplicado", val campo: String): RuntimeException(mensagem) {

}
