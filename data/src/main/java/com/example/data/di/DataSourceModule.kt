package com.example.data.di

import com.example.data.remote.source.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    @Singleton
    fun provideSignUpRemoteDataSource(
        signUpRemoteDataSourceImpl: SignUpRemoteDataSourceImpl
    ): SignUpRemoteDataSource

    @Binds
    @Singleton
    fun provideInterviewRemoteDataSource(
        interviewRemoteDataSourceImpl: InterviewRemoteDataSourceImpl
    ): InterviewRemoteDataSource

    @Binds
    @Singleton
    fun provideMyPageRemoteDataSource(
        myPageRemoteDataSourceImpl: MyPageRemoteDataSourceImpl
    ): MyPageRemoteDataSource

    @Binds
    @Singleton
    fun provideAnalysisRemoteDataSource(
        analysisRemoteDataSourceImpl: AnalysisRemoteDataSourceImpl
    ): AnalysisRemoteDataSource
}