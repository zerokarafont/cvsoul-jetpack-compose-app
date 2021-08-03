package com.compose.cvsoul.repository.model

data class PaginationList<T>(val page: Int, val pageSize: Int, val data: List<T>)