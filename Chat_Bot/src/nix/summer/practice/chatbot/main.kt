package nix.summer.practice.chatbot
import java.util.*

fun main() {
    val botName = "Aboba"
    val yearOfCreating = "2022"
    println("Hello! My name is $botName")
    println("I was created in $yearOfCreating")

    println("Please, remind me your name.")
    val yourName = readLine()
    println("What a great name you have, $yourName")

    val scanner  = Scanner(System.`in`)
    println("Let me guess your age.")
    println("Enter remainders of dividing your age by 3, 5 and 7.")
    val remainder3 = scanner.nextInt()
    val remainder5 = scanner.nextInt()
    val remainder7 = scanner.nextInt()
    scanner.nextLine()
    val yourAge = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105
    println("Your age is $yourAge; that's a good time to start programming!")

    println("I will prove to you that I can count to any number you want:")
    val numb = scanner.nextInt()
    scanner.nextLine()
    for (i in 1..numb) {
        println("$i !")
    }

    while (true) {
        println("What is the size of the Char in Kotlin?")
        println("1. 2 bytes")
        println("2. 8 bits")
        println("3. 1 bits")
        val answ = scanner.nextInt()
        scanner.nextLine()
        if(answ == 1) {
            println("Great, you right!")
            break;
        }
        else {
            println("Please, try again")
        }
    }
    println("Goodbye, have a nice day!")
}