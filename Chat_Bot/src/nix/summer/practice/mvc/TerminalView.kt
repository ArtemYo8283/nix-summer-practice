package nix.summer.practice.mvc

import nix.summer.practice.coffeemachine.Coffee
import java.util.*

class TerminalView(override var controller: Controller) : View {

    private val scanner = Scanner(System.`in`)

    override fun showInfo(resources: Resources) {
        println("The coffee machine has:")
        println("${resources.water} of water")
        println("${resources.milk} of milk")
        println("${resources.coffeeBeans} of coffee beans")
        println("${resources.disposableCups} of disposable cups")
    }

    override fun start() {
        while (true) {
            do {
                println("Write action (buy, fill, take, remaining, exit):")
                val answer: String = readLine().toString()
                controller.takeCommand(answer)
            } while (answer != "exit")
        }
    }

    override fun showMoney(value: Int) {
        println("You receive $value grn.")
    }

    override fun setStatus(str: Status) {
        println(str.msg)
    }
    
    override fun buyMenu() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to main menu:")
        val coffeeType = readLine()
        when(coffeeType) {
            "1" -> controller.takeCommand("ESPRESSO")
            "2" -> controller.takeCommand("LATTE")
            "3" -> controller.takeCommand("CAPPUCCINO")
            "back" -> return
            else -> println("Write correct action!")
        }
    }

    override fun fillMenu() {
        val resources = Resources()
        println("Write how many ml of water do you want to add:")
        resources.water = scanner.nextInt()
        scanner.nextLine()

        println("Write how many ml of milk do you want to add:")
        resources.milk = scanner.nextInt()
        scanner.nextLine()

        println("Write how many grams of coffee beans do you want to add:")
        resources.coffeeBeans = scanner.nextInt()
        scanner.nextLine()

        println("Write how many disposable cups of coffee do you want to add:")
        resources.disposableCups = scanner.nextInt()
        scanner.nextLine()

        controller.fillResources(resources)
    }
}
