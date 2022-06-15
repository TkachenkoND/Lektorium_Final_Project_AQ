package com.example.autoquest.data.fireBase.data_source_impl

import android.util.Log
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
                    isFavorite!!,
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
            val accessCode = it.getString("accessCode")
            val latitude = it.getDouble("latitude")
            val longitude = it.getDouble("longitude")
            val questTaskImg = it.getString("questTaskImg")

            questTaskList.add(
                QuestTask(
                    questsId = questsId!!.toInt(),
                    textTask = textTask!!,
                    answerTask = answerTask!!,
                    latitude = latitude!!,
                    longitude = longitude!!,
                    questTaskImg = questTaskImg!!
                )
            )
        }
        val data = QuestsTasksList(questTaskList = questTaskList)
        emit(data)

    }.flowOn(Dispatchers.IO)

    override fun addQuestToFavourites(userId: Int, questId: Int) {
        db.collection("favourites").document().set(Favourite(questId, userId))
    }

    override fun saveUserInFb(userId: Int, userName: String, userImg: String) {
        db.collection("users").document().set(User(userId, userImg, userName))
    }

    override fun getUserFavouriteQuests(userId: Int) = flow {
        val userFavouriteQuests = Tasks.await(
            db.collection("favourites")
                .whereEqualTo("userId", userId)
                .get()
        )
        val questsId = mutableListOf<String>()

        userFavouriteQuests.documents.forEach {
            questsId.add(it.getString("userId")!!)
        }


        emit(questsId)
    }.flowOn(Dispatchers.IO)

    override suspend fun removeQuestFromFavourites(questId: String, userId: String) {
        val userFavouriteQuest = Tasks.await(
            db.collection("favourites")
                .whereEqualTo("questId", questId)
                .whereEqualTo("userId", userId)
                .get()
        )

        if (userFavouriteQuest != null) {
            for (fav in userFavouriteQuest) {
                db.collection("favourites").document(fav.id).delete()
                    .addOnSuccessListener {
                        Log.e("Fav deleted", " successful")
                    }
                    .addOnFailureListener {
                        Log.e("Fav deleted", " $it")
                    }
            }
        }
    }

}