package com.luanasilva.aularetrofitapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luanasilva.aularetrofitapi.api.EnderecoAPI
import com.luanasilva.aularetrofitapi.api.RetrofitHelper
import com.luanasilva.aularetrofitapi.databinding.ActivityMainBinding
import com.luanasilva.aularetrofitapi.model.Endereco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
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
            Log.i("info_endereco", "botão clicado")
            CoroutineScope(Dispatchers.IO).launch {

                recuperarEndereco()

            }
        }

    }

    private suspend fun recuperarEndereco() {

        var retorno:Response<Endereco>? =null
        val cepDigitadoUsuario = "94130270"//binding.editViewUsuario

        try {
            val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
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



    //TODA função suspend só pode ser usado dentro de uma Coroutine
    private suspend fun recuperarUsuarioLogado() :Usuario{
        delay(2000)
        return Usuario(1011, "Luana Silva")
    }

}