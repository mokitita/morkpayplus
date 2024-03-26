package com.morkurensky.payplus.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.morkurensky.payplus.NetworkService
import com.morkurensky.payplus.data.Item
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsViewModel(private val navController:
    NavController, application: Application
) : AndroidViewModel(application) {

    private val _items = MutableLiveData<List<Item>>()

    val items: LiveData<List<Item>> = _items

    init {
        viewModelScope.launch {
            fetchItems()
        }
    }
    suspend fun fetchItems() {
        val itemsCall: Call<List<Item>> = NetworkService().allItems()
        itemsCall.enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    val allItems =   response.body()
                    allItems?.let {
                        _items.value = it
                    }
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
}

    class ItemsViewModelFactory(
        private val navController: NavController,
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ItemsViewModel( navController, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}