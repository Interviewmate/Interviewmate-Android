package com.example.domain.di

import com.example.domain.usecase.signup.AuthenticateCodeUseCase
import com.example.domain.usecase.signup.AuthenticateCodeUseCaseImpl
import com.example.domain.usecase.signup.SendEmailUseCase
import com.example.domain.usecase.signup.SendEmailUseCaseImpl
import dagger.Module
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {
    @Binds
    @Singleton
    fun provideSendEmailUseCase(
        sendEmailUseCaseImpl: SendEmailUseCaseImpl
    ): SendEmailUseCase

    @Binds
    @Singleton
    fun provideAuthenticateCodeUseCase(
        authenticateCodeUseCaseImpl: AuthenticateCodeUseCaseImpl
    ): AuthenticateCodeUseCase
}