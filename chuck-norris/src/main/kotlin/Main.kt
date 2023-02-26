fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        when (val inputOperation = readln()) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                println("Bye!")
                break
            }
            else -> println("There is no '$inputOperation' operation")
        }
    }
}

fun encode() {
    println("Input string:")
    val word = readln()
    var binaryFormat = ""

    for (i in word) {
        binaryFormat += String.format("%07d", i.code.toString(2).toInt())
    }

    var first = binaryFormat[0]
    var result = ""
    var counter = 0
    for (i in binaryFormat.indices) {
        if (binaryFormat[i] == first) {
            counter++
            if (i != binaryFormat.lastIndex) continue
        }
        val rep = if (first == '1') "0" else "00"
        result += " $rep ${MutableList(counter) { 0 }.joinToString("")}"

        counter = 1
        if (i == binaryFormat.lastIndex && binaryFormat[i] != first) {
            val reps = if (binaryFormat[i] == '1') "0" else "00"
            result += " $reps ${MutableList(counter) { 0 }.joinToString("")}"
        }
        first = binaryFormat[i]
    }
    println("Encoded string:")
    println(result.trim())

}

fun decode() {
    var code = ""
    println("encoded string:")
    val word = readln().split(" ")

    for (i in 0 until word.size - 1 step 2) {
        code += if (word[i] == "0") "1".repeat(word[i + 1].length)
        else if (word[i] == "00") "0".repeat(word[i + 1].length)
        else return println("Encoded string is not valid.")
    }
    var result = ""
    for (i in code.indices step 7) {
        try {
            result += code.substring(startIndex = i, endIndex = i + 7).toInt(2).toChar()
        }catch (e: Exception){
            return println("Encoded string is not valid.")
        }
    }
    println("Decoded string:")
    println(result)
}