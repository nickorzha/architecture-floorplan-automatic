package ch.fhnw.afpars.ui.control.editor.tools

import ch.fhnw.afpars.ui.control.editor.ImageEditor
import ch.fhnw.afpars.ui.control.editor.shapes.LineShape
import javafx.geometry.Point2D
import javafx.scene.input.MouseEvent

/**
 * Created by cansik on 25.01.17.
 */
class LineTool : BaseEditorTool() {
    var dragStart = Point2D.ZERO!!
    var current = LineShape()

    override fun onCanvasMousePressed(imageEditor: ImageEditor, event: MouseEvent) {
        dragStart = Point2D(event.x, event.y)
        current = LineShape(dragStart, dragStart)
        imageEditor.activeLayer.shapes.add(current)
        imageEditor.redraw()
    }

    override fun onCanvasMouseDragged(imageEditor: ImageEditor, event: MouseEvent) {
        current.point2 = Point2D(event.x, event.y)
        imageEditor.redraw()
    }

    override fun onCanvasMouseReleased(imageEditor: ImageEditor, event: MouseEvent) {
        imageEditor.redraw()
    }
}