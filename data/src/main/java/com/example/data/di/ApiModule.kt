package com.example.data.di

import com.example.data.remote.network.InterviewApiService
import com.example.data.remote.network.MyPageApiService
import com.example.data.remote.network.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService {
        return retrofit.create(SignUpApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideInterviewApiService(retrofit: Retrofit): InterviewApiService {
        return retrofit.create(InterviewApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyPageApiService(retrofit: Retrofit): MyPageApiService {
        return retrofit.create(MyPageApiService::class.java)
    }
}