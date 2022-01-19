package com.example.patrick_santos

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class ViewCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var number: String? = null

    private var dragEnabled = false

    private var circlePosX: Float? = null
    private var circlePosY: Float? = null
    private val radius = 200f

    private var textPosX: Float? = null
    private var textPosY: Float? = null
    private val textBounds = Rect()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawElements(canvas)
    }

    private fun drawElements(canvas: Canvas?) {
        if (number == null)
            return

        // Circle
        val circlePaint =
            Paint().apply {
                color = Color.BLACK
                style = Paint.Style.FILL
            }

        // Text
        val mTextSize = 200f
        val textPaint = Paint().apply {
            textSize = mTextSize
            color = Color.WHITE
            style = Paint.Style.FILL
        }
        textPaint.getTextBounds(number, 0, number!!.length, textBounds);

        if (circlePosX == null || circlePosY == null || textPosX == null || textPosY == null) {
            circlePosX = (canvas!!.width / 2).toFloat()
            circlePosY = (canvas!!.height / 2).toFloat()

            textPosX = circlePosX!! - (textBounds.width() / 2)
            textPosY = circlePosY!! + (textBounds.height() / 2)
        }

        // Drawing
        canvas?.drawCircle(
            circlePosX!!,
            circlePosY!!,
            radius,
            circlePaint
        )

        canvas?.drawText(
            number!!,
            textPosX!!,
            textPosY!!,
            textPaint
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (event.x >= circlePosX!! - radius
                && event.x <= circlePosX!! + radius
                && event.y >= circlePosY!! - radius
                && event.y <= circlePosY!! + radius
            ) {
                dragEnabled = true
            }
        }
        if (event.action == MotionEvent.ACTION_MOVE && dragEnabled) {
            circlePosX = event.x
            circlePosY = event.y

            textPosX = circlePosX!! - (textBounds.width() / 2)
            textPosY = circlePosY!! + (textBounds.height() / 2)
        }
        if (event.action == MotionEvent.ACTION_UP) {
            dragEnabled = false
        }

        this.postInvalidate()
        return true
    }

    fun setNumber(number: String) {
        this.number = number
    }
}