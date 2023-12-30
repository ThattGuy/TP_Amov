package pt.isec.amov.tp.eguide.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import pt.isec.amov.tp.eguide.data.Location
import pt.isec.amov.tp.eguide.ui.viewmodels.LocationViewModel

@Composable
fun RegisterPointOfInterest( navController: NavController,viewModel: LocationViewModel){
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }
    var coordinates by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        TextField(value = name, onValueChange = {name = it},label = { Text(text = "Nome")})
        TextField(value = description, onValueChange = {description = it},label = { Text(text = "Descrição")})
        TextField(value = coordinates, onValueChange = {coordinates = it},label = { Text(text = "Coordenadas")})
        Button(onClick = { coordinates =  viewModel.extrairString(viewModel.currentLocation.value.toString())!!}) {
            Text(text = "Minhas coordenadas")
        }
    
        Button(onClick = {
            var location = viewModel.locationSelected
            viewModel.insertPointOfInterest(name,description,coordinates)
        }){
            Text(text = "Salvar")
            
        }

    }
}