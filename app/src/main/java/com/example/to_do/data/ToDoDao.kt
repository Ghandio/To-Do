package com.example.to_do.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.to_do.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("Select * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>
    @Query("SELECT * FROM todo_table Where id=:taskId")
    fun getSelectedTask(taskId:Int):Flow<ToDoTask>
    @Insert(onConflict = IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)
    @Update
    suspend fun updateTask(toDoTask: ToDoTask)
    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)
    @Query("Delete From todo_table")
    suspend fun deleteAllTasks()
    @Query("SELECT * FROM todo_table WHERE title like :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery:String):Flow<List<ToDoTask>>
    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 When priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority():Flow<List<ToDoTask>>
    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 When priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority():Flow<List<ToDoTask>>
}