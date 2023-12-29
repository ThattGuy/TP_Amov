package pt.isec.amov.tp.eguide.utils.firebase

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import pt.isec.amov.tp.eguide.data.Location

class FStorageUtil {
    companion object {
        fun addDataToFirestore(onResult: (Throwable?) -> Unit) {
            val db = Firebase.firestore

            val scores = hashMapOf(
                "nrgames" to 0,
                "topscore" to 0
            )
            db.collection("Scores").document("Level1").set(scores)
                .addOnCompleteListener { result ->
                    onResult(result.exception)
                }
        }

        fun updateDataInFirestore(onResult: (Throwable?) -> Unit) {
            val db = Firebase.firestore
            val v = db.collection("Scores").document("Level1")

            v.get(Source.SERVER)
                .addOnSuccessListener {
                    val exists = it.exists()
                    Log.i("Firestore", "updateDataInFirestore: Success? $exists")
                    if (!exists) {
                        onResult(Exception("Doesn't exist"))
                        return@addOnSuccessListener
                    }
                    val value = it.getLong("nrgames") ?: 0
                    v.update("nrgames", value + 1)
                    onResult(null)
                }
                .addOnFailureListener { e ->
                    onResult(e)
                }
        }

         fun insertCategoryIntoDB(categoryName : String, categoryDescription : String){
            val db = com.google.firebase.Firebase.firestore
            val nameData = hashMapOf("Name" to categoryName, "Description" to categoryDescription)


            db.collection("Categories").document(categoryName).set(nameData)
                .addOnSuccessListener {
                    Log.i(ContentValues.TAG, "addDataToFirestore: Success")
                }
                .addOnFailureListener { e->
                    Log.i(ContentValues.TAG, "addDataToFirestore: ${e.message}")
                }

        }

        fun insertLocationIntoDB(name: String,description: String, locationCoordinates : String)
        {
            val db = Firebase.firestore
            val data = hashMapOf("Description" to description,
                                "Coordinates" to locationCoordinates)

            db.collection("Locations").document(name).set(data)
                .addOnSuccessListener {
                    Log.i(ContentValues.TAG, "addDataToFirestore: Success")
                }
                .addOnFailureListener { e->
                    Log.i(ContentValues.TAG, "addDataToFirestore: ${e.message}")
                }
            db.collection("Locations").document(name).collection("PointsOfInterest").document("ReferencePoint").set(data)
                .addOnSuccessListener {
                    Log.i(ContentValues.TAG, "addDataToFirestore: Success")
                }
                .addOnFailureListener { e->
                    Log.i(ContentValues.TAG, "addDataToFirestore: ${e.message}")
                }
        }

        /*fun provideLocations() : ArrayList<Location>{


            val db = Firebase.firestore
            val lista = ArrayList<Location>()
           val location = db.collection("Locations").get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val location = Location(document.id,document.data["Description"].toString(),document.data["Coordinates"].toString())
                        lista.add(location)
                    }
                    return@addOnSuccessListener lista

                }
                .addOnFailureListener { exception ->

                Log.d(ContentValues.TAG, "\n\n\nError getting documents: ", exception)

                }
//retiurn lista
            return lista

        }

         */


         suspend fun provideLocations(): ArrayList<Location> {
            return try {
                val db = Firebase.firestore
                val lista = ArrayList<Location>()

                val querySnapshot = db.collection("Locations").get().await()
                for (document in querySnapshot.documents) {
                    val location = Location(
                        document.id,
                        document.data?.get("Description").toString(),
                        document.data?.get("Coordinates").toString()
                        //document.data["Coordinates"].toString()
                    )
                    lista.add(location)
                }

                lista
            } catch (e: Exception) {
                Log.d(ContentValues.TAG, "Error getting documents: ", e)
                ArrayList()
            }
        }





        fun fetchLocations(callback: (ArrayList<Location>) -> Unit) {
            val db = Firebase.firestore

            db.collection("Locations").get()
                .addOnSuccessListener { result ->
                    val lista = ArrayList<Location>()

                    for (document in result) {
                        val location = Location(
                            document.id,
                            document.data["Description"].toString(),
                            document.data["Coordinates"].toString()
                        )
                        lista.add(location)
                    }

                    callback(lista) // Chamando o callback com a lista de locais
                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "Error getting documents: ", exception)
                    callback(ArrayList()) // Em caso de falha, chamando o callback com uma lista vazia
                }
        }

