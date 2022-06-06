package com.example.autoquest.data.fireBase.data_source_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
                    imgDetailsQuest!!
                )
            )
        }
            val data = QuestsItemList(questItemList = questItemList)
            emit(data)

    }.flowOn(Dispatchers.IO)

    override fun fetchQuestTaskList(): Flow<QuestsTasksList> {
        TODO("Not yet implemented")
    }

    override fun fetchFavoriteQuestItem()= flow {

        val questItems = Tasks.await(
            db.collection("questItemList").whereEqualTo("isFavorite",true).get()
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
                    imgDetailsQuest!!
                )
            )
        }
        val data = QuestsItemList(questItemList = questItemList)
        emit(data)

    }.flowOn(Dispatchers.IO)

    override fun updateQuestIsFavorite(isFavorite: Boolean) {
        val routeDocRef = db.collection("questItemList").document("questItem0")

        db.runBatch { batch ->
            batch.update(routeDocRef, "isFavorite", isFavorite)
        }
    }

}