package com.isolity.toastalarm.dagger

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Copyright (c) 2017 shoarai

@Module
class ClientModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

//    @Provides
//    @Singleton
//    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()


}