package com.example.autoquest.data.fireBase.data_source_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.models.*
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuestDataSourceImpl(
    private val db: FirebaseFirestore,
) : QuestDataSource {

    override fun fetchQuestItemList() = flow {

        val questItems = Tasks.await(
            db.collection("questItemList").get()
        )

        val questItemList = mutableListOf<QuestItem>()

        questItems.documents.forEach {

            val questsId = it.getLong("questsId")
            val itemBackgroundImg = it.getString("itemBackgroundImg")
            val rating = it.getString("rating")
            val isFavorite = it.getBoolean("isFavorite")
            val questDescription = it.getString("questDescription")
            val dataQuest = it.getString("dataQuest")
            val nameQuest = it.getString("nameQuest")
            val timeQuest = it.getString("timeQuest")
            val placeStartQuest = it.getString("placeStartQuest")
            val imgDetailsQuest = it.getString("imgDetailsQuest")
            val accessCode = it.getString("accessCode")

            questItemList.add(
                QuestItem(
                    questsId!!.toInt(),
                    itemBackgroundImg!!,
                    rating!!.toInt(),
                    questDescription!!,
                    dataQuest!!,
                    nameQuest!!,
                    timeQuest!!,
                    placeStartQuest!!,
                    imgDetailsQuest!!,
                    accessCode!!
                )
            )
        }
        val data = QuestsItemList(questItemList = questItemList)
        emit(data)

    }.flowOn(Dispatchers.IO)

    override fun fetchQuestTaskList() = flow {

        val questTasks = Tasks.await(
            db.collection("questTaskList").get()
        )

        val questTaskList = mutableListOf<QuestTask>()

        questTasks.documents.forEach {

            val questsId = it.getLong("questsId")
            val textTask = it.getString("textTask")
            val answerTask = it.getString("answerTask")
            val latitude = it.getDouble("latitude")
            val longitude = it.getDouble("longitude")
            val questTaskImg = it.getString("questTaskImg")
            val taskId = it.getLong("taskId")

            questTaskList.add(
                QuestTask(
                    questsId = questsId!!.toInt(),
                    textTask = textTask!!,
                    answerTask = answerTask!!,
                    latitude = latitude!!,
                    longitude = longitude!!,
                    questTaskImg = questTaskImg!!,
                    taskId = taskId!!.toInt()
                )
            )
        }
        val data = QuestsTasksList(questTaskList = questTaskList)
        emit(data)

    }.flowOn(Dispatchers.IO)

    override fun addQuestToFavourites(userId: String, questId: Int, changeCallback: (result: Boolean) -> Unit) {

        db.collection("favorite")
            .whereEqualTo("questId", questId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { userFavouriteList ->
                if (userFavouriteList.size() > 0) {
                    userFavouriteList.forEach {
                        db.collection("favorite").document(it.id).delete()
                        changeCallback.invoke(false)
                    }
                } else{
                    changeCallback.invoke(true)
                    db.collection("favorite").document().set(Favourite(questId, userId))
                }
            }
    }

    override fun saveUserInFb(userId: String, userName: String, userImg: String) {
        db.collection("users")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { users ->
                if (users.size() == 0)
                    db.collection("users").document().set(User(userId, userImg, userName))
            }
    }

    override fun getUserFavouriteQuests(userId: String) = flow {
        val userFavouriteQuests = Tasks.await(
            db.collection("favorite")
                .whereEqualTo("userId", userId)
                .get()
        )
        val questsId = mutableListOf<Long>()

        userFavouriteQuests.documents.forEach {
            questsId.add(it.getLong("questId")!!)
        }


        emit(questsId)
    }.flowOn(Dispatchers.IO)

}