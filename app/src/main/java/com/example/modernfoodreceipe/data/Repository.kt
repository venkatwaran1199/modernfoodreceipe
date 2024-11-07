package com.example.modernfoodreceipe.data

import dagger.hilt.android.lifecycle.ActivityRetainedSavedState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource
}