        fun insertPointOfInterest(
            name: String,
            description: String,
            coordinates: String,
            locationSelected: Location?
        ) {
            val db = Firebase.firestore
            val data = hashMapOf(
                "Description" to description,
                "Coordinates" to coordinates,
                "Location" to locationSelected?.name
            )

            /*
            db.collection("PointsOfInterest").document(name).set(data)
                .addOnSuccessListener {
                    Log.i(ContentValues.TAG, "addDataToFirestore: Success")
                }
                .addOnFailureListener { e ->
                    Log.i(ContentValues.TAG, "addDataToFirestore: ${e.message}")
                }

             */
            db.collection("Locations").document(locationSelected?.name.toString()).collection("PointsOfInterest").document(name).set(data)
                .addOnSuccessListener {
                    Log.i(ContentValues.TAG, "addDataToFirestore: Success")
                }
                .addOnFailureListener { e ->
                    Log.i(ContentValues.TAG, "addDataToFirestore: ${e.message}")
                }
        }


        /*
        fun updateDataInFirestoreTrans(onResult: (Throwable?) -> Unit) {
            val db = Firebase.firestore
            val v = db.collection("Scores").document("Level1")

            db.runTransaction { transaction ->
                val doc = transaction.get(v)
                if (doc.exists()) {
                    val newnrgames = (doc.getLong("nrgames") ?: 0) + 1
                    val newtopscore = (doc.getLong("topscore") ?: 0) + 100
                    transaction.update(v, "nrgames", newnrgames)
                    transaction.update(v, "topscore", newtopscore)
                    null
                } else
                    throw FirebaseFirestoreException(
                        "Doesn't exist",
                        FirebaseFirestoreException.Code.UNAVAILABLE
                    )
            }.addOnCompleteListener { result ->
                onResult(result.exception)
            }
        }

        fun removeDataFromFirestore(onResult: (Throwable?) -> Unit) {
            val db = Firebase.firestore
            val v = db.collection("Scores").document("Level1")

            v.delete()
                .addOnCompleteListener { onResult(it.exception) }
        }

        private var listenerRegistration: ListenerRegistration? = null

        fun startObserver(onNewValues: (Long, Long) -> Unit) {
            stopObserver()
            val db = Firebase.firestore
            listenerRegistration = db.collection("Scores").document("Level1")
                .addSnapshotListener { docSS, e ->
                    if (e != null) {
                        return@addSnapshotListener
                    }
                    if (docSS != null && docSS.exists()) {
                        val nrgames = docSS.getLong("nrgames") ?: 0
                        val topscore = docSS.getLong("topscore") ?: 0
                        Log.i("Firestore", "$nrgames : $topscore")
                        onNewValues(nrgames, topscore)
                    }
                }
        }

        fun stopObserver() {
            listenerRegistration?.remove()
        }

// Storage

        fun getFileFromAsset(assetManager: AssetManager, strName: String): InputStream? {
            var istr: InputStream? = null
            try {
                istr = assetManager.open(strName)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return istr
        }

//https://firebase.google.com/docs/storage/android/upload-files

        fun uploadFile(inputStream: InputStream, imgFile: String) {
            val storage = Firebase.storage
            val ref1 = storage.reference
            val ref2 = ref1.child("images")
            val ref3 = ref2.child(imgFile)

            val uploadTask = ref3.putStream(inputStream)
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                ref3.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    println(downloadUri.toString())
                    // something like:
                    //   https://firebasestorage.googleapis.com/v0/b/p0405ansamov.appspot.com/o/images%2Fimage.png?alt=media&token=302c7119-c3a9-426d-b7b4-6ab5ac25fed9
                } else {
                    // Handle failures
                    // ...
                }
            }


        }*/

    }
}