package com.example.autoquest.data.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.autoquest.data.helper.APP_PREFERENCES
import com.example.autoquest.data.helper.FLAG
import com.example.autoquest.data.helper.QUEST_ID

class WorkWithSharedPref(context: Context) {

    private var shared: SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = shared.edit()

    //Flag Registered
    fun saveFlagUserIsAuthorizedInShared() {
        editor.putBoolean(FLAG, true)
        editor.apply()
    }

    fun fetchFlagUserIsAuthorizedFromShared() = shared.getBoolean(FLAG, false)

    //Quest Id
    fun saveQuestIdInShared(questId: Int) {
        editor.putString(QUEST_ID, questId.toString())
        editor.apply()
    }

    fun fetchQuestIdFromShared() = shared.getString(QUEST_ID, "")

}