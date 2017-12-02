package com.isolity.toastalarm.application.`interface`

/**
 * Created by shoarai on 2017/12/02.
 */
interface IAppService<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
    fun add(model: T)
    fun update(model: T)
    fun delete(model: T)
}