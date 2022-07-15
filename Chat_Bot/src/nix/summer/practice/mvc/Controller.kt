package nix.summer.practice.mvc
import java.lang.System.exit

class Controller(private val model: Model) {

    private lateinit var view: View

    fun attachView(_view: View) {
        view = _view
    }

    fun takeCommand(command: String) {
        when (command) {
            "buy" -> {
                view.buyMenu()
            }
            "fill" -> {
                view.fillMenu()
            }
            "ESPRESSO" -> {
                view.setStatus(model.buy(Coffee.ESPRESSO))
            }
            "LATTE" -> {
                view.setStatus(model.buy(Coffee.LATTE))
            }
            "CAPPUCCINO" -> {
                view.setStatus(model.buy(Coffee.CAPPUCCINO))
            }
            "take" -> {
                view.showMoney(model.take())
            }
            "remaining" -> {
                
            }
            "exit" -> {
                exit(0)
            }
            else -> println(" ")
        }
        view.showInfo(model.remaining())
    }

    fun fillResources(resources: Resources) {
        model.fill(resources)
        view.showInfo(model.remaining())
    }

    fun start() {
        view.start()
    }
}
