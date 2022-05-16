package io.github.monthalcantara.forum.model

enum class StatusTopico(val descricao: String) {
    NAO_RESPONDIDO("nao_respondido"),
    NAO_SOLUCIONADO("nao_solucionado"),
    SOLUCIONADO("solucionado"),
    FECHADO("fechado");

    companion object {

        private val mapStatusTopico = values().map { Pair(it.descricao, it) }.toMap()

        fun existsType(descricao: String): Boolean {
            return mapStatusTopico.contains(descricao)
        }

        fun toEnum(descricao: String): StatusTopico{
            return valueOf(descricao.uppercase())
        }
    }
}

