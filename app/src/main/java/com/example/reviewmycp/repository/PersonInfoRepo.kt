package com.example.reviewmycp.repository

class PersonInfoRepo:BaseRepository(){






    companion object {

        @Volatile
        private var INSTANCE: PersonInfoRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PersonInfoRepo().also { INSTANCE = it }
            }
    }

}