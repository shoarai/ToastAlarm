package com.isolity.toastalarm.domain.`interface`

/**
 * Created by shoarai on 2017/12/02.
 */
interface IRepository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
    fun add(model: T)
    fun update(model: T)
    fun delete(model: T)
}