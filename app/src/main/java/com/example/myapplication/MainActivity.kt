package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.LightSlateGray
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    color = Color.Yellow
                ) {
                    Id_sliding_tab()
                }
            }
        }
    }
}



@Composable
fun MyButton(modifier: Modifier = Modifier){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //填充按钮是高强调按钮。填充按钮在浮动操作按钮之后具有最大的视觉效果，
            // 应该用于完成流的重要最终操作，如“保存”、“立即加入”或“确认”。
            Button(
                onClick = { },
            ) {
                Text(stringResource(R.string.Button))
            }

            //提升的按钮是高强调按钮，本质上是带有阴影的填充色调按钮。
            // 为防止阴影蔓延，请仅在绝对必要时使用它们，例如当按钮需要与图案容器进行视觉分离时。
            ElevatedButton(
                onClick = { }) {
                Text(stringResource(R.string.ElevatedButton))
            }

            //填充色调按钮是中等强调的按钮，是默认按钮（填充）和轮廓按钮之间的替代中间地带。
            // 它们可用于优先级较低的按钮需要比大纲稍微多一点的强调的上下文，例如载入流中的“下一步”。色调按钮使用辅助颜色映射。
            FilledTonalButton(
                onClick = { }
            ){
                Text(stringResource(R.string.FilledTonalButton))
            }

            //带轮廓的按钮是中等强度的按钮。它们包含重要的操作，但不是应用中的主要操作。
            // 带轮廓的按钮与按钮配对良好，以指示替代的辅助操作。
            OutlinedButton(
                onClick = { }
            ){
                Text(stringResource(R.string.OutlinedButton))
            }

            //文本按钮通常用于不太明显的操作，包括位于对话框和卡片中的操作。
            // 在卡片中，文本按钮有助于保持对卡片内容的强调。文本按钮用于最低优先级的操作，尤其是在显示多个选项时。
            TextButton(
                onClick = { }
            ){
                Text(stringResource(R.string.TextButton))
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                tint = Color.Yellow
            )

        }


}

@Preview(showBackground = false)
@Composable
fun MyButtonPreview(){
//    MyApplicationTheme {
//        MyButton()
//    }
    MyButton()
}