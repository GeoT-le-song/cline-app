package com.example.clineapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val divinationApiService: DivinationApiService,
    private val userDao: UserDao
) {

    fun getHoroscope(date: String): Flow<Result<HoroscopeDetailDto>> = flow {
        emit(Result.Loading)
        try {
            val horoscope = divinationApiService.getHoroscope(date)
            // Assuming HoroscopeDto contains a list of horoscopes and we take the first one.
            // This might need adjustment based on the actual structure of HoroscopeDto.
            val detail = horoscope.horoscope.first()
            emit(Result.Success(detail))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    fun getUserProfile(): Flow<UserEntity?> {
        return userDao.get()
    }
}
