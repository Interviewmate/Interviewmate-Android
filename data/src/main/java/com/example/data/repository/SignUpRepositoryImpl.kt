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

    override suspend fun setSignUp(signUpUserInfo: SignUpUserInfo): Flow<ResponseUseCaseModel<UserInfo>> =
        flow {
            signUpRemoteDataSource.setSignUp(SignUpMapper.mapperToSignUpUserInfo(signUpUserInfo))
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun sendEmail(email: String): Flow<ResponseUseCaseModel<String>> = flow {
        signUpRemoteDataSource.sendEmail(email)
            .onSuccess { responseRepositoryModel ->
                emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result))
            }
            .onFailure {
                throw it
            }
    }

    override suspend fun authenticateCode(
        email: String,
        code: String
    ): Flow<ResponseUseCaseModel<String>> = flow {
        signUpRemoteDataSource.authenticateCode(email, code)
            .onSuccess { responseRepositoryModel ->
                emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result))
            }
            .onFailure {
                throw it
            }
    }

    override suspend fun setLogin(loginUserInfo: LoginUserInfo): Flow<ResponseUseCaseModel<UserAuth>> =
        flow {
            signUpRemoteDataSource.setLogin(SignUpMapper.mapperToLoginUserInfo(loginUserInfo))
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }
        }

}