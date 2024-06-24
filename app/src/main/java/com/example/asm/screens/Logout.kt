package com.example.asm.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.asm.models.UserInfo

class Logout {
    @Composable
    fun Container(saveUserInfo: (UserInfo) -> Unit){
        Column {
            Text(text = "Log out")
            Button(onClick = { /*TODO*/
                saveUserInfo(UserInfo(null, null, null, null))
            }) {
                Text(text = "LOG OUT")
            }
        }
    }

}