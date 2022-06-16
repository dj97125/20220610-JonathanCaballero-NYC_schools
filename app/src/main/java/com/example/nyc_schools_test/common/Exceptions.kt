package com.example.nyc_schools_test.common


class NullResponseException(
    message: String = "Response is null"
) : Exception(message)

class FailedResponseException(
    message: String = "Error: failure in the response"
) : Exception(message)
