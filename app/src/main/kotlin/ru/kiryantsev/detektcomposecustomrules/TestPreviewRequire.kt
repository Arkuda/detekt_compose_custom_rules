package ru.kiryantsev.detektcomposecustomrules

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

//composables for test preview rule


@Composable
fun TestComposable() {
    Text("Test")
    SomeTestComposable()

}

@Composable
fun SomeTestComposable(){
    Text("SOMEETST")
}