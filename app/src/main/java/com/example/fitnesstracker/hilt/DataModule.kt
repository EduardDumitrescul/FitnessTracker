package com.example.fitnesstracker.hilt

import android.content.Context
import com.example.fitnesstracker.data.repositories.ExerciseRepository
import com.example.fitnesstracker.data.repositories.MuscleRepository
import com.example.fitnesstracker.data.roomdb.AppDatabase
import com.example.fitnesstracker.data.roomdb.dao.ExerciseDao
import com.example.fitnesstracker.data.roomdb.dao.MuscleDao
import com.example.fitnesstracker.data.roomdb.repository.RoomExerciseRepository
import com.example.fitnesstracker.data.roomdb.repository.RoomMuscleRepository
import com.example.fitnesstracker.services.ExerciseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Provides
    @Singleton
    fun provideExerciseService(
        exerciseRepository: ExerciseRepository,
        muscleRepository: MuscleRepository)
    : ExerciseService {
        return ExerciseService(exerciseRepository, muscleRepository)
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(
        exerciseDao: ExerciseDao
    ): ExerciseRepository {
        return RoomExerciseRepository(exerciseDao)
    }

    @Provides
    @Singleton
    fun provideMuscleRepository(
        muscleDao: MuscleDao
    ): MuscleRepository {
        return RoomMuscleRepository(muscleDao)
    }

    @Provides
    @Singleton
    fun provideMuscleDao(
        appDatabase: AppDatabase
    ): MuscleDao {
        return appDatabase.muscleDao()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(
        appDatabase: AppDatabase
    ): ExerciseDao {
        return appDatabase.exerciseDao()
    }

//
//    @Provides
//    fun provideChannelDao(appDatabase: AppDatabase): TestDao {
//        return appDatabase.testDao()
//    }
//
//    @Provides
//    fun provideExerciseMuscleDao(appDatabase: AppDatabase): ExerciseMuscleDao {
//        return appDatabase.exerciseMuscleDao()
//    }
//
//    @Provides
//    fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
//        return appDatabase.exerciseDao()
//    }
//
//    @Provides
//    fun provideExerciseRepository(
//        exerciseDao: ExerciseDao
//    ): ExerciseRepository {
//        return ExerciseRepository(exerciseDao)
//    }
//
//    @Provides
//    fun provideMuscleDao(appDatabase: AppDatabase): MuscleDao {
//        return appDatabase.muscleDao()
//    }
//
//    @Provides
//    fun provideMuscleRepository(
//        muscleDao: MuscleDao
//    ): MuscleRepository {
//        return MuscleRepository(muscleDao)
//    }
//
//    @Provides
//    fun provideSetDao(appDatabase: AppDatabase): SetDao {
//        return appDatabase.setDao()
//    }
//
//    @Provides
//    fun provideSetRepository(
//        setDao: SetDao
//    ): SetRepository {
//        return SetRepository(setDao)
//    }
//
//    @Provides
//    fun provideTemplateExerciseDao(appDatabase: AppDatabase): TemplateExerciseDao {
//        return appDatabase.templateExerciseDao()
//    }
//
//    @Provides
//    fun provideTemplateExerciseRepository(
//        templateExerciseDao: TemplateExerciseDao
//    ): TemplateExerciseRepository {
//        return TemplateExerciseRepository(templateExerciseDao)
//    }
//
//    @Provides
//    fun provideTemplateDao(appDatabase: AppDatabase): TemplateDao {
//        return appDatabase.templateDao()
//    }
//
//    @Provides
//    fun provideTemplateRepository(
//        templateDao: TemplateDao
//    ): TemplateRepository {
//        return TemplateRepository(templateDao)
//    }
//
//    @Provides
//    fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao {
//        return appDatabase.workoutDao()
//    }
//
//    @Provides
//    fun provideWorkoutRepository(
//        workoutDao: WorkoutDao
//    ): WorkoutRepository {
//        return WorkoutRepository(workoutDao)
//    }
}