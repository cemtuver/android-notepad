package tuver.notepad.util.extension

suspend fun <T> tryResult(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}