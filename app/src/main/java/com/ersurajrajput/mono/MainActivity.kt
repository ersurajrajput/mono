package com.ersurajrajput.mono

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ersurajrajput.mono.api.TweetsyAPI
import com.ersurajrajput.mono.data.model.TweetModel
import com.ersurajrajput.mono.ui.theme.MONOTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tweetsyAPI: TweetsyAPI
    lateinit var tweetList: List<TweetModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        GlobalScope.launch {
            Thread.sleep(5000)
            tweetList = tweetsyAPI.getTweets().body()!!
            showList(tweetList)
        }
        setContent {
            MONOTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(

                        name = "hello",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MONOTheme {


        Greeting("Android")
    }
}
fun showList(tweetList: List<TweetModel>){
    for (tweet in tweetList){
        Log.d("MyTag","cat: ${tweet.category}\nText: ${tweet.text}")

    }
}