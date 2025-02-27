package abrayudhistira.cobafinal.repository

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.service.PropertyService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

interface PropertiRepository {
    suspend fun getProperty() : List<Properti>
    suspend fun getbyidProperti(id_properti : String): Properti
    suspend fun insertProperti(properti: Properti)
    suspend fun editProperti(id_properti: String,properti: Properti)
    suspend fun deleteProperti(id_properti: String)
}
class NetworkPropertiRepository(
    private val propertyService: PropertyService,
) : PropertiRepository {

    override suspend fun getProperty(): List<Properti> = propertyService.getProperti()

    override suspend fun getbyidProperti(id_properti: String): Properti {
        try {
            // Gunakan await() untuk menunggu hasil dari Call
            val response = propertyService.getbyidProperti(id_properti)
            if (response != null) {
                return response // Kembalikan objek Properti langsung
            } else {
                throw IOException("Data properti tidak ditemukan")
            }
        } catch (e: Exception) {
            throw IOException("Gagal mengambil data: ${e.message}")
        }
    }

    override suspend fun insertProperti(properti: Properti) {
        // Log data yang akan dikirim ke API
        Log.d("NetworkPropertiRepository", "Data yang dikirim ke API: $properti")


        val response = propertyService.insertProperti(properti)
        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            Log.e("NetworkPropertiRepository", "Gagal menyimpan data: ${response.code()} - $errorBody")
            throw IOException("Gagal menyimpan data: ${response.code()} - $errorBody")
        }
    }


    override suspend fun editProperti(id_properti: String, properti: Properti) {
        propertyService.editProperti(id_properti,properti)
    }

    override suspend fun deleteProperti(id_properti: String) {
        try {
            val response = propertyService.deleteProperti(id_properti)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete properti. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}