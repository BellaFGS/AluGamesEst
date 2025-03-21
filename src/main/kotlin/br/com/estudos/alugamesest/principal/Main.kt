package org.example.br.com.estudos.alugamesest.principal

import br.com.estudos.alugamesest.modelo.Gamer
import br.com.estudos.alugamesest.servicos.ConsumoApi
import org.example.br.com.estudos.alugamesest.modelo.Jogo
import transformarEmIdade
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluído com sucesso. Dados do Gamer:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {

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
           gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos Buscado:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por título: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach{
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("\nJogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()

    if (opcao.equals("s", true)){
        println(gamer.jogosBuscados)
        println("\nInforme a posição do jogo que deseja exluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista atualizada: ")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso.")
}

