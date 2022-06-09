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

    /**
     * Shared preferences setting
     */
    fun setSharedPreferences(preferences: SharedPreferences) {
        this.sharedPreferences = preferences
    }

    /**
     * Add participants to the shared preferences
     */
    fun addParticipants(input: String) {
        if (input == ""){
            this.sharedPreferences.edit().putString("user","").apply()
        } else{
            this.sharedPreferences.edit().putString("user", input).apply()
        }
    }

    /**
     * Get max price from the shared preferences
     */
    fun getParticipants(): String {
        participants.value = this.sharedPreferences.getString("user", "")
        return participants.value.toString()
    }
    /**
     * Add types to the shared preferences
     */
    fun addType(input: String) {
        if (input == ""){
            this.sharedPreferences.edit().putString("type", "").apply()
        }else {
            this.sharedPreferences.edit().putString("type", input).apply()
        }
    }

    /**
     * Get max price from the shared preferences
     */
    fun getType(): String {
        type.value = this.sharedPreferences.getString("type", "")
        return type.value.toString()
    }

    /**
     * Add min price to the shared preferences
     */
    fun addMin(input: String) {
        this.sharedPreferences.edit().putString("min", input).apply()
    }

    /**
     * Get max price from the shared preferences
     */
    fun getMin(): String {
        minprice.value = this.sharedPreferences.getString("min", "")
        return minprice.value.toString()
    }

    /**
     * Add max price to the shared preferences
     */
    fun addMax(input: String) {
        this.sharedPreferences.edit().putString("max", input).apply()
    }

    /**
     * Get max price from the shared preferences
     */
    fun getMax(): String {
        maxprice.value = this.sharedPreferences.getString("max", "")
        return maxprice.value.toString()
    }


    /**
     * Fetch the activities trough the repository, and emit 3 possibles phases Loading, when is calling
     * Success, if the response is successful, and Failure, if the response return an error.
     */
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

/**
 * ViewModelFactory, is used for the repository implementation in the constructor.
 */
class ActivitiesViewModelFactory(private val repo: ActivityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActivityRepository::class.java).newInstance(repo)
    }
}