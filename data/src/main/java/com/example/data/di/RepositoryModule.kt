package com.example.data.di

import com.example.data.repository.InterviewRepositoryImpl
import com.example.data.repository.MyPageRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.repository.InterviewRepository
import com.example.domain.repository.MyPageRepository
import com.example.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun provideSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository

    @Binds
    @Singleton
    fun provideInterviewRepository(
        interviewRepositoryImpl: InterviewRepositoryImpl
    ): InterviewRepository

    @Binds
    @Singleton
    fun provideMyPageRepository(
        myPageRepositoryImpl: MyPageRepositoryImpl
    ): MyPageRepository
}