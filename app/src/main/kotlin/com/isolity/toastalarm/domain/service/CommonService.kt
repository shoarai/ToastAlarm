package com.isolity.toastalarm.domain.service

import com.isolity.toastalarm.domain.`interface`.IRepository
import com.isolity.toastalarm.domain.`interface`.IService

/**
 * Created by shoarai on 2017/04/22.
 */

abstract class CommonService<T>(private val repository: IRepository<T>) : IService<T> {
    override fun getById(id: Int): T {
        return repository.getById(id)
    }

    override fun getAll(): List<T> {
        return repository.getAll()
    }

    override fun add(model: T) {
        repository.add(model)
    }

    override fun update(model: T) {
        repository.update(model)
    }

    override fun delete(model: T) {
        repository.delete(model)
    }
}