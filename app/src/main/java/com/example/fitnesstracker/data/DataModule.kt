package com.example.fitnesstracker.data

import android.content.Context
import androidx.room.Room
import com.example.fitnesstracker.data.daos.ExerciseDao
import com.example.fitnesstracker.data.daos.ExerciseMuscleDao
import com.example.fitnesstracker.data.daos.MuscleDao
import com.example.fitnesstracker.data.daos.SetDao
import com.example.fitnesstracker.data.daos.TestDao
import com.example.fitnesstracker.data.repositories.ExerciseRepository
import com.example.fitnesstracker.data.repositories.MuscleRepository
import com.example.fitnesstracker.data.repositories.SetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // to make sure the dependencies stay alive as long as the aplication is running
class DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RssReader"
        ).build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): TestDao {
        return appDatabase.testDao()
    }

    @Provides
    fun provideExerciseMuscleDao(appDatabase: AppDatabase): ExerciseMuscleDao {
        return appDatabase.exerciseMuscleDao()
    }

    @Provides
    fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
        return appDatabase.exerciseDao()
    }

    @Provides
    fun provideExerciseRepository(
        exerciseDao: ExerciseDao
    ): ExerciseRepository {
        return ExerciseRepository(exerciseDao)
    }

    @Provides
    fun provideMuscleDao(appDatabase: AppDatabase): MuscleDao {
        return appDatabase.muscleDao()
    }

    @Provides
    fun provideMuscleRepository(
        muscleDao: MuscleDao
    ): MuscleRepository {
        return MuscleRepository(muscleDao)
    }

    @Provides
    fun provideSetDao(appDatabase: AppDatabase): SetDao {
        return appDatabase.setDao()
    }

    @Provides
    fun provideSetRepository(
        setDao: SetDao
    ): SetRepository {
        return SetRepository(setDao)
    }

}