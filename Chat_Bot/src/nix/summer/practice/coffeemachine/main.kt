package nix.summer.practice.coffeemachine
import java.util.*

enum class Coffee(val water: Int, val milk: Int, val coffee: Int, val money: Int) {
    ESPRESSO (250, 0, 16, 4),
    LATTE (350, 75, 20, 7),
    CAPPUCCINO (200, 100, 12, 6)
}

class CoffeeMachine(private var water: Int = 400,
                   private var milk: Int = 540,
                   private var coffee: Int = 120,
                   private var cups: Int = 9,
                   private var money: Int = 550) {

    fun take() {
        println("I gave you $money")
        money = 0
    }

    fun remaining() {
        println("The coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$coffee of coffee beans")
        println("$cups of disposable cups")
        println("$money of money")
    }

    fun fill(_water: Int, _milk: Int, _coffee: Int, _cups: Int) {
        water += _water
        milk += _milk
        coffee += _coffee
        cups += _cups
    }

    fun buy(type: Coffee) {
        if(water - type.water < 0) {
            println("Sorry, not enough water!")
            return
        }
        if (milk - type.milk < 0) {
            println("Sorry, not enough milk!")
            return
        }
        if (coffee - type.coffee < 0) {
            println("Sorry, not enough coffee!")
            return
        }
        if (cups == 0) {
            println("Sorry, not enough cups!")
            return
        }
        cups--
        money += type.money
        water -= type.water
        milk -= type.milk
        coffee -= type.coffee
        println("Yes, I can make that amount of coffee")
        println("Starting to make a coffee in NIX office")
        Thread.sleep(1_000)
        println("Grinding coffee beans")
        Thread.sleep(1_000)
        println("Boiling water")
        Thread.sleep(2_000)
        println("Mixing boiled water with crushed coffee beans")
        Thread.sleep(1_000)
        println("Pouring coffee into the cup")
        Thread.sleep(1_000)
        println("Pouring some milk into the cup")
        Thread.sleep(2_000)
        println("Coffee is ready! Go to work!")
        Thread.sleep(3_000)
    }

    fun command(comm: String, scanner: Scanner, coffemachine: CoffeeMachine) {
        when(comm) {
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to main menu:")
                val coffeeType = readLine()
                when(coffeeType) {
                    "1" -> coffemachine.buy(Coffee.ESPRESSO)
                    "2" -> coffemachine.buy(Coffee.LATTE)
                    "3" -> coffemachine.buy(Coffee.CAPPUCCINO)
                    "back" -> return
                    else -> println("Write correct action!")
                }
            }
            "fill" -> {
                println("Write how many ml of water do you want to add:")
                val water = scanner.nextInt()
                scanner.nextLine()

                println("Write how many ml of milk do you want to add:")
                val milk = scanner.nextInt()
                scanner.nextLine()

                println("Write how many grams of coffee beans do you want to add:")
                val coffee = scanner.nextInt()
                scanner.nextLine()

                println("Write how many disposable cups of coffee do you want to add:")
                val cups = scanner.nextInt()
                scanner.nextLine()

                coffemachine.fill(water, milk, coffee, cups)
            }
            "take" -> coffemachine.take()
            "remaining" -> coffemachine.remaining()
            "exit" -> return
            else -> println("Write correct action!")
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val coffemachine = CoffeeMachine()
    do {
        println("Write action (buy, fill, take, remaining, exit):")
        val answ: String
        answ = readLine().toString()
        coffemachine.command(answ, scanner, coffemachine)
    } while (answ != "exit")
}
