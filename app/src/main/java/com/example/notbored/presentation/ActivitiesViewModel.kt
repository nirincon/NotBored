package com.example.notbored.presentation

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.example.notbored.domain.repository.ActivityRepository
import com.example.notbored.utils.Resource
import kotlinx.coroutines.Dispatchers

//ViewModel of the movie List fragment.
class ActivitiesViewModel(private val repo: ActivityRepository) : ViewModel() {
    var participants = MutableLiveData<String>()
    var type = MutableLiveData<String>()
    var minprice = MutableLiveData<String>()
    var maxprice = MutableLiveData<String>()

    private lateinit var sharedPreferences: SharedPreferences

    fun setSharedPreferences (preferences: SharedPreferences){
        this.sharedPreferences = preferences
    }

    fun addParticipants(input: String){
        this.sharedPreferences.edit().putString("user", input).apply()
    }

    fun getParticipants(): String{
        participants.value = this.sharedPreferences.getString("user","")
        return participants.value.toString()
    }

    fun addType(input: String){
        this.sharedPreferences.edit().putString("type", input).apply()
    }

    fun getType(): String{
        type.value = this.sharedPreferences.getString("type","")
        return type.value.toString()
    }

    fun addMin(input: String){
        this.sharedPreferences.edit().putString("min", input).apply()
    }

    fun getMin(): String{
        minprice.value = this.sharedPreferences.getString("min","")
        return minprice.value.toString()
    }

    fun addMax(input: String){
        this.sharedPreferences.edit().putString("max", input).apply()
    }

    fun getMax(): String{
        maxprice.value = this.sharedPreferences.getString("max","")
        return maxprice.value.toString()
    }

    fun fetchActivities(participants: String, type: String, minprice: String, maxprice: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(repo.getActivity(participants, type, minprice, maxprice)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

}

class ActivitiesViewModelFactory(private val repo: ActivityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActivityRepository::class.java).newInstance(repo)
    }
}