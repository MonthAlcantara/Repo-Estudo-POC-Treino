package io.github.monthalcantara.casadocodigo.exception

data class NaoAutorizadoException(var mensagem: String = "Opa, parece que você não permissão para acessar esse recurso") : RuntimeException(mensagem) {

}
