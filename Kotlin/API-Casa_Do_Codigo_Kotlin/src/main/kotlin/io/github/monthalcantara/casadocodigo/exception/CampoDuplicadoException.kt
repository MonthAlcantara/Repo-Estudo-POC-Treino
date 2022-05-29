package io.github.monthalcantara.casadocodigo.exception

data class CampoDuplicadoException(var mensagem: String = "Campo duplicado", val campo: String): RuntimeException(mensagem) {

}
