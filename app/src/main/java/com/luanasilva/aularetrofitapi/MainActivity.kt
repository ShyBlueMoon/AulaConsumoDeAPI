package com.luanasilva.aularetrofitapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luanasilva.aularetrofitapi.api.PostagemAPI
import com.luanasilva.aularetrofitapi.api.RetrofitHelper
import com.luanasilva.aularetrofitapi.databinding.ActivityMainBinding
import com.luanasilva.aularetrofitapi.model.Comentario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val INFO_JSON = "info_json"
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofitViaCep by lazy {
        RetrofitHelper.apiViaCep
    }
    private val retrofitJsonPlaceHolder by lazy {
        RetrofitHelper.apiJsonPlaceHolder
    }


    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAbrirTela.setOnClickListener {
            finish()
        }

        binding.btnParar.setOnClickListener {
            job?.cancel()
            binding.btnIniciar.text = "Reiniciar execução"
            binding.btnIniciar.isEnabled = true
        }


        binding.btnIniciar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                //recuperarEndereco()
                Log.i(INFO_JSON, "botão corotina iniciada")
                //recuperarPostagens()
                //recuperarPostagemUnica()
                recuperarComentariosParaPostagens()

            }
        }

    }



    private suspend fun recuperarComentariosParaPostagens() {

        var retorno:Response<List<Comentario>>? =null

        try {
            val postagemAPI = retrofitJsonPlaceHolder.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarComentariosParaPostagem(1)
            Log.i("info_jason", "Postagens recuperadas")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_json", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val listaComentarios = retorno.body()
                var exibirTexto = ""

                listaComentarios?.forEach { comentario ->
                    val id = comentario.id
                    val email = comentario.email
                    val texto = " $id - $email \n"
                    exibirTexto += texto

                }

                withContext(Dispatchers.Main){
                    binding.textView.text = exibirTexto
                }

            }
        }
    }




    /*private suspend fun recuperarPostagemUnica() {

        var retorno:Response<Postagem>? =null

        try {
            val postagemAPI = retrofitJsonPlaceHolder.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagemUnica(1)
            Log.i("info_jason", "Postagens recuperadas")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i(INFO_JSON, "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val postagem = retorno.body()
                val mostrarTexto = "o ID é ${postagem?.id} e título é ${postagem?.title}"


                withContext(Dispatchers.Main) {
                    binding.textView.text = mostrarTexto
                }



            }
        }
    }*/


    /*private suspend fun recuperarPostagens() {

        var retorno:Response<List<Postagem>>? =null

        try {
            val postagemAPI = retrofitJsonPlaceHolder.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagens()
            Log.i("info_jason", "Postagens recuperadas")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_json", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val listaPostagens = retorno.body()
                listaPostagens?.forEach { postagem ->
                    val id = postagem.id
                    val title = postagem.title
                    Log.i("info_json", "o ID é $id e título é $title")
                }

            }
        }
    }

    private suspend fun recuperarEndereco() {

        var retorno:Response<Endereco>? =null
        val cepDigitadoUsuario = "94130270"//binding.editViewUsuario

        try {
            val enderecoAPI = retrofitViaCep.create(EnderecoAPI::class.java)
            retorno = enderecoAPI.recuperarEndereco(cepDigitadoUsuario)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_endereco", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val endereco = retorno.body()
                val rua = endereco?.logradouro
                val cidade = endereco?.localidade
                Log.i("info_endereco", "endereco: $rua e cidade $cidade")
            }
        }
    }
*/


    //TODA função suspend só pode ser usado dentro de uma Coroutine
    private suspend fun recuperarUsuarioLogado() :Usuario{
        delay(2000)
        return Usuario(1011, "Luana Silva")
    }

}