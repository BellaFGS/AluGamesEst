package br.com.estudos.alugamesest.modelo

import org.example.br.com.estudos.alugamesest.modelo.Jogo
import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome:String, var email:String) {
    var dataNascimento:String? = null
    var usuario:String? = null
        set(value){
            field = value
        }

    var idInterno:String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo?>()

    constructor(nome:String, email: String, dataNascimento:String, usuario:String): this(nome, email){
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }


    init {
        if (nome.isNullOrBlank()){
            throw IllegalArgumentException("Argumento em branco detectado")
        }
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno () {
        val numero = Random.nextInt(1000)
        val tag = String.format("%04d", numero)

        this.idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        } else{
            throw IllegalArgumentException("Email inválido")
        }
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer{
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)){
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            } else{
                return Gamer(nome,email)
            }

        }
    }

}
