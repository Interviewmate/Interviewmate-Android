package com.example.data.di

import com.example.data.remote.source.SignUpRemoteDataSource
import com.example.data.remote.source.SignUpRemoteDataSourceImpl
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
}