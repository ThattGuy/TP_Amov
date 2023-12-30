package pt.isec.amov.tp.eguide.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import pt.isec.amov.tp.eguide.ui.viewmodels.LocationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLocationScreen(viewModel: LocationViewModel) {


    var description by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("")}
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = name, onValueChange = {name = it}, placeholder = { Text( stringResource(id = pt.isec.amov.tp.eguide.R.string.name))})
        TextField(value = description, onValueChange = {description = it}, placeholder = { Text(text = "Descrição")})

        Button(onClick = {/*TODO*/}){
            Text(stringResource(id = pt.isec.amov.tp.eguide.R.string.select_image))
        }

        Button(onClick = {
            print("\n\n\n Vim aquiii")
            location = viewModel._currentLocation.value.toString()
            viewModel.insertLocationIntoDB(name,description)


        }){
            Text(stringResource(id = pt.isec.amov.tp.eguide.R.string.save))
        }



    }




}