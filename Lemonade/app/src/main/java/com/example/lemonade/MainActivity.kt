package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme() {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    var curStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Column(

    ) {
        Surface(
            color = Color(0xFFFFFF99),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp),
                fontWeight = FontWeight.Bold
            )

        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFD2E8D4)
        ) {
            when (curStep) {
                1 -> {
                    LemonTextAndImage(
                        textLabel = R.string.lemon_tree_text,
                        image = R.drawable.lemon_tree,
                        contentDescription = R.string.lemon_tree_content_description,
                        onImageClick = {
                            curStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    LemonTextAndImage(
                        textLabel = R.string.lemon_squeeze_text,
                        image = R.drawable.lemon_squeeze,
                        contentDescription = R.string.lemon_content_description,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                curStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonTextAndImage(
                        textLabel = R.string.lemon_drink_text,
                        image = R.drawable.lemon_drink,
                        contentDescription = R.string.glass_of_lemonade_content_description,
                        onImageClick = {
                            curStep = 4
                        }
                    )
                }

                4 -> {
                    LemonTextAndImage(
                        textLabel = R.string.lemon_restart_text,
                        image = R.drawable.lemon_restart,
                        contentDescription = R.string.empty_glass_content_description,
                        onImageClick = {
                            curStep = 1
                        }
                    )
                }
            }
        }
    }


}


@Composable
fun LemonTextAndImage(
    textLabel: Int,
    image: Int,
    contentDescription: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                colors = ButtonDefaults.buttonColors(Color(0xFFFFE5B4)),
                shape = RoundedCornerShape(200.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(contentDescription),
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            Text(
                text = stringResource(textLabel),
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonadeApp()
    }
}