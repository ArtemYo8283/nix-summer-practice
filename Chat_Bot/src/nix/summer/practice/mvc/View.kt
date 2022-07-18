package nix.summer.practice.mvc

interface View {

    var controller: Controller

    fun showInfo(resources: Resources)

    fun showMoney(value: Int)

    fun setStatus(str: Status)

    fun start()

    fun buyMenu()

    fun fillMenu()
}
