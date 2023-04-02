package com.example.interviewmate.di.data

import com.example.data.remote.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService {
        return retrofit.create(SignUpApiService::class.java)
    }
}