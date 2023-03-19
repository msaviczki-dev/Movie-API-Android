package com.freecast.thatmovieapp.di

class ModuleProvider {
    fun provide() = listOf(
        provideDataModule() + providePresentationModule() + provideDomainModule()
    ).flatten()
}