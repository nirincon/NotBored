package com.example.notbored.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.notbored.domain.repository.ActivityRepository
import com.example.notbored.utils.Resource
import kotlinx.coroutines.Dispatchers

//ViewModel of the movie List fragment.
class ActivitiesViewModel(private val repo: ActivityRepository): ViewModel() {
    fun fetchActivities(participants: String, type: String) = liveData(viewModelScope.coroutineContext + Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getActivity(participants,type)))

        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class ActivitiesViewModelFactory(private val repo: ActivityRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActivityRepository::class.java).newInstance(repo)
    }
}