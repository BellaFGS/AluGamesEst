package org.example.br.com.estudos.alugamesest.principal

import br.com.estudos.alugamesest.servicos.ConsumoApi
import org.example.br.com.estudos.alugamesest.modelo.Jogo
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")

    val busca = leitura.nextLine()
    val buscaApi = ConsumoApi()
    buscaApi.buscaJogo(busca)

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        val informacaoJogo = buscaApi.buscaJogo(busca)
        meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb)
    }

    resultado.onFailure {
        println("Jogo não encontrado. Tente outro id.")
    }

    resultado.onSuccess {
        println("Deseja inserir descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            println("Insira a descrição personalizada para o jogo:")

            val descricaoPersonalizada = leitura.nextLine()

            meuJogo?.descricao = descricaoPersonalizada
        } else {
            meuJogo?.descricao = meuJogo?.titulo
        }
        println(meuJogo)
    }

}

