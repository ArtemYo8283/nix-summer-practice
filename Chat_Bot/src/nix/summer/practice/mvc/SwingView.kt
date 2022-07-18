package nix.summer.practice.mvc

import java.awt.FlowLayout
import java.awt.Font
import java.awt.Color
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*
import kotlin.system.exitProcess


class SwingView(override var controller: Controller) : JFrame(), View {

    private lateinit var mainFrame: JFrame

    private lateinit var controlPanel: JPanel
    private lateinit var fillPanel: JPanel
    private lateinit var statusPanel: JPanel
    private lateinit var footerPanel: JPanel
    private lateinit var groupOfButton: JPanel

    private lateinit var infoLabel: JTextArea

    private lateinit var statusLabel: JLabel
    private lateinit var waterLabel: JLabel
    private lateinit var milkLabel: JLabel
    private lateinit var coffeeLabel: JLabel
    private lateinit var cupLabel: JLabel
    private lateinit var spaceLabel: JLabel

    private lateinit var waterInput: JTextField
    private lateinit var milkInput: JTextField
    private lateinit var coffeeInput: JTextField
    private lateinit var cupInput: JTextField

    init {
        createUI()
    }

    private fun createUI() {
        title = SwingView::class.java.toString()
        controlPanel = JPanel().apply { layout = FlowLayout(FlowLayout.CENTER, 50, 10)
                                        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)}

        fillPanel = JPanel().apply { layout = GridLayout(5, 2, 300, 5)
                                        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)}

        footerPanel = JPanel().apply { layout = GridLayout(1, 2, 10, 10)
                                        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)}

        statusPanel = JPanel().apply { layout = FlowLayout()
                                        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)}

        groupOfButton = JPanel().apply { layout = GridLayout(2, 1, 10, 10)
                                        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)}

        spaceLabel = JLabel("")

        mainFrame = JFrame("Swing View").apply {
            setSize(600,600)
            layout = GridLayout(4,1)
            addWindowListener(object: WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    exitProcess(0)
                }
            })
            add(controlPanel)
            add(statusPanel)
            add(fillPanel)
            add(footerPanel)
            isVisible = true
        }

        val EspressoButton = JButton("Make espresso").apply {
            actionCommand = "ESPRESSO"
            addActionListener(ButtonClick())
        }
        controlPanel.add(EspressoButton)

        val LatteButton = JButton("Make latte").apply {
            actionCommand = "LATTE"
            addActionListener(ButtonClick())
        }
        controlPanel.add(LatteButton)

        val CappuccinoButton = JButton("Make cappuccino").apply {
            actionCommand = "CAPPUCCINO"
            addActionListener(ButtonClick())
        }
        controlPanel.add(CappuccinoButton)

        statusLabel = JLabel("Ready!")
        statusLabel.setFont(Font("Verdana", Font.PLAIN, 24))
        statusLabel.setForeground(Color.GREEN);
        statusPanel.add(statusLabel)


        waterInput = JTextField("0",3)
        milkInput = JTextField("0",3)
        coffeeInput = JTextField("0",3)
        cupInput = JTextField("0",3)

        waterLabel= JLabel("Water: ")
        milkLabel = JLabel("Milk: ")
        coffeeLabel = JLabel("Coffee beans: ")
        cupLabel = JLabel("Cups: ")


        fillPanel.add(waterLabel)
        fillPanel.add(waterInput)

        fillPanel.add(milkLabel)
        fillPanel.add(milkInput)

        fillPanel.add(coffeeLabel)
        fillPanel.add(coffeeInput)

        fillPanel.add(cupLabel)
        fillPanel.add(cupInput)

        val fillButton = JButton("Fill").apply {
            addActionListener {
                val resources = Resources(
                    waterInput.text.toInt(),
                    milkInput.text.toInt(),
                    coffeeInput.text.toInt(),
                    cupInput.text.toInt())

                waterInput.text = "0"
                milkInput.text = "0"
                coffeeInput.text = "0"
                cupInput.text = "0"

                controller.fillResources(resources)
            }
        }
        fillPanel.add(spaceLabel)
        fillPanel.add(fillButton)

        infoLabel = JTextArea("Water: ml.\n\n" +
                                "Milk: ml.\n\n" +
                                "Coffee beans: gr.\n\n" +
                                "Cups: \n\n")

        val takeButton = JButton("Take money").apply {
            actionCommand = "take"
            addActionListener(ButtonClick())
        }

        val InfoButton = JButton("Get info").apply {
            actionCommand = "remaining"
            addActionListener(ButtonClick())
        }

        footerPanel.add(infoLabel)
        groupOfButton.add(InfoButton)
        groupOfButton.add(takeButton)
        footerPanel.add(groupOfButton)

        mainFrame.isVisible = true
    }

    fun showWindow(str: String) {
        JOptionPane.showMessageDialog(mainFrame, str)
    }

    override fun setStatus(status: Status)
    {
        if(status == Status.OK) {
            statusLabel.setForeground(Color.GREEN)
        }
        else {
            statusLabel.setForeground(Color.RED)
        }
        statusLabel.text = status.msg
    }

    inner class ButtonClick : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            if (e != null) {
                statusLabel.setForeground(Color.GREEN)
                statusLabel.text = "Ready!"
                controller.takeCommand(e.actionCommand)
            }
        }
    }

    override fun showMoney(value: Int) {
        showWindow("You receive: $value grn.")
    }

    override fun showInfo(resources: Resources) {
        infoLabel.text = "Water: ${resources.water}ml.\n\n" +
                        "Milk: ${resources.milk}ml.\n\n" +
                        "Coffee beans: ${resources.coffeeBeans}gr.\n\n" +
                        "Cups: ${resources.disposableCups}\n\n"
    }

    override fun start() {
        controller.takeCommand("remaining")
    }

    override fun buyMenu() {
        controller.takeCommand("remaining")
    }

    override fun fillMenu() {
        controller.takeCommand("remaining")
    }
}
