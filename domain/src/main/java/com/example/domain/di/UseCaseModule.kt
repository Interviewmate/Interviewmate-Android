package com.example.domain.di

import com.example.domain.usecase.interview.*
import com.example.domain.usecase.mypage.*
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

    @Binds
    @Singleton
    fun provideSetS3PreSignedUseCase(
        setS3PreSignedUseCaseIml: SetS3PreSignedUseCaseImpl
    ): SetS3PreSignedUseCase

    @Binds
    @Singleton
    fun providePutInterviewVideoUseCase(
        putInterviewVideoUseCaseImpl: PutInterviewVideoUseCaseImpl
    ): PutInterviewVideoUseCase

    @Binds
    @Singleton
    fun provideSetInterviewAnalysesUseCase(
        setInterviewAnalysesUseCaseImpl: SetInterviewAnalysesUseCaseImpl
    ): SetInterviewAnalysesUseCase

    @Binds
    @Singleton
    fun provideGetUserInfoUseCase(
        getUserInfoUseCaseImpl: GetUserInfoUseCaseImpl
    ): GetUserInfoUseCase

    @Binds
    @Singleton
    fun providePutPortfolioUseCase(
        putPortfolioUseCaseImpl: PutPortfolioUseCaseImpl
    ): PutPortfolioUseCase

    @Binds
    @Singleton
    fun providePutPortfolioKeywordUseCase(
        putPortfolioKeywordUseCaseImpl: PutPortfolioKeywordUseCaseImpl
    ): PutPortfolioKeywordUseCase
}