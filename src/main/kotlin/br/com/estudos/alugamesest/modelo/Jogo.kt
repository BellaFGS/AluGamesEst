package org.example.br.com.estudos.alugamesest.modelo

data class Jogo(val titulo: String, val capa: String ){

    var descricao: String? = null

    override fun toString(): String {
        return "\n\n\n------------------------------------------------------------------------------------------------------------------------------------------\n"+
                "Meu jogo: \n"+
                "Titulo: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao \n"+
                "------------------------------------------------------------------------------------------------------------------------------------------\n"
    }
}