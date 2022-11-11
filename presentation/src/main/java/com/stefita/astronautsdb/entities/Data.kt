package com.stefita.astronautsdb.entities

/**
 * A generic wrapper class around data request
 */
data class Data<RequestData>(
    var responseType: StatusResponse,
    var data: RequestData? = null,
    var error: Error? = null)

enum class StatusResponse { SUCCESSFUL, ERROR, LOADING }