package com.example.data.repository

import com.example.data.remote.mapper.SignUpMapper
import com.example.data.remote.source.signup.SignUpRemoteDataSource
import com.example.domain.model.*
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SignUpRepositoryImpl @Inject constructor(
    private val signUpRemoteDataSource: SignUpRemoteDataSource
) : SignUpRepository {

    override suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Flow<SignUpResponse> = flow {
        signUpRemoteDataSource.setSignUp(SignUpMapper.mapperToSignUpUserInfo(signUpUserInfo))
            .onSuccess { signUpResponseRepositoryModel ->
                emit(signUpResponseRepositoryModel.toDomainModel())
            }
            .onFailure {
                throw it
            }
    }

    override suspend fun sendEmail(email: String): Flow<EmailResponse> = flow {
        signUpRemoteDataSource.sendEmail(email)
            .onSuccess { emailResponseRepositoryModel ->
                emit(emailResponseRepositoryModel.toDomainModel())
            }
            .onFailure {
                throw it
            }
    }

    override suspend fun authenticateCode(email: String, code: String): Flow<EmailResponse> = flow {
        signUpRemoteDataSource.authenticateCode(email, code)
            .onSuccess { emailResponseRepositoryModel ->
                emit(emailResponseRepositoryModel.toDomainModel())
            }
            .onFailure {
                throw it
            }
    }

    override suspend fun setLogin(loginUserInfo: LoginUserInfo): Flow<LoginResponse> = flow {
        signUpRemoteDataSource.setLogin(SignUpMapper.mapperToLoginUserInfo(loginUserInfo))
            .onSuccess { loginResponseRepositoryModel ->
                emit(loginResponseRepositoryModel.toDomainModel())
            }
            .onFailure {
                throw it
            }
    }

}