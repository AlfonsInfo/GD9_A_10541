package com.alfonsus.gd9_camera_a_10541

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException

class CameraView (context: Context?, private val mCamera: Camera) : SurfaceView(context),SurfaceHolder.Callback{
    private val mHolder : SurfaceHolder

    init {
        mCamera.setDisplayOrientation(90)
        mHolder = holder
        mHolder.addCallback(this)
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }
    override fun surfaceCreated(p0: SurfaceHolder) {
        try{
            mCamera.setPreviewDisplay(mHolder)
            mCamera.startPreview()
        } catch (e : IOException)
        {
            Log.d("error", "Camera error on SurfaceCreated" + e.message)
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        if(mHolder.surface == null) return
        try{
            mCamera.setPreviewDisplay(mHolder)
            mCamera.startPreview()
        } catch (e : IOException)
        {
            Log.d("error", "Camera error on SurfaceCreated" + e.message)
        }
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        mCamera.stopPreview()
        mCamera.release()
    }

}