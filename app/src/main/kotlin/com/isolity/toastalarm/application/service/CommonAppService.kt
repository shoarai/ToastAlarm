package com.isolity.toastalarm.application.service

import com.isolity.toastalarm.application.`interface`.IAppService
import com.isolity.toastalarm.domain.`interface`.IService

/**
 * Created by shoarai on 2017/12/02.
 */

abstract class CommonAppService<T>(private val service: IService<T>) : IAppService<T> {
    override fun getById(id: Int): T {
        return service.getById(id)
    }

    override fun getAll(): List<T> {
        return service.getAll()
    }

    override fun add(model: T) {
        service.add(model)
    }

    override fun update(model: T) {
        service.update(model)
    }

    override fun delete(model: T) {
        service.delete(model)
    }
}