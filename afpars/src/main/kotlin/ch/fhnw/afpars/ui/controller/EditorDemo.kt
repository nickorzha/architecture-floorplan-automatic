package ch.fhnw.afpars.ui.controller

import ch.fhnw.afpars.io.reader.AFImageReader
import ch.fhnw.afpars.ui.control.editor.ImageEditor
import ch.fhnw.afpars.ui.control.editor.Layer
import ch.fhnw.afpars.ui.control.editor.shapes.RectangleShape
import ch.fhnw.afpars.ui.control.editor.tools.LineTool
import ch.fhnw.afpars.ui.control.editor.tools.OvalTool
import ch.fhnw.afpars.ui.control.editor.tools.RectangleTool
import ch.fhnw.afpars.ui.control.editor.tools.ViewTool
import ch.fhnw.afpars.util.toImage
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Dimension2D
import javafx.scene.paint.ImagePattern
import javafx.stage.FileChooser
import org.opencv.core.Core

/**
 * Created by cansik on 15.01.17.
 */
class EditorDemo {
    @FXML
    var editor: ImageEditor? = null

    init {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    }

    fun viewToolClicked(e: ActionEvent) {
        editor!!.activeTool = ViewTool()
    }

    fun ellipseToolClicked(e: ActionEvent) {
        editor!!.activeTool = OvalTool()
    }

    fun lineToolClicked(e: ActionEvent) {
        editor!!.activeTool = LineTool()
    }

    fun rectangleToolClicked(e: ActionEvent) {
        editor!!.activeTool = RectangleTool()
    }

    fun loadImage(e: ActionEvent) {
        val fileChooser = FileChooser()
        fileChooser.title = "Open image"
        val file = fileChooser.showOpenDialog(null)
        if (file != null) {
            val source = AFImageReader().read(file.toPath())
            val image = source.image.toImage()

            editor!!.resizeCanvas(image.width, image.height)

            // set layer
            val imageLayer = Layer("Image")
            val drawLayer = Layer("Draw")

            val imageRect = RectangleShape()
            imageRect.size = Dimension2D(image.width, image.height)
            imageRect.noStroke()
            imageRect.fill = ImagePattern(image, 0.0, 0.0, image.width, image.height, false)

            imageLayer.shapes.add(imageRect)

            editor!!.layers.clear()
            editor!!.layers.add(imageLayer)
            editor!!.layers.add(drawLayer)
            editor!!.activeLayer = drawLayer
            editor!!.redraw()
        }
    }
}