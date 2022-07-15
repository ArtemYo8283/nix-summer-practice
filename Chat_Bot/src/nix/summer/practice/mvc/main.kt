package nix.summer.practice.mvc

fun main() {
    val model = Model()
    val controller = Controller(model)
    val view = SwingView(controller)
    controller.attachView(view)

    controller.start()
}
