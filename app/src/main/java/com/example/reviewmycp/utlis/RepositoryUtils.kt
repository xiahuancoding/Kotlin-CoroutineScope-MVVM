package com.example.reviewmycp.utlis

import com.example.reviewmycp.repository.LoginRepo
import com.example.reviewmycp.repository.SplashRepo


object  RepositoryUtils {

    fun getSplashRepository() = SplashRepo.getInstance()

    fun getLoginRepository() = LoginRepo.getInstance()


}