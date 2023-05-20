package com.example.domain.di

import com.example.domain.usecase.interview.GetInterviewQuestionUseCaseImpl
import com.example.domain.usecase.interview.GetInterviewQuestionsUseCase
import com.example.domain.usecase.interview.SetInterviewUseCase
import com.example.domain.usecase.interview.SetInterviewUseCaseImpl
import com.example.domain.usecase.signup.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    @Singleton
    fun provideSetSignUpUseCase(
        setSignUpUseCaseImpl: SetSignUpUseCaseImpl
    ): SetSignUpUseCase

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

    @Binds
    @Singleton
    fun provideSetLoginUseCase(
        setLoginUserCaseImpl: SetLoginUserCaseImpl
    ): SetLoginUseCase

    @Binds
    @Singleton
    fun provideCheckNicknameDuplicationUseCase(
        checkNicknameDuplicationUseCaseImpl: CheckNicknameDuplicationUseCaseImpl
    ): CheckNicknameDuplicationUseCase

    @Binds
    @Singleton
    fun provideSetUserKeywordUseCase(
        setUserKeywordCaseImpl: SetUserKeywordCaseImpl
    ): SetUserKeywordCase

    @Binds
    @Singleton
    fun provideSetInterviewUseCase(
        setInterviewUseCaseImpl: SetInterviewUseCaseImpl
    ): SetInterviewUseCase

    @Binds
    @Singleton
    fun provideGetInterviewQuestionsUseCase(
        getInterviewQuestionUseCaseImpl: GetInterviewQuestionUseCaseImpl
    ): GetInterviewQuestionsUseCase

}