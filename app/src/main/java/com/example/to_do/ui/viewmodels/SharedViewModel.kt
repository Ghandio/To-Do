package com.example.to_do.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.data.models.Priority
import com.example.to_do.data.models.ToDoTask
import com.example.to_do.data.repos.ToDoRepository
import com.example.to_do.util.Constant.MAX_TITLE_LENGTH
import com.example.to_do.util.RequestState
import com.example.to_do.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel() {

    val id:MutableState<Int> = mutableStateOf(0)
    val title:MutableState<String> = mutableStateOf("")
    val description:MutableState<String> = mutableStateOf("")
    val priority:MutableState<Priority> = mutableStateOf(Priority.LOW)

     val searchAppBarState:MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
     val searchTextState:MutableState<String> =
        mutableStateOf("")


    private val _allTasks=
        MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks:StateFlow<RequestState<List<ToDoTask>>> = _allTasks

    fun getAllTasks(){
        _allTasks.value=RequestState.Loading
       try {
           viewModelScope.launch {
               repository.getAllTasks.collect {
                   _allTasks.value=RequestState.Success(it)
               }
           }
       }catch (e:Exception){
            _allTasks.value=RequestState.Error(e)
       }
    }
    private val _selectedTask:MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask:StateFlow<ToDoTask?> =_selectedTask
    fun getSelectedTaskFromDb(taskId:Int){
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect {
                task->
                _selectedTask.value=task
            }
        }
    }
    fun updateTaskFields(
        selectedTask:ToDoTask?
    ){
            if(selectedTask!=null){
                id.value=selectedTask.id
                description.value=selectedTask.description
                priority.value=selectedTask.Priority
                title.value=selectedTask.title
            }
        else{
                id.value=0
                description.value=""
                priority.value=Priority.LOW
                title.value=""
            }
    }
    fun updateTitle(newTitle:String){
        if(newTitle.length<MAX_TITLE_LENGTH){
            title.value=newTitle
        }

    }

    fun validateFields():Boolean{
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

}