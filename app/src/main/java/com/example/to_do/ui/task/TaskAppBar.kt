package com.example.to_do.ui.task

import android.app.Notification
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do.R
import com.example.to_do.data.models.Priority
import com.example.to_do.data.models.ToDoTask
import com.example.to_do.ui.theme.topAppBarBackgroundColor
import com.example.to_do.ui.theme.topAppBarContentColor
import com.example.to_do.util.Actions

@Composable
fun TaskAppBar(
    selectedTask:ToDoTask?,
    navigateToListScreen:(Actions)->Unit
) {
    if(selectedTask==null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }else{
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen =navigateToListScreen )
    }

}

@Composable
fun NewTaskAppBar(
    navigateToListScreen:(Actions)->Unit

) {
    TopAppBar(
        navigationIcon = {
                         BackAction(onBackClicked =navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(R.string.add_task),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked =navigateToListScreen )
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Actions) -> Unit
) {
    IconButton(onClick = {
        onBackClicked(Actions.NO_ACTION)
    }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack ,
            contentDescription =stringResource(R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContentColor

            )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Actions) -> Unit
) {
    IconButton(onClick = {
        onAddClicked(Actions.ADD)
    }) {
        Icon(
            imageVector = Icons.Filled.Check ,
            contentDescription =stringResource(R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor

        )
    }
}
@Composable
fun ExistingTaskAppBar(
    selectedTask:ToDoTask,
    navigateToListScreen:(Actions)->Unit

) {
    TopAppBar(
        navigationIcon = {
        CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
           DeleteAction(onDeleteClicked =navigateToListScreen )
            UpdateAction(onUpdateClicked =navigateToListScreen )

        }
    )
}
@Composable
fun CloseAction(
    onCloseClicked: (Actions) -> Unit
) {
    IconButton(onClick = {
       onCloseClicked(Actions.NO_ACTION)
    }) {
        Icon(
            imageVector = Icons.Filled.Close ,
            contentDescription =stringResource(R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor

        )
    }
}
@Composable
fun UpdateAction(
    onUpdateClicked: (Actions) -> Unit
) {
    IconButton(onClick = {
        onUpdateClicked(Actions.UPDATE)
    }) {
        Icon(
            imageVector = Icons.Filled.Check ,
            contentDescription =stringResource(R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContentColor

        )
    }
}
@Composable
fun DeleteAction(
    onDeleteClicked: (Actions) -> Unit
) {
    IconButton(onClick = {
        onDeleteClicked(Actions.DELETE)
    }) {
        Icon(
            imageVector = Icons.Filled.Delete ,
            contentDescription =stringResource(R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor

        )
    }
}
@Composable
@Preview
fun PreviewTaskAppBar(){
    NewTaskAppBar(navigateToListScreen = {})
}
@Composable
@Preview
fun PreviewNewTaskAppBar(){
    ExistingTaskAppBar(selectedTask = ToDoTask(
        0,"a7a","this word is used mainly for rejection",Priority.HIGH
    ) , navigateToListScreen ={} )
    }
