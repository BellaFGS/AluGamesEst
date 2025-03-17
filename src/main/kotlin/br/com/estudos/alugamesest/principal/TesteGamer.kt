package br.com.estudos.alugamesest.principal

import br.com.estudos.alugamesest.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Joãozinho", "joaozinho@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Bruce", "milhonariodegotan@gmail.com", "19/09/1920", "Batman")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario = "jacSkyWalker"

    }.also {
        println(gamer1.idInterno)
    }
    println(gamer1)
}