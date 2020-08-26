package com.example.WebAPITest

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.*
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    private var setContent: String = "test1"
    var content: MyJsonData = MyJsonData("NoahFox")
    private val gson = Gson()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content.UsersIdString = "testy string"
        findViewById<Button>(R.id.set_button).setOnClickListener { this.setNickname(it) }

        findViewById<Button>(R.id.done_button).setOnClickListener { this.addNickname(it) }


    }

    private fun setNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)


        setContent = editText.text.toString()
        nicknameTextView.text = setContent
//        editText.visibility = View.GONE
//        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
//        set_button.visibility = View.GONE

        //Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        GlobalScope.launch(Dispatchers.Main) {
            webCallSet()
        }
        GlobalScope.launch(Dispatchers.Main) {
            webCall()
        }

    }

    private fun addNickname(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        GlobalScope.launch(Dispatchers.Main) {
            webCall()
        }

        nicknameTextView.text = content.userFirstName


//        nicknameTextView.text = editText.text
//        editText.visibility = View.GONE
//        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
        //Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

//        GlobalScope.async(Dispatchers.IO){processIt()}


    }

    private suspend fun webCallSet(): Unit = withContext(Dispatchers.IO) {
        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000


                /**
                 * Proxy address to use.
                 */

                /**
                 * Proxy address to use.
                 */
//            proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.0.2.2", 8080))
            }
        }

        println("start 1")
        GlobalScope.async(Dispatchers.IO) {

            // make network call
            val name = setContent
            content.userFirstName = name
            val userPayload = gson.toJson(content)
            println(userPayload)
            client.post<String>(urlString = "http://10.0.2.2:80/demo/user"){
                body = userPayload
            }

//            client.post<String>(urlString = "http://10.0.2.2:80/demo/harry?name=$name")

//            client.get<String>(urlString = "http://10.0.2.2:80/demo/harry?name=$name")
//            client.post<StringBuilder>(urlString = "http://10.0.2.2:80/demo/harry"){
//                body = name
//            }

            println("web accessed 1")

        }.await()


    }

    private suspend fun webCall(): Unit = withContext(Dispatchers.IO) {
        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
//                acceptContentTypes += ContentType("application", "json+hal")
            }
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000

                /**
                 * Proxy address to use.
                 */

                /**
                 * Proxy address to use.
                 */
//            proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.0.2.2", 8080))
            }
        }

        println("start 2")
        GlobalScope.async(Dispatchers.IO) {
            // make network call
            val jsonContent = client.get<String>(urlString = "http://10.0.2.2:80/demo")
            println(jsonContent)
//            var jsonObject : JsonObject = gson.fromJson(jsonContent, JsonObject.class)
            content = gson.fromJson(jsonContent, MyJsonData::class.java)
            println("web accessed 2")
        }.await()


    }

}

//private operator fun String.invoke(urlString: String): String {
//
//}
